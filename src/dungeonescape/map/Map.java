package dungeonescape.map;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GImage;
import acm.graphics.GObject;
import dungeonescape.Main;
import dungeonescape.ai.NPC;
import dungeonescape.character.Player;
import dungeonescape.helper.Levels;
import dungeonescape.helper.NPC_const;
import dungeonescape.helper.PlayerImg;
import dungeonescape.level.Collision;
import dungeonescape.level.CollisionMisc;
import dungeonescape.level.Door;
import dungeonescape.level.Ground;
import dungeonescape.level.Level;
import dungeonescape.level.Misc;
import dungeonescape.level.Moveable;
import dungeonescape.level.PickUpItems;
import dungeonescape.level.TopLayer;

public class Map {

	// CAMERA //
	public Camera camera;
	private int location = Levels.ROOMA;
	private int new_location = Levels.ROOMA;

	// LEVELS //
	public Ground ground;
	public Collision collision;
	public CollisionMisc collisionMisc;
	public Misc misc;
	public Moveable moveable;
	public TopLayer topLayer;
	public Door door;
	public PickUpItems pickUpItem;

	// CHARACTERS
	public Player player;
	public NPC test;
	public ArrayList<NPC> npcs;

	// REMEMBER POSITION //
	private ArrayList<Moveable> moveables;
	private ArrayList<PickUpItems> pickUpItems;

	// PLAYER GRAPHIC //
	private GObject player_graphic;

	// OVERLAY TEXT
	public OverlayText overlayText;

	// MINI MAP
	MiniMap miniMap;
	double measure;
	
	// CHANGED ROOM LISTENER
	private ArrayList<RoomChangeListener> roomChangeListeners;
	
	// TIMER
	Timer timer;

	public Map(Player player) {
		this.player = player;
		this.camera = new Camera(this);
		
		this.roomChangeListeners = new ArrayList<Map.RoomChangeListener>();
		this.npcs = new ArrayList<NPC>();
		
		this.moveables = new ArrayList<Moveable>();
		this.pickUpItems = new ArrayList<PickUpItems>();
		this.overlayText = new OverlayText(this);

		Main.main.setBackground(Color.BLACK);
		Main.main.setSize(camera.getWindowX() * Camera.IMG_SIZE
				* Camera.IMG_SCALE, camera.getWindowY() * Camera.IMG_SIZE
				* Camera.IMG_SCALE);
		printMap();
	}

	public void add(GObject object) {
		Main.main.add(object);
	}

	public void remove(GObject object) {
		Main.main.remove(object);
	}

	public void removeAll() {
		Main.main.removeAll();
	}

	public Player getPlayer() {
		return this.player;
	}

	public void restartCurrentLevel() {
		printMap();
	}

	public int getLevelCode() {
		return location;
	}

	public void resetLevelCode() {
		this.location = -1;
	}

	public void setLevelCode(int location) {
		this.new_location = location;
		printMap();
	}

	public int getMapX() {
		return ground.getSizeX();
	}

	public int getMapY() {
		return ground.getSizeY();
	}

	public void printMap() {
		overlayText.printLables();
		initializeNewLevels(this.new_location);
		fireRoomChangeListener(this.new_location);
		redrawViews();
	}

	private void initializeNewLevels(int level) {
		this.ground = new Ground(level);
		this.collision = new Collision(level);
		this.collisionMisc = new CollisionMisc(level);
		this.moveable = getMoveableToUse(level);
		this.misc = new Misc(level);
		this.door = new Door(level);
		this.pickUpItem = getPickUpItemsToUse(level);
		this.topLayer = new TopLayer(level);
		this.miniMap = new MiniMap(this, camera, ground, collision,
				collisionMisc, misc, moveable, topLayer, pickUpItem);
		this.camera.setCamera();
		this.player.setPlayer(camera, collision, collisionMisc, moveable, door,
				location);
		addNewNPCs();
		this.location = new_location;
		startTimerForLevel();
	}

	private void addNewNPCs() {
		this.test = new NPC(NPC_const.BALLROG, this);
		if (npcs.size() > 0) {
			for (NPC npc : npcs) {
				if (npc.getRoom() == new_location && npc.getID() != test.getID()) {
					npcs.add(test);
				}
			}
		} else {
			npcs.add(test);
		}
	}

	private void startTimerForLevel() {
		if (timer != null)
			timer.cancel();
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				for (NPC npc : npcs) {
					if (npc.getRoom() == new_location)
						npc.moveCloserToPlayer();
				}

				KeyEvent ke = new KeyEvent(Main.main.getComponent(0),
						KeyEvent.KEY_PRESSED, 0, // When timeStamp
						0, // Modifier
						KeyEvent.VK_UNDEFINED, // Key Code
						KeyEvent.CHAR_UNDEFINED); // Key Char

				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(ke);

			}
		}, 500, 500);
	}
	
	private void printLevel(Level level) {
		GImage image = null;
		int position_x = 0;
		int position_y = 0;
		for (int i = camera.getOffsetY(); i < camera.getVisibleTilesY(); i++) {
			position_x = 0;
			for (int j = camera.getOffsetX(); j < camera.getVisibleTilesX(); j++) {
				image = level.printMap(i, j, position_x, position_y);
				if (image != null) {
					add(image);
				}
				position_x++;
			}
			position_y++;
		}
	}

	public void redrawViews() {
		removeAll();
		Main.main.setBackground(Color.BLACK);
		camera.getCamera();
		printLevel(ground);
		printLevel(collision);
		printLevel(collisionMisc);
		printLevel(misc);
		printLevel(moveable);
		printLevel(pickUpItem);
		printCharacter(player);
		for (NPC npc : npcs) {
			if (npc.getRoom() == this.location)
				printCharacter(npc);
		}
		printLevel(topLayer);
		miniMap.printMiniMap();
		if (!overlayText.isDoneShowing()) {
			overlayText.printLablesAgain();
		}
	}

	public void printCharacter(dungeonescape.player.Character player) {
		player_graphic = player.getCharacterView(
				PlayerImg.PLAYER_MAP_SIZE_LARGE, 0);
		add(player_graphic);
	}

	private Moveable getMoveableToUse(int level) {
		for (Moveable m : moveables)
			if (m.getLevelCode() == level)
				return m;
		Moveable moveable = new Moveable(level);
		moveable.setLevelCode(level);
		moveables.add(moveable);
		return moveable;
	}

	private PickUpItems getPickUpItemsToUse(int level) {
		for (PickUpItems i : pickUpItems)
			if (i.getLevelCode() == level)
				return i;
		PickUpItems items = new PickUpItems(level);
		items.setLevelCode(level);
		pickUpItems.add(items);
		return items;
	}

	private void findAllNPC(int code) {

	}

	public void moveToNextRoom() {
		int code = door.getCell(player.getCharacterX(), player.getCharacterY());
		if (door.isDoor(code)) {
			timer.cancel();
			timer = null;
			setLevelCode(code);
		}
	}
	
	private void fireRoomChangeListener(int level) {
		for (RoomChangeListener l : roomChangeListeners) {
			l.onChanged(level);
		}
	}
	
	public void setRoomChangeListener(RoomChangeListener l) {
		if (!roomChangeListeners.contains(l)) {
			roomChangeListeners.add(l);
		}
	}
	
	public static interface RoomChangeListener {
		void onChanged(int level);
	}
}
