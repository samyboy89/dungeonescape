package dungeonescape.level;

import dungeonescape.helper.Tile;

public class TopLayer extends LevelFunctions {

	public TopLayer() {
		super();
	}
	
	public TopLayer(int level) {
		super(level);
	}
	
	@Override
	public String isOfLevelType(int code) {
		return isLevel(code, Tile.toplayer, Tile.TOPLAYER_IMG_PATH);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.TOPLAYER_TXT;
	}
}
