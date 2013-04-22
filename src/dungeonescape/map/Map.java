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
import dungeonescape.level.TopLayer;

public class Map {

	// CAMERA //
	public Camera camera;
	private int location = Levels.ROOM1;
	private int new_location = Levels.ROOM1;

	// LEVELS //
	private Ground ground;
	public Collision collision;
	public CollisionMisc collisionMisc;
	private Misc misc;
	public Moveable moveable;
	private TopLayer topLayer;
	private Player player;
	private Door door;
	NPC test;

	// REMEMBER POSITION //
	private ArrayList<Moveable> moveables;

	// OVERLAY TEXT
	public OverlayText overlayText;
	
	// MINI MAP
	MiniMap miniMap;
	double measure;

	public Map(Player player) {
		this.player = player;
		this.camera = new Camera(this);
		this.moveables = new ArrayList<Moveable>();
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

	public NPC getNPC() {
		return test;
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
		redrawViews();
	}

	private void initializeNewLevels(int level) {
		this.ground = new Ground(level);
		this.collision = new Collision(level);
		this.collisionMisc = new CollisionMisc(level);
		this.moveable = getMoveableToUse(level);
		this.misc = new Misc(level);
		this.door = new Door(level);
		this.topLayer = new TopLayer(level);
		this.miniMap = new MiniMap(this, camera, ground, collision, collisionMisc, misc, moveable);
		this.camera.setCamera();
		this.player.setPlayer(camera, collision, collisionMisc, moveable, door, location);
		this.location = new_location;
		test = new NPC(NPC_const.BALLROG, this);

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				getNPC().moveCloserToPlayer();
				
				KeyEvent ke = new KeyEvent( Main.main.getComponent(0), KeyEvent.KEY_PRESSED,   
	                    0,                          // When timeStamp  
	                    0,                          // Modifier  
	                    KeyEvent.VK_UNDEFINED,      // Key Code  
	                    KeyEvent.CHAR_UNDEFINED );  // Key Char  

				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent( ke );
				
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
		camera.getCamera();
		printLevel(ground);
		printLevel(collision);
		printLevel(collisionMisc);
		printLevel(misc);
		printLevel(moveable);
		printCharacter(player);
		printCharacter(test);
		printLevel(topLayer);
		miniMap.printMiniMap();
		if (!overlayText.isDoneShowing()) {
			overlayText.printLablesAgain();
		}
	}

	public void printCharacter(dungeonescape.player.Character player) {
		add(player.getCharacterView(PlayerImg.PLAYER_MAP_SIZE_LARGE, 0));
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

	public void moveToNextRoom() {
		int code = door.getCell(player.getCharacterX(), player.getCharacterY());
		if (door.isDoor(code)) {
			setLevelCode(code);
		}
	}
}
