package dungeonescape.character;


public class Experience {

	private dungeonescape.player.Character character;
	private int player_current_level;
	private int player_exp;
	
	private final double LEVEL_1 = 0;
	private final double LEVEL_2 = 6000;
	private final double LEVEL_3 = 15000;
	private final double LEVEL_4 = 30000;
	private final double LEVEL_5 = 50000;
	private final double LEVEL_6 = 90000;
	private final double LEVEL_7 = 145000;
	private final double LEVEL_8 = 210000;
	private final double LEVEL_9 = 300000;
	private final double LEVEL_10 = 400000;
	
	private final double MAX_LEVEL = 10;
	
	private final double[] LEVELS = { LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7, LEVEL_8, LEVEL_9, LEVEL_10 };
	
	public Experience (dungeonescape.player.Character character) {
		this.character = character;
		this.player_current_level = 0;
	}
	
	public void addExp(double exp) {
		if (exp > 0)
			player_exp += exp;
	}
	
	public int getCurrentLevel() {
		return this.player_current_level;
	}
	
	public void setLevel(int level) {
		this.player_current_level = level;
	}
	
	public double getLevel() {
		if (player_exp >= LEVEL_10) {
			return LEVEL_10;
		} else if (player_exp >= LEVEL_9) {
			return LEVEL_9;
		} else if (player_exp >= LEVEL_8) {
			return LEVEL_8;
		} else if (player_exp >= LEVEL_7) {
			return LEVEL_7;
		} else if (player_exp >= LEVEL_6) {
			return LEVEL_6;
		} else if (player_exp >= LEVEL_5) {
			return LEVEL_5;
		} else if (player_exp >= LEVEL_4) {
			return LEVEL_4;
		} else if (player_exp >= LEVEL_3) {
			return LEVEL_3;
		} else if (player_exp >= LEVEL_2) {
			return LEVEL_2;
		} else  {
			return LEVEL_1;
		}
	}
	
	public double[] getLevelProgress() {
		double level = getLevel();
		if (level == MAX_LEVEL)  {
			return  new double[] {100, MAX_LEVEL-1, MAX_LEVEL};
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
