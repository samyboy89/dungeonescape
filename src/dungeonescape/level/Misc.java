package dungeonescape.level;

import dungeonescape.helper.Tile;
import dungeonescape.helper.TileDecider;

public class Misc extends LevelFunctions {

	public Misc() {
		super();
	}
	
	public Misc(int level) {
		super(level);
	}

	@Override
	public String isOfLevelType(int code) {
		return TileDecider.isMisc(code);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.MISC_TXT;
	}
}
