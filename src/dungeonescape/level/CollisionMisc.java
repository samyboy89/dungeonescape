package dungeonescape.level;

import dungeonescape.helper.Tile;
import dungeonescape.helper.TileDecider;

public class CollisionMisc extends LevelFunctions {

	public CollisionMisc() {
		super();
	}
	
	public CollisionMisc(int level) {
		super(level);
	}
	
	@Override
	public String isOfLevelType(int code) {
		return TileDecider.isCollisionMisc(code);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.COLLISIONMISC_TXT;
	}
}
