package dungeonescape.character;


public class Experience {

	private dungeonescape.player.Character character;
	private int player_current_level;
	private int player_exp;

	private final double LEVEL_0 = 0;
	private final double LEVEL_1 = 6000;
	private final double LEVEL_2 = 12000;
	private final double LEVEL_3 = 20000;
	private final double LEVEL_4 = 30000;
	private final double LEVEL_5 = 50000;
	private final double LEVEL_6 = 90000;
	private final double LEVEL_7 = 145000;
	private final double LEVEL_8 = 210000;
	private final double LEVEL_9 = 300000;
	private final double LEVEL_10 = 400000;
	
	private final double MAX_LEVEL = 10;
	
	private final double[] LEVELS = { LEVEL_0, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7, LEVEL_8, LEVEL_9, LEVEL_10 };
	
	public Experience (dungeonescape.player.Character character) {
		this.character = character;
		this.player_current_level = 0;
	}
	
	public void addExp(double exp) {
		if (exp > 0 && player_exp + exp < LEVEL_10)
			player_exp += exp;
		else if (player_exp + exp > LEVEL_10)
			player_exp = (int) LEVEL_10;
	}
	
	public int getCurrentLevel() {
		return this.player_current_level;
	}
	
	public void setLevel(int level) {
		this.player_current_level = level;
	}
	
	public double getLevel() {
		if (player_exp >= LEVEL_10) {
			return 10;
		} else if (player_exp >= LEVEL_9) {
			return 9;
		} else if (player_exp >= LEVEL_8) {
			return 8;
		} else if (player_exp >= LEVEL_7) {
			return 7;
		} else if (player_exp >= LEVEL_6) {
			return 6;
		} else if (player_exp >= LEVEL_5) {
			return 5;
		} else if (player_exp >= LEVEL_4) {
			return 4;
		} else if (player_exp >= LEVEL_3) {
			return 3;
		} else if (player_exp >= LEVEL_2) {
			return 2;
		} else if (player_exp >= LEVEL_1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public double[] getLevelProgress() {
		double level = getLevel();
		this.player_current_level = (int) level;
		if (level == MAX_LEVEL)  {
			return  new double[] { 100, MAX_LEVEL-1, MAX_LEVEL};
		} else {
			double fromLevel = LEVELS[(int) level];
			double toLevel = LEVELS[((int) level)+1];
			return  new double[] { (((player_exp - fromLevel) / (toLevel - fromLevel)) * 100), fromLevel, toLevel};
		}
	}

	public int getExperience() {
		return player_exp;
	}
}
