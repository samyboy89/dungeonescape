package dungeonescape.character;

import java.util.ArrayList;

import acm.graphics.GImage;
import dungeonescape.Main;
import dungeonescape.ai.NPC;
import dungeonescape.helper.PlayerImg;
import dungeonescape.helper.Type;
import dungeonescape.items.Item;
import dungeonescape.map.Camera;
import dungeonescape.map.Map;

public abstract class CharacterFunctions implements Character {

	private Map map;
	private Camera camera;
	private DoMove move;
	private Stats stats;
	private Inventory inventory;
	private Direction lastmove = Direction.SOUTH;

	private int key_rounds = 0;
	
	private final int MALE = 0;
	private final int FEMALE = 1;

	private int gender = MALE;
	public int counter = 0;

	private int charcterX = 4, characterY = 4;
	public int lastMoveCounter = 2;
	private boolean hasMoved = true;

	public static final int CHANGE_EXPERIENCE = 0;
	public static final int CHANGE_GENDER = 1;
	public static final int CHANGE_IMAGE_STATE = 2;
	public static final int CHANGE_LEVEL = 3;
	public static final int CHANGE_INVENTORY = 4;
	public static final int CHANGE_HEALTH = 5;
	public static final int CHANGE_LIFE = 6;
	public static final int CHANGE_KEY = 7;

	private ArrayList<PlayerStatsChangedListener> playerStatsChangedListeners;

	public CharacterFunctions() {
		playerStatsChangedListeners = new ArrayList<CharacterFunctions.PlayerStatsChangedListener>();
		setInventory(new Inventory(this));
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
		if (this.getClass().equals(Player.class)) {
			NPC npc = sameAsNPC();
			if (npc != null) {
				Main.main.setCombat(true);
				Main.main.npc = npc;
				Main.main.fireHack();
			}
		}
	}

	public NPC sameAsNPC() {
		for (NPC n : map.npcs.getNpcs()) {
			if (n.getRoom() == map.getLevelCode() && n.isAlive()) {
				if (n.getCharacterX() == getCharacterX()
						&& n.getCharacterY() == getCharacterY())
					return n;
			}
		}
		return null;
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
					(charcterX * minimap), ((characterY * minimap) - 4));
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
			for (Item i : inventory.getItemsList()) {
				if (i.isActive() && i.getParentType() == Type.CHEST) {
					switch (i.getType()) {
					case Type.CHEST_COMMON:
						return PlayerImg.BOY_ARMOR;
					case Type.CHEST_RARE:
						return PlayerImg.WARRIOR_LIGHT;
					case Type.CHEST_EPIC:
						return PlayerImg.WARRIOR_MEDIUM;
					case Type.CHEST_LEGENDARY:
						return PlayerImg.WARRIOR_HEAVY;
					}
				}
			}
		} else if (gender == FEMALE) {
			for (Item i : inventory.getItemsList()) {
				if (i.isActive() && i.getParentType() == Type.CHEST) {
					switch (i.getType()) {
					case Type.CHEST_COMMON:
						return PlayerImg.GIRL_ARMOR;
					case Type.CHEST_RARE:
						return PlayerImg.GIRL_ARMOR;
					case Type.CHEST_EPIC:
						return PlayerImg.KNIGHTESS;
					case Type.CHEST_LEGENDARY:
						return PlayerImg.KNIGHTESS;
					}
				}
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

	public double getAttack() {
		double attack = 0;
		for (Item i : inventory.getItemsList()) {
			if (i.isActive())
				attack += i.getDPS();
		}
		return getDamage() + attack;
	}

	public double getDefence() {
		double protection = 0;
		for (Item i : inventory.getItemsList()) {
			if (i.isActive())
				protection += i.getProtection();
		}
		return getProtection() + protection;
	}

	public int getLife() {
		return stats.getLife();
	}

	public void setLife(int life) {
		this.stats.setLife(life);
		setHealth(getMaxHealth());
		firePlayerChange(CHANGE_LIFE);
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
		firePlayerChange(CHANGE_EXPERIENCE);
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

	public int getMaxHealth() {
		return 100 + (getLevel() * 35);
	}

	public int getHealth() {
		return stats.getHealth();
	}

	public void setHealth(int health) {
		stats.setHealth(health);
		firePlayerChange(CHANGE_HEALTH);
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

	public double[] getLevelProgress() {
		return stats.getLevelProgress();
	}

	public void setPlayerStatsChangedListener(PlayerStatsChangedListener l) {
		if (!playerStatsChangedListeners.contains(l))
			playerStatsChangedListeners.add(l);
	}

	public void firePlayerChange(int change) {
		for (PlayerStatsChangedListener l : playerStatsChangedListeners) {
			l.change(change);
		}
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public int getKeyRounds() {
		return key_rounds;
	}

	public void setKeyRounds(int key_rounds) {
		this.key_rounds = key_rounds;
	}

	public static interface PlayerStatsChangedListener {
		void change(int change);
	}
}
