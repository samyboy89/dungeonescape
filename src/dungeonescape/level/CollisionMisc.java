package dungeonescape.level;

import dungeonescape.helper.Tile;

public class CollisionMisc extends LevelFunctions {

	public CollisionMisc() {
		super();
	}
	
	public CollisionMisc(int level) {
		super(level);
	}
	
	@Override
	public String isOfLevelType(int code) {
		return isLevel(code, Tile.collisionmisc, Tile.COLLISIONMISC_IMG_PATH);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.COLLISIONMISC_TXT;
	}
}
