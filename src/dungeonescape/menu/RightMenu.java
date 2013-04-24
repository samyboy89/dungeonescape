package dungeonescape.menu;

import java.awt.Color;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import dungeonescape.Main;
import dungeonescape.character.CharacterFunctions;
import dungeonescape.character.CharacterFunctions.PlayerStatsChangedListener;
import dungeonescape.character.Player;
import dungeonescape.components.JProgressBarColoredExperience;
import dungeonescape.components.JProgressBarColoredPlayer;
import dungeonescape.helper.Tile;
import dungeonescape.helper.Window;
import dungeonescape.items.Item;
import dungeonescape.map.Map;
import dungeonescape.map.Map.MapChangeListener;
import dungeonescape.map.MiniMap;

public class RightMenu {

	public InventoryGrid grid;
	private Map map;
	Player player;

	JProgressBarColoredExperience exp;
	GLabel xp_label;
	GLabel lvl_label;

	JProgressBarColoredPlayer health;
	GLabel health_label;

	GLabel attack;
	GLabel defence;
	
	GCanvas hearts;
	GCanvas keys;
	
	public GCanvas gcanvas;

	// MINI MAP
	MiniMap miniMap;
	double measure;

	public RightMenu(final Map map) {
		this.gcanvas = new GCanvas();
		this.gcanvas.setSize(Window.GAME_X, Window.WINDOW_Y);
		this.gcanvas.setLocation(896, 0);
		this.gcanvas.setBackground(Color.BLACK);
		this.map = map;
		this.player = map.player;
		this.miniMap = new MiniMap(map, this);
		this.grid = new InventoryGrid(this);
		this.map.setMapChangeListener(new MapChangeListener() {

			@Override
			public void onChanged(int state) {
				switch (state) {
				case Map.INIT_MAP:
					miniMap.updateMap(map, RightMenu.this);
					break;
				case Map.PRINT_RIGHT_SIDE:
					printMiniMap();
					break;
				}
			}
		});
		gcanvas.add(miniMap.gcanvas);
		initializePlayerStats();
	}

	public void add(GObject gobject) {
		gcanvas.add(gobject);
	}

	public void printMiniMap() {
		if (miniMap != null)
			miniMap.printMiniMap();
	}

