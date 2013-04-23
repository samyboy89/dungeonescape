package dungeonescape.map;

import java.awt.Color;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import dungeonescape.ai.NPC;
import dungeonescape.helper.PlayerImg;
import dungeonescape.level.Collision;
import dungeonescape.level.CollisionMisc;
import dungeonescape.level.Ground;
import dungeonescape.level.Misc;
import dungeonescape.level.Moveable;
import dungeonescape.level.PickUpItems;
import dungeonescape.level.TopLayer;
import dungeonescape.menu.RightMenu;

public class MiniMap {

	// CAMERA AND MAP REFERENCES
	private Map map;
	private RightMenu menu;
	private Camera camera;

	// LEVELS REFERENCES
	private Ground ground;
	private Collision collision;
	private CollisionMisc collisionMisc;
	private Misc misc;
	private Moveable moveable;
	private TopLayer topLayer;
	private PickUpItems items;

	// SIZE
	private int MAX_X_MINI_MAP = 14;
	private int MAX_Y_MINI_MAP = 10;

	public GCanvas gcanvas;

	public MiniMap(Map map, RightMenu menu) {
		gcanvas = new GCanvas();
		gcanvas.setBackground(Color.black);
		gcanvas.setSize(365, 261);
		gcanvas.setLocation(20, 0);

		this.map = map;
		this.menu = menu;
		this.camera = map.camera;
		this.ground = map.ground;
		this.collision = map.collision;
		this.collisionMisc = map.collisionMisc;
		this.misc = map.misc;
		this.moveable = map.moveable;
		this.topLayer = map.topLayer;
		this.items = map.pickUpItem;
	}

	public void updateMap(Map map, RightMenu menu) {
		this.map = map;
		this.menu = menu;
		this.camera = map.camera;
		this.ground = map.ground;
		this.collision = map.collision;
		this.collisionMisc = map.collisionMisc;
		this.misc = map.misc;
		this.moveable = map.moveable;
		this.topLayer = map.topLayer;
		this.items = map.pickUpItem;
	}

	private void add(GObject gobject) {
		gcanvas.add(gobject);
	}

	private void removeMiniMap() {
		// menu.gcanvas.remove(gcanvas);
		gcanvas.removeAll();
	}

	public void addMiniMap() {
		menu.gcanvas.add(gcanvas);
	}

	private double getMiniMapMeasurments() {
			int y = MAX_Y_MINI_MAP * 26;
			return y / camera.getMapY();
	}

	private void drawCurrentCameraView(double measure) {
		GRect rec = new GRect(camera.getMaxOffsetX() * measure,
				camera.getMaxOffsetY() * measure);
		rec.setLocation((camera.getOffsetX() * measure),
				(camera.getOffsetY() * measure));
		rec.setSize((camera.getWindowX() * measure),
				(camera.getWindowY() * measure));
		rec.setColor(Color.RED);
		add(rec);
	}

	public void printMiniMap() {
		removeMiniMap();
		double measure = getMiniMapMeasurments();
		GImage image = null;
		for (int i = 0; i < map.getMapX(); i++) {
			for (int j = 0; j < map.getMapY(); j++) {
				image = ground.printMiniMap(j, i, measure);
				if (image != null)
					add(image);
				image = collision.printMiniMap(j, i, measure);
				if (image != null)
					add(image);
				image = collisionMisc.printMiniMap(j, i, measure);
				if (image != null)
					add(image);
				image = misc.printMiniMap(j, i, measure);
				if (image != null)
					add(image);
				image = moveable.printMiniMap(j, i, measure);
				if (image != null)
					add(image);
				image = items.printMiniMap(j, i, measure);
				if (image != null)
					add(image);
			}
		}
		image = map.getPlayer().getCharacterView(
				PlayerImg.PLAYER_MAP_SIZE_SMALL, measure);
		add(image);
		for (NPC npc : map.npcs) {
			if (npc.getRoom() == map.getLevelCode()) {
				image = npc.getCharacterView(PlayerImg.PLAYER_MAP_SIZE_SMALL,
						measure);
				add(image);
			}
		}
		for (int i = 0; i < map.getMapX(); i++) {
			for (int j = 0; j < map.getMapY(); j++) {
				image = topLayer.printMiniMap(j, i, measure);
				if (image != null)
					add(image);
			}
		}
		drawCurrentCameraView(measure);
	}
}
