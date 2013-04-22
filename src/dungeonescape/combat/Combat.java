package dungeonescape.combat;

import java.awt.Color;

import acm.graphics.GObject;
import dungeonescape.Main;
import dungeonescape.ai.AI;
import dungeonescape.character.Player;
import dungeonescape.map.Camera;
import dungeonescape.map.Map;

public class Combat {
	
	private AI npc;
	private Player player;
	private Map map;

	public Combat(Player player, Map map, AI npc) {
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
		GObject player_view = player.getCombatView();
		player_view.setLocation(50, (getCamera().getWindowY() * Camera.IMG_SIZE * Camera.IMG_SCALE) - 150);
		add(player_view);
	}
	
	
}