	private void initializePlayerStats() {
		exp = new JProgressBarColoredExperience(100, 100);
		exp.setValue(player.getLevelProgress()[0]);
		exp.setSize(240, 15);
		exp.setLocation(140, 295);
		gcanvas.add(exp);

		lvl_label = new GLabel("Lvl.: " + player.getLevel());
		lvl_label.setFont(Main.main.font.deriveFont(10f));
		lvl_label.setColor(Color.white);
		lvl_label.setLocation(Window.MENU_X - 30 - lvl_label.getWidth(), 290);
		gcanvas.add(lvl_label);

		health = new JProgressBarColoredPlayer(player.getMaxHealth(),
				player.getHealth());
		health.setValue(player.getHealth());
		health.setMaximumValue(player.getMaxHealth());
		health.setSize(240, 15);
		health.setLocation(140, 325);
		gcanvas.add(health);

		xp_label = new GLabel("XP: " + player.getExperience());
		xp_label.setFont(Main.main.font.deriveFont(10f));
		xp_label.setColor(Color.white);
		xp_label.setLocation(150, 290);
		gcanvas.add(xp_label);

		health_label = new GLabel("Health: " + player.getHealth() + " / "
				+ player.getMaxHealth());
		health_label.setFont(Main.main.font.deriveFont(10f));
		health_label.setColor(Color.white);
		health_label.setLocation(150, 320);
		gcanvas.add(health_label);

		GObject player_view = player.getCombatView();
		player_view.setLocation(20, 290);
		gcanvas.add(player_view);
		
		GLabel name = new GLabel(player.getName().toUpperCase());
		name.setFont(Main.main.font.deriveFont(14f));
		name.setColor(Color.white);
		name.setLocation(25, 380);
		gcanvas.add(name);

		attack = new GLabel("Attack: " + player.getDamage() + " ( "
				+ (player.getAttack() - player.getDamage()) + "+ ) ");
		attack.setFont(Main.main.font.deriveFont(10f));
		attack.setColor(Color.white);
		attack.setLocation(150, 355);
		gcanvas.add(attack);
		
		defence = new GLabel("Protection: " + player.getProtection());
		defence.setFont(Main.main.font.deriveFont(10f));
		defence.setColor(Color.white);
		defence.setLocation(150, 370);
		gcanvas.add(defence);
		
		hearts = new GCanvas();
		hearts.setBackground(Color.BLACK);
		hearts.setSize(18*player.getLife(), 15);
		hearts.setLocation(320, 345);
		for (int i = 0; i < player.getLife(); i++) {
			GImage heart = new GImage("player/heart.png");
			heart.setSize(16, 16);
			heart.setLocation(i*18, 0);
			hearts.add(heart);
		}
		gcanvas.add(hearts);

		keys = new GCanvas();
		keys.setBackground(Color.BLACK);
		keys.setSize(18*player.getLife(), 15);
		keys.setLocation(320, 370);
		int counter = 0;
		for (Item i : player.getInventory().getKeysList()) {
			GImage key = new GImage(Tile.PICKUPITEMS_IMG_PATH + i.getType() + Tile.IMG_EXTENTION);
			key.setSize(16, 16);
			key.setLocation(counter*18, 0);
			keys.add(key);
			counter ++;
		}
		gcanvas.add(keys);
		
		this.player
				.setPlayerStatsChangedListener(new PlayerStatsChangedListener() {

					@Override
					public void change(int change) {
						switch (change) {
						case CharacterFunctions.CHANGE_EXPERIENCE:
							updateExperience();
							break;
						case CharacterFunctions.CHANGE_LEVEL:
							updateLevel();
							updateHealth();
							updateAttack();
							updateProtection();
							break;
						case CharacterFunctions.CHANGE_HEALTH:
							updateHealth();
							break;
						case CharacterFunctions.CHANGE_INVENTORY:
							grid.updateInventoryGrid();
							updateAttack();
							updateProtection();
							break;
						case CharacterFunctions.CHANGE_LIFE:
							updateLife();
							break;
						case CharacterFunctions.CHANGE_KEY:
							updateKeys();
							break;
						}
					}
				});
		
		grid.printPlayerInventory();
	}

	private void updateExperience() {
		xp_label.setLabel("XP: " + player.getExperience());
		exp.setValue(player.getLevelProgress()[0]);
	}

	private void updateHealth() {
		health_label.setLabel("Health: " + player.getHealth() + " / "
				+ player.getMaxHealth());
		health.setValue(player.getHealth());
		health.setMaximumValue(player.getMaxHealth());
	}

	private void updateLevel() {
		player.setHealth(player.getMaxHealth());
		lvl_label.setLabel("Lvl.: " + player.getLevel());
	}
	
	private void updateAttack() {
		attack.setLabel("Attack: " + player.getDamage() + " ( "
				+ (player.getAttack() - player.getDamage()) + "+ ) ");
	}

	
	private void updateProtection() {
		defence.setLabel("Protection: " + player.getDefence());
	}
	
	private void updateLife() {
		hearts.removeAll();
		for (int i = 0; i < player.getLife(); i++) {
			GImage heart = new GImage("player/heart.png");
			heart.setSize(16, 16);
			heart.setLocation(i*18, 0);
			hearts.add(heart);
		}
	}
	
	private void updateKeys() {
		keys.removeAll();
		int counter = 0;
		for (Item i : player.getInventory().getKeysList()) {
			GImage key = new GImage(Tile.PICKUPITEMS_IMG_PATH + i.getType() + Tile.IMG_EXTENTION);
			key.setSize(16, 16);
			key.setLocation(counter*18, 0);
			keys.add(key);
			counter ++;
		}
	}



}
