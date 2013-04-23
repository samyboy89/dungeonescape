package dungeonescape.combat;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GCanvas;
import acm.graphics.GDimension;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import dungeonescape.Main;
import dungeonescape.ai.NPC;
import dungeonescape.character.Player;
import dungeonescape.components.JProgressBarColoredCustom;
import dungeonescape.helper.PlayerImg;
import dungeonescape.map.Camera;
import dungeonescape.map.Map;

public class Combat {

	private GCanvas mainCanvas;
	private NPC npc;
	private Player player;
	private Map map;
	private Animations animations;
	
	
	JProgressBarColoredCustom playerHealth = new JProgressBarColoredCustom(100,
			100);
	JProgressBarColoredCustom npcHealth = new JProgressBarColoredCustom(100,
			100);

	// NPC
	private GImage npc_char;
	private GDimension npc_size;
	private GPoint npc_location;

	public Combat(Player player, Map map, NPC npc) {
		Main.setState(Main.COMBAT);
		this.animations = new Animations(map);
		this.player = player;
		this.map = map;
		this.npc = npc;
		printViews();
	}

	private void add(GObject gobject) {
		mainCanvas.add(gobject);
	}

	public void remove() {
		Main.main.remove(mainCanvas);
	}

	private Camera getCamera() {
		return this.map.camera;
	}

	private void printViews() {
		// removeAll();
		mainCanvas = new GCanvas();
		mainCanvas.setSize(896, 640);
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
		Main.main.getGCanvas().add(mainCanvas);
	}

	private void initializeHealthBars() {
		playerHealth.setValue(100);
		playerHealth.setLocation(50, (getCamera().getWindowY()
				* Camera.IMG_SIZE * Camera.IMG_SCALE) - 60);
		playerHealth.setSize(98, 20);
		mainCanvas.add(playerHealth);

		npcHealth.setValue(80);
		npcHealth
				.setLocation(
						(getCamera().getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150,
						150);
		npcHealth.setSize(98, 20);
		mainCanvas.add(npcHealth);
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
		GImage player_view = player.getCharacterImageState(
				(player.getCharacterState() + PlayerImg.EAST),
				PlayerImg.PLAYER_MAP_SIZE_LARGE, 0);
		player_view.scale(1.5);
		player_view.setLocation(150, ((getCamera().getWindowY()
				* Camera.IMG_SIZE * Camera.IMG_SCALE)) / 3);
		add(player_view);
	}

	private void printNPCImage() {
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

	public void updatePlayerHealth(double progress) {
		playerHealth.setValue(playerHealth.getValue() + progress);
	}

	public void updateNPCHealth(double progress) {
		npcHealth.setValue(npcHealth.getValue() + progress);
	}

	public void scaleUnScaleGObject() {
		animations.scaleUpScaleDown(npc_char, npc_location, npc_size);
	}
	
	public void moveForwardAndBackGObject() {
		animations.moveForwardAndBack(npc_char, npc_location, npc_size, Animations.LEFT);
	}
		
}
