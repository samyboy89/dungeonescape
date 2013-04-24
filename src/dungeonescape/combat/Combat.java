package dungeonescape.combat;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import acm.graphics.GCanvas;
import acm.graphics.GDimension;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.util.RandomGenerator;
import dungeonescape.Main;
import dungeonescape.ai.NPC;
import dungeonescape.character.Player;
import dungeonescape.combat.Animations.AnimationDeleteListener;
import dungeonescape.combat.Animations.AnimationDoneListener;
import dungeonescape.components.JProgressBarColoredNPC;
import dungeonescape.components.JProgressBarColoredPlayer;
import dungeonescape.helper.Levels;
import dungeonescape.helper.PlayerImg;
import dungeonescape.helper.Tile;
import dungeonescape.helper.Type;
import dungeonescape.helper.Window;
import dungeonescape.items.Item;
import dungeonescape.items.Items;
import dungeonescape.map.Camera;
import dungeonescape.map.Map;

public class Combat {

	public GCanvas gcanvas = new GCanvas();
	private NPC npc;
	private Player player;
	private Map map;
	private Animations animations;

	JProgressBarColoredPlayer playerHealth;
	JProgressBarColoredNPC npcHealth;

	private boolean canQuit = false;

	// PLAYER
	private GImage player_view;
	private GDimension player_size;
	private GPoint player_location;

	// NPC
	private GImage npc_char;
	private GDimension npc_size;
	private GPoint npc_location;

	// STATES
	private int state = ROUND_DONE;
	public static final int PLAYERS_TURN = 0;
	public static final int NPCS_TURN = 1;
	protected static final int ROUND_DONE = 2;
	protected static final int FIGHT_OVER = 3;

	private static final int NO_BAR_UPDATE = -1;

	// BUTTONS
	private GCanvas fightButtonCanvas;
	private GCanvas potionButtonCanvas;

	// LOOT GRAPHICS
	private int counter = 0;
	GCanvas loot_gcanvas;
	ArrayList<GObject> labels = new ArrayList<GObject>();

	// DEBUG
	private boolean debug = false;

	public Combat(Player player, Map map, NPC npc) {
		gcanvas.setSize(Window.GAME_X, Window.WINDOW_Y);

		Main.main.setState(Main.COMBAT);
		this.animations = new Animations(map);
		this.player = player;
		this.map = map;
		this.npc = npc;
		this.animations
				.setAnimationDeleteListener(new AnimationDeleteListener() {

					@Override
					public void onDelete(GObject gimage) {
						gcanvas.remove(gimage);
					}
				});
		this.animations.setAnimationDoneListener(new AnimationDoneListener() {

			@Override
			public void onDone(int action) {
				switch (action) {
				case Animations.ATTACK:
					switch (state) {
					case PLAYERS_TURN:
						updatePlayerHealth(NO_BAR_UPDATE);
						if (Combat.this.player.getHealth() > 0) {
							state = ROUND_DONE;
							gcanvas.add(fightButtonCanvas);
							gcanvas.add(potionButtonCanvas);
						} else {
							state = FIGHT_OVER;
							if (Combat.this.player.getLife() > 1)
									playerLooseLife();
							else
								printGameOver();
						}
						break;
					case NPCS_TURN:
						updateNPCHealth(NO_BAR_UPDATE);
						if (Combat.this.npc.getHealth() > 0) {
							npcAttack();
							state = PLAYERS_TURN;
						} else {
							state = FIGHT_OVER;
							Combat.this.npc.setHealth(-10);
							printLootGraphics();
						}
						break;
					}
					break;
				case Animations.MOVE:

					break;
				case Animations.LOOT_PRINT:
					if (counter < labels.size()) {
						loot_gcanvas.add(labels.get(counter));
						animations.lootPrint(labels.get(counter));
						counter++;
					} else {
						canQuit = true;
					}
					break;
				}

			}
		});
		Main.main.getGCanvas().remove(Main.main.map.gcanvas);
		Main.main.getGCanvas().add(gcanvas);
		printViews();
	}

	// ************ //
	// KEY LISTENER //
	// ************ //

	public void onKey(int key) {

		switch (key) {
		case KeyEvent.VK_P:
			// OPEN POTION CHOICE
			break;
		case KeyEvent.VK_SPACE:
			if (canQuit) {
				quitCombat();
			}
			break;
		case KeyEvent.VK_Q:
			break;
		case KeyEvent.VK_F:
			if (state == ROUND_DONE) {
				state = NPCS_TURN;
				playerAttack();
			}
			break;
		}

	}

	// ***** //
	// FIGHT //
	// ***** //

