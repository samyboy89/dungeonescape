package dungeonescape.combat;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import dungeonescape.Main;
import dungeonescape.ai.NPC;
import dungeonescape.character.Player;
import dungeonescape.map.Camera;
import dungeonescape.map.Map;

public class Combat {
	
	private NPC npc;
	private Player player;
	private Map map;

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
		add(getFightButton());
		add(getCancelButton());
	}
	
	private GObject getCombatBackground() {
		return new GImage("combat_back/0599.png");
	}
	
	private GObject getPlayerView() {
		GObject player_view = player.getCombatView();
		player_view.setLocation(50, (getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150);
		return player_view;
	}
	
	private GObject getFightButton() {
		GObject object;
		GRect attack = new GRect(250, 100);
		attack.setLocation(200, (getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 275);
		return attack;
	}
	
	private GObject getCancelButton() {
		GObject cancel = new GRect(250, 100);
		cancel.setLocation(200, (getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150);
		return cancel;
	}
}
