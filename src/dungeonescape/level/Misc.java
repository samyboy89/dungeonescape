package dungeonescape.level;

import dungeonescape.helper.Tile;

public class Misc extends LevelFunctions {

	public Misc() {
		super();
	}
	
	public Misc(int level) {
		super(level);
	}

	@Override
	public String isOfLevelType(int code) {
		return isLevel(code, Tile.misc, Tile.MISC_IMG_PATH);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.MISC_TXT;
	}
}
