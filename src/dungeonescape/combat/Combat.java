package dungeonescape.combat;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JProgressBar;
import javax.swing.UIManager;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
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
	JProgressBarColoredCustom progressBar = new JProgressBarColoredCustom(100,100);

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
		add(getCancelButton());
		printFightButton();
		
		progressBar.setValue(100);
		progressBar.setLocation(50, (getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 50);
		Main.main.getGCanvas().add(progressBar);
	}
	
	private GObject getCombatBackground() {
		return new GImage("combat_back/0599.png");
	}
	
	private GObject getPlayerView() {
		GObject player_view = player.getCombatView();
		player_view.setLocation(50, (getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150);
		return player_view;
	}
	
	private void printFightButton() {
		GImage attack = new GImage("combat_back/0499.png");
		attack.setSize(250, 70);
		attack.setLocation(200, (getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 175);
		GLabel label = new GLabel("Fight (F)");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label.setLocation(attack.getX() + (attack.getWidth() / 2) - (label.getWidth() / 2), attack.getY() + (attack.getHeight() / 2) + (label.getHeight() / 4));
		add(attack);
		add(label);
	}
	
	private GObject getCancelButton() {
		GImage cancel = new GImage("combat_back/0499.png");
		cancel.setSize(250, 70);
		cancel.setLocation(200, (getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 100);
		return cancel;
	}
	
	public void updatePlayerHealth(double progress) {
		progressBar.setValue(progressBar.getValue()+progress);
	}
}
