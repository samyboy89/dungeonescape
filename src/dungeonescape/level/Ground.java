package dungeonescape.level;

import dungeonescape.helper.Tile;

public class Ground extends LevelFunctions {

	private int sizeX, sizeY;

	public Ground() {
		super();
	}
	
	public Ground(int level) {
		super(level);
	}

	@Override
	public void createLevel(int level) {
		Integer[] level_size = getLevelsFromFile(level, Tile.GROUND_TXT);
		sizeX = level_size[1];
		sizeY = level_size[0];
	}
	
	@Override
	public String isOfLevelType(int code) {
		return isLevel(code, Tile.ground, Tile.GROUND_IMG_PATH);
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}
	
	@Override
	public String getFileLocation() {
		return Tile.GROUND_TXT;
	}

}
