package dungeonescape.character;

import dungeonescape.player.CharacterFunctions;


public class Experience {

	private dungeonescape.player.Character character;
	private int player_current_level = 1;
	private int player_exp = 0;

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
	//nprivate final double LEVEL_10 = 400000;
	
	private final double MAX_LEVEL = 9;
	
	private final double[] LEVELS = { LEVEL_0, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7, LEVEL_8, LEVEL_9 };
	
	public Experience (dungeonescape.player.Character character) {
		this.character = character;
		this.player_current_level = 0;
	}
	
	public void addExp(double exp) {
		if (exp > 0 && player_exp + exp < LEVEL_9)
			player_exp += exp;
		else if (player_exp + exp > LEVEL_9)
			player_exp = (int) LEVEL_9;
	}
	
	public int getCurrentLevel() {
		return this.player_current_level;
	}
	
	public void setLevel(int level) {
		this.player_current_level = level;
	}
	
	public int getLevel() {
		if (player_exp >= LEVEL_9) {
			return 10;
		} else if (player_exp >= LEVEL_8) {
			return 9;
		} else if (player_exp >= LEVEL_7) {
			return 8;
		} else if (player_exp >= LEVEL_6) {
			return 7;
		} else if (player_exp >= LEVEL_5) {
			return 6;
		} else if (player_exp >= LEVEL_4) {
			return 5;
		} else if (player_exp >= LEVEL_3) {
			return 4;
		} else if (player_exp >= LEVEL_2) {
			return 3;
		} else if (player_exp >= LEVEL_1) {
			return 2;
		} else {
			return 1;
		}
	}
	
	public double[] getLevelProgress() {
		int level = getLevel();
		if (level != player_current_level) {
			this.player_current_level = (int) level;
			character.firePlayerChange(CharacterFunctions.CHANGE_LEVEL);
		}
		if (level == MAX_LEVEL)  {
			return  new double[] { 100, MAX_LEVEL-1, MAX_LEVEL};
		} else {
			double fromLevel = LEVELS[level - 1];
			double toLevel = LEVELS[(level)];
			return  new double[] { (((player_exp - fromLevel) / (toLevel - fromLevel)) * 100), fromLevel, toLevel};
		}
	}

	public int getExperience() {
		return player_exp;
	}
}
