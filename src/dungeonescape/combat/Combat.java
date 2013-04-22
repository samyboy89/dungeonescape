package dungeonescape.combat;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import dungeonescape.Main;
import dungeonescape.ai.NPC;
import dungeonescape.character.Player;
import dungeonescape.components.JProgressBarColoredCustom;
import dungeonescape.map.Camera;
import dungeonescape.map.Map;

public class Combat {

	private NPC npc;
	private Player player;
	private Map map;
	JProgressBarColoredCustom playerHealth = new JProgressBarColoredCustom(100,
			100);
	JProgressBarColoredCustom npcHealth = new JProgressBarColoredCustom(100,
			100);

	public Combat(Player player, Map map, NPC npc) {
		Main.main.setKeyState(Main.KEY_COMBAT);
		this.player = player;
		this.map = map;
		this.npc = npc;
		printViews();
	}

	private void add(GObject gobject) {
		Main.main.add(gobject);
	}

	private void remove(GObject gobject) {
		Main.main.remove(gobject);
	}

	private void removeAll() {
		Main.main.removeAll();
	}

	private Camera getCamera() {
		return this.map.camera;
	}

	private void printViews() {
		removeAll();
		Main.main.setBackground(Color.WHITE);

		add(getCombatBackground());
		add(getPlayerView());
		add(getNPCView());
		printCancelButton();
		printFightButton();
		initializeHealthBars();
		printPlayerStats();
		printNPCStats();
	}

	private void initializeHealthBars() {
		playerHealth.setValue(100);
		playerHealth.setLocation(50, (getCamera().getWindowY()
				* Camera.IMG_SIZE * Camera.IMG_SCALE) - 60);
		playerHealth.setSize(98, 20);
		Main.main.getGCanvas().add(playerHealth);

		npcHealth.setValue(80);
		npcHealth
				.setLocation(
						(getCamera().getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150,
						150);
		npcHealth.setSize(98, 20);
		Main.main.getGCanvas().add(npcHealth);
	}

	private GObject getCombatBackground() {
		return new GImage("combat_back/0599.png");
	}

	private GObject getPlayerView() {
		GObject player_view = player.getCombatView();
		player_view
				.setLocation(
						50,
						(getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 160);
		return player_view;
	}

	private GObject getNPCView() {
		// GObject player_view = npc.getCombatView();
		GImage npc_view = new GImage("combat_back/0598.png");
		npc_view.scale(Camera.IMG_SCALE);
		npc_view.setLocation(
				(getCamera().getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150,
				50);
		return npc_view;
	}
	
	private void printPlayerStats() {
		GLabel label = new GLabel("Lvl.: " + player.getLevel());
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		label.setColor(Color.white);
		label.setLocation(50, (getCamera().getWindowY()
				* Camera.IMG_SIZE * Camera.IMG_SCALE) - 165);
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

	public void updatePlayerHealth(double progress) {
		playerHealth.setValue(playerHealth.getValue() + progress);
	}

	public void updateNPCHealth(double progress) {
		npcHealth.setValue(npcHealth.getValue() + progress);
	}

}
