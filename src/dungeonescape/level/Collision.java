package dungeonescape.level;

import dungeonescape.helper.Tile;
import dungeonescape.helper.TileDecider;

public class Collision extends LevelFunctions {

	public Collision() {
		super();
	}
	
	public Collision(int level) {
		super(level);
	}
	
	@Override
	public String isOfLevelType(int code) {
		return TileDecider.isCollision(code);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.COLLISION_TXT;
	}
}