	private void playerAttack() {
		int attack = (int) (player.getAttack() - npc.getDefence());
		if (debug) {
			System.out.println("PLAYER ATTACK: " + player.getAttack());
			System.out.println("NPC DEF.: " + npc.getDefence());
		}
		npc.setHealth(attack > 0 ? npc.getHealth() - attack
				: npc.getHealth() - 5);
		moveForwardAndBack(Animations.RIGHT, player_view, player_location,
				player_size);
		swordAttack();
	}

	private void npcAttack() {
		int attack = (int) (npc.getAttack() - player.getDefence());
		if (debug) {
			System.out.println("NPC ATTACK: " + npc.getAttack());
			System.out.println("PLAYER DEF.: " + player.getDefence());
		}
		player.setHealth(attack > 0 ? player.getHealth() - attack : player
				.getHealth() - 5);
		moveForwardAndBack(Animations.LEFT, npc_char, npc_location, npc_size);
		int random = RandomGenerator.getInstance().nextInt(0, 1);
		if (random == 0)
			npcSlashAttack();
		else
			npcSmachAttack();
	}

	// *********** //
	// UPDATE BARS //
	// *********** //

	public void updatePlayerHealth(int progress) {
		if (progress != NO_BAR_UPDATE)
			playerHealth.setValue(playerHealth.getValue() + progress);
		else
			playerHealth.setValue(player.getHealth());
		playerHealth.setMaximumValue(player.getMaxHealth());
	}

	public void updateNPCHealth(int progress) {
		if (progress != NO_BAR_UPDATE)
			npcHealth.setValue(npcHealth.getValue() + progress);
		else
			npcHealth.setValue(npc.getHealth());
	}

	// **** //
	// LOOT //
	// **** //

	private void afterFightLoot() {
		playerHealth.setValue(player.getHealth());
		playerHealth.setMaximumValue(player.getMaxHealth());
		player.addExperience(npc.getExperience());
		player.setGold(player.getGold() + npc.getGold());
	}	

	private void playerLooseLife() {
		player.setLife(player.getLife()-1);
		int code = map.getLevelCode();
		if (code != Levels.ROOMa)
			map.setLevelCode(map.getLevelCode()-1);
		else
			map.setLevelCode(Levels.ROOMA);
		quitCombat();
	}
	
	private void quitCombat() {
		Main.main.setState(Main.MAP);
		Main.main.setCombat(false);
		Main.main.getGCanvas().add(map.gcanvas);
		Main.main.getGCanvas().remove(gcanvas);
		map.startTimerForLevel();
		map.redrawViews();
		Main.main.combat = null;
	}


	// ******** //
	// GRAPHICS //
	// ******** //

	private void add(GObject gobject) {
		gcanvas.add(gobject);
	}

	private Camera getCamera() {
		return this.map.camera;
	}

	private void printViews() {
		add(getCombatBackground());
		add(getPlayerPortrait());
		add(getNPCPortrait());
		printPotionButton();
		printFightButton();
		initializeHealthBars();
		printPlayerStats();
		printNPCStats();
		printPlayerImage();
		printNPCImage();
	}

