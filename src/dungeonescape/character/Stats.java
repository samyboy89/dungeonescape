package dungeonescape.character;

public class Stats {

	// private Player player;
	// private Inventory inventory;
	private String name;
	private int gold;
	private int experience;
	private int level;
	private int health;
	private int damage;
	private int protection;
	
	
	public Stats(Player player, Inventory inventory) {
		// this.player = player;
		// this.inventory = inventory;
	}

	public String getName() {
		return this.name;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getProtection() {
		return protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}
	
}
