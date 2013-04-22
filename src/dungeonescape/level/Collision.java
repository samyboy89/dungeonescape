package dungeonescape.level;

import dungeonescape.helper.Tile;

public class Collision extends LevelFunctions {

	public Collision() {
		super();
	}
	
	public Collision(int level) {
		super(level);
	}
	
	@Override
	public String isOfLevelType(int code) {
		return isLevel(code, Tile.collision, Tile.COLLISION_IMG_PATH);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.COLLISION_TXT;
	}
}
