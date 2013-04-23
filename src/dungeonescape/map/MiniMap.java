package dungeonescape.map;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GRect;
import dungeonescape.ai.NPC;
import dungeonescape.helper.Game;
import dungeonescape.helper.PlayerImg;
import dungeonescape.level.Collision;
import dungeonescape.level.CollisionMisc;
import dungeonescape.level.Ground;
import dungeonescape.level.Misc;
import dungeonescape.level.Moveable;
import dungeonescape.level.PickUpItems;
import dungeonescape.level.TopLayer;

public class MiniMap {

	// CAMERA AND MAP REFERENCES
	private Camera camera;
	private Map map;

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

	private double measure = 0;

	public MiniMap(Map map, Camera camera, Ground ground, Collision collision,
			CollisionMisc collisionMisc, Misc misc, Moveable moveable,
			TopLayer topLayer, PickUpItems items) {
		this.camera = camera;
		this.map = map;
		this.ground = ground;
		this.collision = collision;
		this.collisionMisc = collisionMisc;
		this.misc = misc;
		this.moveable = moveable;
		this.topLayer = topLayer;
		this.items = items;
	}

	private double getMiniMapMeasurments() {
		if (measure != 0) {
			return this.measure;
		} else {
			int x = MAX_X_MINI_MAP * 26;
			int y = MAX_Y_MINI_MAP * 26;
			if (camera.getMapX() > MAX_X_MINI_MAP) {
				measure = x / camera.getMapX();
			}
			if (camera.getMapY() > MAX_Y_MINI_MAP) {
				if (camera.getMapY() > camera.getMapX()) {
					return y / camera.getMapY();
				}
				return measure;
			}
			return 26;
		}
	}

	private void drawCurrentCameraView(double measure) {
		GRect rec = new GRect(camera.getMaxOffsetX() * measure,
				camera.getMaxOffsetY() * measure);
		rec.setLocation((camera.getOffsetX() * measure) + Game.MenuSizeAway,
				(camera.getOffsetY() * measure) + 20);
		rec.setSize((camera.getWindowX() * measure),
				(camera.getWindowY() * measure));
		rec.setColor(Color.RED);
		map.add(rec);
	}

	public void printMiniMap() {
		double measure = getMiniMapMeasurments();
		GImage image = null;
		for (int i = 0; i < map.getMapX(); i++) {
			for (int j = 0; j < map.getMapY(); j++) {
				image = ground.printMiniMap(j, i, measure);
				if (image != null)
					map.add(image);
				image = collision.printMiniMap(j, i, measure);
				if (image != null)
					map.add(image);
				image = collisionMisc.printMiniMap(j, i, measure);
				if (image != null)
					map.add(image);
				image = misc.printMiniMap(j, i, measure);
				if (image != null)
					map.add(image);
				image = moveable.printMiniMap(j, i, measure);
				if (image != null)
					map.add(image);
				image = items.printMiniMap(j, i, measure);
				if (image != null)
					map.add(image);
			}
		}
		image = map.getPlayer().getCharacterView(
				PlayerImg.PLAYER_MAP_SIZE_SMALL, measure);
		map.add(image);
		for (NPC npc : map.npcs) {
			if (npc.getRoom() == map.getLevelCode()) {
				image = npc.getCharacterView(PlayerImg.PLAYER_MAP_SIZE_SMALL,
						measure);
				map.add(image);
			}
		}
		for (int i = 0; i < map.getMapX(); i++) {
			for (int j = 0; j < map.getMapY(); j++) {
				image = topLayer.printMiniMap(j, i, measure);
				if (image != null)
					map.add(image);
			}
		}
		drawCurrentCameraView(measure);
	}
}
