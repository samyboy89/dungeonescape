package dungeonescape.player;

import acm.graphics.GImage;
import dungeonescape.character.Direction;
import dungeonescape.character.DoMove;
import dungeonescape.character.Inventory;
import dungeonescape.character.Stats;
import dungeonescape.helper.Game;
import dungeonescape.helper.PlayerImg;
import dungeonescape.map.Camera;

public abstract class CharacterFunctions implements Character {

	private Camera camera;
	private DoMove move;
	private Stats stats;
	private Inventory inventory;
	private Direction lastmove = Direction.SOUTH;

	private final int MALE = 0;
	private final int FEMALE = 1;

	private int gender = MALE;
	public int counter = 0;

	private int charcterX = 4, characterY = 4;
	public int lastMoveCounter = 2;
	private boolean hasMoved = true;

	public CharacterFunctions() {
		setInventory(new Inventory());
		setStats(new Stats(this, getInventory()));
	}

	public void move(Direction direction) {
		setMoved(true);
		if (lastmove.equals(direction)) {
			lastMoveCounter++;
		} else {
			lastMoveCounter = 1;
		}
		this.lastmove = direction;
		this.move.move(direction);
	}

	public int getCharacterX() {
		return this.charcterX;
	}

	public void setCharacterX(int playerX) {
		this.charcterX = playerX;
	}

	public int getCharacterY() {
		return this.characterY;
	}

	public void setCharacterY(int playerY) {
		this.characterY = playerY;
	}

	public void addToCounter() {
		counter++;
	}

	public GImage getCharacterView(int size, double minimap) {
		switch (lastmove) {
		case EAST:
			return getCharacterImageState(
					(getCharacterState() + PlayerImg.EAST), size, minimap);
		case NORTH:
			return getCharacterImageState(
					(getCharacterState() + PlayerImg.NORTH), size, minimap);
		case SOUTH:
			return getCharacterImageState(
					(getCharacterState() + PlayerImg.SOUTH), size, minimap);
		case WEST:
			return getCharacterImageState(
					(getCharacterState() + PlayerImg.WEST), size, minimap);
		default:
			return getCharacterImageState(getCharacterState(), size, minimap);
		}

	}

	public GImage getCharacterImageState(int code, int size, double minimap) {
		GImage image = null;
		lastMoveCounter = (lastMoveCounter > 3 ? 1 : lastMoveCounter);
		if (size == PlayerImg.PLAYER_MAP_SIZE_SMALL) {
			image = new GImage(PlayerImg.IMG_LOCATION
					+ (code + lastMoveCounter) + PlayerImg.IMG_EXTENTION,
					(charcterX * minimap) + Game.MenuSizeAway, ((characterY * minimap) + 8));
			image.scale(minimap / Camera.IMG_SIZE, minimap / Camera.IMG_SIZE);
		} else {
			if (camera.getOffsetX() <= Camera.MIN_X_OFFSET
					|| camera.getOffsetY() <= Camera.MIN_Y_OFFSET
					|| camera.getOffsetX() >= camera.getMaxOffsetX()
					|| camera.getOffsetY() >= camera.getMaxOffsetY()) {
				image = new GImage(
						PlayerImg.IMG_LOCATION + (code + lastMoveCounter)
								+ PlayerImg.IMG_EXTENTION,
						Camera.IMG_SIZE
								* (getCharacterX() - camera.getOffsetX())
								* Camera.IMG_SCALE,
						(Camera.IMG_SIZE
								* (getCharacterY() - camera.getOffsetY()) * Camera.IMG_SCALE)
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

	public int getCharacterState() {
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

	public GImage getCombatView() {
		GImage image = new GImage(PlayerImg.IMG_LOCATION + "0001"
				+ PlayerImg.IMG_EXTENTION);
		image.scale(Camera.IMG_SCALE);
		return image;
	}

	public void changeGender() {
		gender = (gender == MALE ? FEMALE : MALE);
	}

	public int getGender() {
		return this.gender;
	}

	public int getCounterState() {
		return this.counter;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setCounterState(int state) {
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

	public boolean hasMoved() {
		return hasMoved;
	}

	public void setMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

	public int getExperience() {
		return stats.getExperience();
	}

	public void addExperience(int exp) {
		stats.addExperience(exp);
	}

	public String getName() {
		return stats.getName();
	}

	public void setName(String name) {
		stats.setName(name);
	}

	public int getDamage() {
		return stats.getDamage();
	}

	public void setDamage(int dmg) {
		stats.setDamage(dmg);
	}

	public int getGold() {
		return stats.getGold();
	}

	public void setGold(int gold) {
		stats.setGold(gold);
	}

	public int getHealth() {
		return stats.getHealth();
	}

	public void setHealth(int health) {
		stats.setHealth(health);
	}

	public int getProtection() {
		return stats.getProtection();
	}

	public void setProtection(int protecion) {
		stats.setProtection(protecion);
	}

	public int getLevel() {
		return stats.getLevel();
	}

	public void setLevel(int level) {
		stats.setLevel(level);
	}

	public DoMove getMove() {
		return move;
	}

	public void setMove(DoMove move) {
		this.move = move;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
