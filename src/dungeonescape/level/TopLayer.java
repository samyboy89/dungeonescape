package dungeonescape.level;

import dungeonescape.helper.Tile;
import dungeonescape.helper.TileDecider;

public class TopLayer extends LevelFunctions {

	public TopLayer() {
		super();
	}
	
	public TopLayer(int level) {
		super(level);
	}
	
	@Override
	public String isOfLevelType(int code) {
		return TileDecider.isTopLayer(code);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.TOPLAYER_TXT;
	}
}