	private void initializeHealthBars() {
		playerHealth = new JProgressBarColoredPlayer(player.getMaxHealth(),
				player.getHealth());
		playerHealth.setValue(player.getHealth());
		playerHealth.setMaximumValue(player.getMaxHealth());
		playerHealth.setLocation(50, (getCamera().getWindowY()
				* Camera.IMG_SIZE * Camera.IMG_SCALE) - 60);
		playerHealth.setSize(98, 20);
		gcanvas.add(playerHealth);

		npcHealth = new JProgressBarColoredNPC(npc.getMaxHealth(), npc.getHealth());
		npcHealth.setValue(npc.getHealth());
		npcHealth.setMaximumValue(npc.getMaxHealth());
		npcHealth
				.setLocation(
						(getCamera().getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150,
						150);
		npcHealth.setSize(98, 20);
		gcanvas.add(npcHealth);
	}

	private GObject getCombatBackground() {
		return new GImage("combat_back/0599.png");
	}

	private GObject getPlayerPortrait() {
		GObject player_view = player.getCombatView();
		player_view
				.setLocation(
						50,
						(getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 160);
		return player_view;
	}

	private GObject getNPCPortrait() {
		GObject npc_view = npc.getCombatView();
		npc_view.setLocation(
				(getCamera().getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150,
				50);
		return npc_view;
	}

	private void printPlayerStats() {
		GLabel label = new GLabel("Lvl.: " + player.getLevel());
		label.setFont(Main.main.font.deriveFont(12f));
		label.setColor(Color.white);
		label.setLocation(
				50,
				(getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 165);
		add(label);
	}

	private void printNPCStats() {
		GLabel label = new GLabel("Lvl.: " + npc.getLevel());
		label.setFont(Main.main.font.deriveFont(12f));
		label.setColor(Color.white);
		label.setLocation(
				(getCamera().getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150,
				44);
		add(label);
	}

	private void printFightButton() {
		fightButtonCanvas = new GCanvas();
		fightButtonCanvas.setSize(250, 60);
		fightButtonCanvas.setLocation(200, (getCamera().getWindowY()
				* Camera.IMG_SIZE * Camera.IMG_SCALE) - 155);
		GImage attack = new GImage("combat_back/0499.png");
		attack.setSize(250, 60);
		attack.setLocation(0, 0);
		GLabel label = new GLabel("Fight (F)");
		label.setFont(Main.main.font.deriveFont(20f));
		label.setLocation(
				attack.getX() + (attack.getWidth() / 2)
						- (label.getWidth() / 2),
				attack.getY() + (attack.getHeight() / 2)
						+ (label.getHeight() / 4));
		fightButtonCanvas.add(attack);
		fightButtonCanvas.add(label);
		fightButtonCanvas.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (state == ROUND_DONE) {
					state = NPCS_TURN;
					playerAttack();
				}
			}
		});
		gcanvas.add(fightButtonCanvas);
	}

	private void printPotionButton() {
		potionButtonCanvas = new GCanvas();
		potionButtonCanvas.setSize(250, 60);
		potionButtonCanvas.setLocation(200, (getCamera().getWindowY()
				* Camera.IMG_SIZE * Camera.IMG_SCALE) - 90);
		GImage cancel = new GImage("combat_back/0499.png");
		cancel.setSize(250, 60);
		cancel.setLocation(0, 0);
		GLabel label = new GLabel("Use Potion (P)");
		label.setFont(Main.main.font.deriveFont(20f));
		label.setLocation(
				cancel.getX() + (cancel.getWidth() / 2)
						- (label.getWidth() / 2),
				cancel.getY() + (cancel.getHeight() / 2)
						+ (label.getHeight() / 4));
		potionButtonCanvas.add(cancel);
		potionButtonCanvas.add(label);
		potionButtonCanvas.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// CHOOSE POTION
			}
		});
		gcanvas.add(potionButtonCanvas);
	}

	private void printPlayerImage() {
		player.setLastMovedState(1);
		player_view = player.getCharacterImageState(
				(player.getCharacterState() + PlayerImg.EAST),
				PlayerImg.PLAYER_MAP_SIZE_LARGE, 0);
		player_view.scale(1.5);
		player_view.setLocation(150, ((getCamera().getWindowY()
				* Camera.IMG_SIZE * Camera.IMG_SCALE)) / 3);
		player_size = player_view.getSize();
		player_location = player_view.getLocation();
		add(player_view);
	}

	private void printNPCImage() {
		npc.setLastMovedState(1);
		npc_char = npc.getCharacterImageState(
				(npc.getCharacterState() + PlayerImg.WEST),
				PlayerImg.PLAYER_MAP_SIZE_LARGE, 0);
		npc_char.scale(2);
		npc_char.setLocation(
				(getCamera().getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 300,
				(getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) / 3);
		npc_size = npc_char.getSize();
		npc_location = npc_char.getLocation();
		add(npc_char);
	}

	// ****************** //
	// ANIMATIONS/ATTACKS //
	// ****************** //

	public void scaleUnScale() {
		animations.scaleUpScaleDown(npc_char, npc_location, npc_size);
	}

	public void moveForwardAndBack(int leftright, GImage image,
			GPoint location, GDimension size) {
		animations.moveForwardAndBack(image, location, size, leftright);
	}

	public void swordAttack() {
		gcanvas.remove(fightButtonCanvas);
		gcanvas.remove(potionButtonCanvas);
		Item item = Type.noAttack();
		for (Item i : player.getInventory().getItemsList()) {
			if (i.getParentType() == Type.SWORD && i.isActive())
				item = i;
		}
		GImage image = new GImage(Tile.PICKUPITEMS_IMG_PATH + item.getType()
				+ Tile.IMG_EXTENTION);
		image.scale(3);
		image.setLocation(
				(getCamera().getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 330,
				330);
		add(image);
		animations.swordAttack(image);
	}

	public void npcSmachAttack() {
		GOval image = new GOval(50, 50);
		image.setColor(Color.white);
		image.setFillColor(Color.white);
		image.setFilled(true);
		image.setLocation(
				150,
				((getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE)) / 3);
		add(image);
		animations.npcSmachAttack(image);
	}

	public void npcSlashAttack() {
		GImage image = new GImage("combat_back/0799" + Tile.IMG_EXTENTION);
		image.scale(3);
		image.setLocation(250, 300);
		add(image);
		animations.npcSlashAttack(image);
	}

	public void printLootGraphics() {
		afterFightLoot();

		loot_gcanvas = new GCanvas();
		loot_gcanvas.setBackground(new Color(255, 255, 255));
		loot_gcanvas.setSize(Window.GAME_X / 2, Window.WINDOW_Y / 2);
		loot_gcanvas.setLocation((Window.GAME_X / 4), (Window.WINDOW_Y / 4));
		this.gcanvas.add(loot_gcanvas);

		GLabel gold = new GLabel("Gold found: " + npc.getGold());
		gold.setLocation(10, 70);
		gold.setFont(Main.main.font.deriveFont(20f));

		GLabel exp = new GLabel("Experience gained: " + npc.getExperience());
		exp.setLocation(10, 100);
		exp.setFont(Main.main.font.deriveFont(20f));

		double[] progress = player.getLevelProgress();
		GLabel to_next_lvl = new GLabel("Lvl.: " + player.getLevel() + " (Progress: " + (int) progress[0]
				+ " %)");
		to_next_lvl.setLocation(10, 130);
		to_next_lvl.setFont(Main.main.font.deriveFont(20f));

		GLabel dummy = new GLabel("");

		labels.add(gold);
		labels.add(exp);
		labels.add(to_next_lvl);

		GLabel space = new GLabel("Press space to continue");
		space.setFont(Main.main.font.deriveFont(20f));
		space.setLocation((Window.GAME_X / 4) - ((space.getWidth() / 2) - 30)
				- (space.getWidth() / 4), loot_gcanvas.getHeight() - 40);
		labels.add(space);

		labels.add(dummy);

		Items items = new Items();
		ArrayList<Item> itemsList = items.getItems(player.getLevel());
		int random = RandomGenerator.getInstance().nextInt(0, itemsList.size()-1);
		Item item = itemsList.get(random);
		
		GCanvas item_graphic = getItemCard(item);
		item_graphic.setLocation(0, 160);
		loot_gcanvas.add(item_graphic);
		
		GLabel header = new GLabel("You won!");
		header.setFont(Main.main.font.deriveFont(40f));
		header.setLocation((Window.GAME_X / 4) - (header.getWidth() / 2) - 30,
				40);
		loot_gcanvas.add(header);
		animations.lootPrint(header);
	}

	private GCanvas getItemCard(Item item) {
		GImage image = new GImage(Tile.PICKUPITEMS_IMG_PATH+item.getType()+Tile.IMG_EXTENTION);
		image.scale(Camera.IMG_SCALE);
		image.setLocation((Window.GAME_X / 12), 10);
		
		GLabel item_name = new GLabel("Item: "+ item.getName());
		item_name.setFont(Main.main.font.deriveFont(20f));
		item_name.setColor(Color.WHITE);
		item_name.setLocation((Window.GAME_X / 6), 30);

		GLabel item_MSS = new GLabel(item.getMSS());
		item_MSS.setFont(Main.main.font.deriveFont(20f));
		item_MSS.setColor(Color.WHITE);
		item_MSS.setLocation((Window.GAME_X / 6), 50);
		

		GLabel item_STSS = new GLabel(item.getSTSS());
		item_STSS.setFont(Main.main.font.deriveFont(20f));
		item_STSS.setColor(Color.WHITE);
		item_STSS.setLocation((Window.GAME_X / 6), 70);
			

		GCanvas itemCanvas = new GCanvas();
		itemCanvas.setSize(Window.GAME_X / 2, (int) image.getHeight() + 20);
		itemCanvas.setBackground(item.getBackground());
		
		itemCanvas.add(image);
		itemCanvas.add(item_name);
		itemCanvas.add(item_MSS);
		itemCanvas.add(item_STSS);

		if (item != null)
			player.getInventory().addItem(item);
		
		return itemCanvas;
	}
	
	private void printGameOver() {
		GLabel gameover = new GLabel("GAME OVER");
		gameover.setFont(Main.main.font.deriveFont(140f));
		gameover.setLocation((Window.GAME_X / 2) - (gameover.getWidth() / 2) ,
				(Window.WINDOW_Y / 2) - (gameover.getHeight() / 2));
		gcanvas.add(gameover);
		Main.main.setState(-1);
	}

}
