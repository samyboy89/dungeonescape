package dungeonescape.combat;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

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
import dungeonescape.helper.PlayerImg;
import dungeonescape.helper.Tile;
import dungeonescape.helper.Window;
import dungeonescape.items.Item;
import dungeonescape.map.Camera;
import dungeonescape.map.Map;

public class Combat {

	public GCanvas gcanvas = new GCanvas();
	private NPC npc;
	private Player player;
	private Map map;
	private Animations animations;

	JProgressBarColoredPlayer playerHealth = new JProgressBarColoredPlayer(100,
			100);
	JProgressBarColoredNPC npcHealth = new JProgressBarColoredNPC(100, 100);

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

	private static final int NO_BAR_UPDATE = -1;
	
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
						} else {
							// DEAD - LOOSE LIFE
						}
						break;
					case NPCS_TURN:
						updateNPCHealth(NO_BAR_UPDATE);
						if (Combat.this.npc.getHealth() > 0) {
							npcAttack();
							state = PLAYERS_TURN;
						} else {
							// KILLED CREEP _ LOOT AND QUIT VIEW
							// REMOVE CREEP FROM MAP LIST
						}
						break;
					}
					break;
				case Animations.MOVE:

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
		case KeyEvent.VK_UP:
			updatePlayerHealth(+10);
			break;
		case KeyEvent.VK_DOWN:
			updatePlayerHealth(-10);
			break;
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_RIGHT:
			break;
		case KeyEvent.VK_SPACE:
			break;
		case KeyEvent.VK_Q:
			Main.main.setState(Main.MAP);
			Main.main.setCombat(false);
			Main.main.getGCanvas().add(map.gcanvas);
			Main.main.getGCanvas().remove(gcanvas);
			map.redrawViews();
			Main.main.combat = null;
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

	public void lootNPC() {
		int random = RandomGenerator.getInstance().nextInt(0, 4);
		Item item = npc.getInventory().getItem(random);
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
		printCancelButton();
		printFightButton();
		initializeHealthBars();
		printPlayerStats();
		printNPCStats();
		printPlayerImage();
		printNPCImage();
	}

	private void initializeHealthBars() {
		playerHealth.setValue(player.getHealth());
		playerHealth.setLocation(50, (getCamera().getWindowY()
				* Camera.IMG_SIZE * Camera.IMG_SCALE) - 60);
		playerHealth.setSize(98, 20);
		gcanvas.add(playerHealth);

		npcHealth.setValue(npc.getHealth());
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
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		label.setColor(Color.white);
		label.setLocation(
				50,
				(getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 165);
		add(label);
	}

	private void printNPCStats() {
		GLabel label = new GLabel("Lvl.: " + npc.getLevel());
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		label.setColor(Color.white);
		label.setLocation(
				(getCamera().getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150,
				44);
		add(label);
	}

	private void printFightButton() {
		GImage attack = new GImage("combat_back/0499.png");
		attack.setSize(250, 60);
		attack.setLocation(
				200,
				(getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 155);
		GLabel label = new GLabel("Fight (F)");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label.setLocation(
				attack.getX() + (attack.getWidth() / 2)
						- (label.getWidth() / 2),
				attack.getY() + (attack.getHeight() / 2)
						+ (label.getHeight() / 4));
		add(attack);
		add(label);
	}

	private void printCancelButton() {
		GImage cancel = new GImage("combat_back/0499.png");
		cancel.setSize(250, 60);
		cancel.setLocation(
				200,
				(getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 90);
		GLabel label = new GLabel("Use Potion (P)");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label.setLocation(
				cancel.getX() + (cancel.getWidth() / 2)
						- (label.getWidth() / 2),
				cancel.getY() + (cancel.getHeight() / 2)
						+ (label.getHeight() / 4));
		add(cancel);
		add(label);
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
		GImage image = new GImage(Tile.PICKUPITEMS_IMG_PATH + "9936"
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

}
