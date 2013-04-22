package dungeonescape.character;

import acm.graphics.GImage;
import dungeonescape.helper.PlayerImg;
import dungeonescape.level.Collision;
import dungeonescape.level.CollisionMisc;
import dungeonescape.level.Door;
import dungeonescape.level.Moveable;
import dungeonescape.map.Camera;

public class Player {

	private Camera camera;
	private Inventory inventory;

	private final int MALE = 0;
	private final int FEMALE = 1;

	private int gender = MALE;
	public int counter = 0;

	private DoMove move;
	private int playerX = 4, playerY = 4;
	private Direction lastmove = Direction.SOUTH;
	public int lastMoveCounter = 2;
	private Stats stats;

	public Player() {
		this.inventory = new Inventory();
		this.stats = new Stats(this, inventory);
	}

	public Player(Camera camera, Collision collision,
			CollisionMisc collisionmisc, Moveable moveable, Door door,
			Integer location) {
		this.setPlayer(camera, collision, collisionmisc, moveable, door,
				location);
		this.inventory = new Inventory();
		this.stats = new Stats(this, inventory);
	}

	public void setPlayer(Camera camera, Collision collision,
			CollisionMisc collisionmisc, Moveable moveable, Door door,
			Integer location) {
		this.camera = camera;
		this.move = new DoMove(this, collision, collisionmisc, moveable);
		int[] playersNewPosition = getPlayersNewPosition(door, location);
		setPlayerX(playersNewPosition[0]);
		setPlayerY(playersNewPosition[1]);
	}

	public int[] getPlayersNewPosition(Door door, int location) {
		if (location == -1) {
			return new int[] { playerX, playerY };
		}
		for (int i = 0; i < door.getList().size(); i++) {
			for (int j = 0; j < door.getList().get(0).size(); j++) {
				int c = door.getCell(j, i);
				if (c == location) {
					return new int[] { j, i };
				}
			}
		}
		return new int[] { playerX, playerY };
	}

	public void move(Direction direction) {
		if (lastmove.equals(direction)) {
			lastMoveCounter++;
		} else {
			lastMoveCounter = 1;
		}
		this.lastmove = direction;
		this.move.move(direction);
	}

	public int getPlayerX() {
		return this.playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public int getPlayerY() {
		return this.playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public void addToCounter() {
		counter++;
	}

	public GImage getPlayerView(int size, double minimap) {
		switch (lastmove) {
		case EAST:
			return getPlayerImageState((getPersonState() + PlayerImg.EAST),
					size, minimap);
		case NORTH:
			return getPlayerImageState((getPersonState() + PlayerImg.NORTH),
					size, minimap);
		case SOUTH:
			return getPlayerImageState((getPersonState() + PlayerImg.SOUTH),
					size, minimap);
		case WEST:
			return getPlayerImageState((getPersonState() + PlayerImg.WEST),
					size, minimap);
		default:
			return getPlayerImageState(getPersonState(), size, minimap);
		}

	}

	private GImage getPlayerImageState(int code, int size, double minimap) {
		GImage image = null;
		lastMoveCounter = (lastMoveCounter > 3 ? 1 : lastMoveCounter);
		if (size == PlayerImg.PLAYER_MAP_SIZE_SMALL) {
			image = new GImage(PlayerImg.IMG_LOCATION
					+ (code + lastMoveCounter) + PlayerImg.IMG_EXTENTION,
					(playerX * minimap) + 20, ((playerY * minimap) + 15));
			image.scale(minimap / Camera.IMG_SIZE, minimap / Camera.IMG_SIZE);
		} else {
			if (camera.getOffsetX() <= Camera.MIN_X_OFFSET
					|| camera.getOffsetY() <= Camera.MIN_Y_OFFSET
					|| camera.getOffsetX() >= camera.getMaxOffsetX()
					|| camera.getOffsetY() >= camera.getMaxOffsetY()) {
				image = new GImage(
						PlayerImg.IMG_LOCATION + (code + lastMoveCounter)
								+ PlayerImg.IMG_EXTENTION,
						Camera.IMG_SIZE * (getPlayerX() - camera.getOffsetX())
								* Camera.IMG_SCALE,
						(Camera.IMG_SIZE * (getPlayerY() - camera.getOffsetY()) * Camera.IMG_SCALE)
								- ((Camera.IMG_SIZE / 2) * Camera.IMG_SCALE));
			} else {
				image = new GImage(PlayerImg.IMG_LOCATION
						+ (code + lastMoveCounter) + PlayerImg.IMG_EXTENTION,
						Camera.IMG_SIZE * (camera.getWindowX() / 2)
								* Camera.IMG_SCALE, (Camera.IMG_SIZE
								* (camera.getWindowY() / 2) * Camera.IMG_SCALE)
								- ((Camera.IMG_SIZE / 2) * Camera.IMG_SCALE));
			}
			image.scale(Camera.IMG_SCALE);
		}
		return image;
	}

	public String getName() {
		return stats.getName();
	}

	private int getPersonState() {
		if (gender == MALE) {
			counter = (counter > 6 ? 0 : counter);
			switch (counter) {
			case 0:
				return PlayerImg.BOY_NORMAL;
			case 1:
				return PlayerImg.BOY_ARMOR;
			case 2:
				return PlayerImg.KNIGHT;
			case 3:
				return PlayerImg.DARK_CAPE;
			case 4:
				return PlayerImg.WARRIOR_LIGHT;
			case 5:
				return PlayerImg.WARRIOR_MEDIUM;
			case 6:
				return PlayerImg.WARRIOR_HEAVY;
			}
		} else if (gender == FEMALE) {
			counter = (counter > 3 ? 0 : counter);
			switch (counter) {
			case 0:
				return PlayerImg.GIRL_NORMAL;
			case 1:
				return PlayerImg.GIRL_ARMOR;
			case 2:
				return PlayerImg.KNIGHTESS;
			case 3:
				return PlayerImg.GIRL_HOUSE;
			}
		}
		return PlayerImg.BOY_NORMAL;
	}

	public void changeGender() {
		gender = (gender == MALE ? FEMALE : MALE);
	}

	public int getGender() {
		return this.gender;
	}

	public int getCharacterState() {
		return this.counter;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setCharacterState(int state) {
		this.counter = state;
	}
	
	public int getLastMovedState() {
		return this.lastMoveCounter;
	}

	public void setLastMovedState(int lastMovedState) {
		this.lastMoveCounter = lastMovedState;
	}
	
	public int getDirectionX() {
		return this.lastmove.dx();
	}
	
	public int getDirectionY() {
		return this.lastmove.dy();
	}

	public void setLastDirection(Direction direction) {
		this.lastmove = direction;
	}
}
