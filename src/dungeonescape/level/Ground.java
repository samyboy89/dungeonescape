package dungeonescape.level;

import dungeonescape.helper.Tile;
import dungeonescape.helper.TileDecider;

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
		return TileDecider.isGround(code);
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
