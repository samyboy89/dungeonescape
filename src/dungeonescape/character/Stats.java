package dungeonescape.character;

public class Stats {

	private dungeonescape.character.Character character;
	private Inventory inventory;
	private Experience exp;
	
	private String name;
	private int gold;
	private int experience;
	private int level;
	private int health;
	private int damage;
	private int protection;
	private int life;

	public Stats(dungeonescape.character.Character character, Inventory inventory) {
		this.character = character;
		this.inventory = inventory;
		this.exp = new Experience(character);;
	}

	public void setName(String name) {
		this.name = name;
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
		return exp.getExperience();
	}

	public void addExperience(int experience) {
		this.exp.addExp(experience);
	}

	public int getLevel() {
		return exp.getCurrentLevel();
	}

	public void setLevel(int level) {
		this.exp.setLevel(level);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLife() {
		return this.life;
	}

	public void setLife(int life) {
		this.life = life;
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
	
	public double[] getLevelProgress() {
		return exp.getLevelProgress();
	}
}
