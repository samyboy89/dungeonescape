package dungeonescape.level;

import dungeonescape.helper.Levels;
import dungeonescape.helper.Tile;

public class Door extends LevelFunctions {

	public Door() {
		super();
	}
	
	public Door(int level) {
		super(level);
	}

	@Override
	public String getFileLocation() {
		return Tile.DOOR_TXT;
	}
	
	public boolean isDoor(int room) {
		switch(room) {
		case Levels.ROOMA:
		case Levels.ROOMB:
		case Levels.ROOMC:
		case Levels.ROOMD:
		case Levels.ROOME:
		case Levels.ROOMF:
		case Levels.ROOMG:
		case Levels.ROOMH:
		case Levels.ROOMI:
		case Levels.ROOMJ:
		case Levels.ROOMa:
		case Levels.ROOMe:
		case Levels.ROOMf:
		case Levels.ROOMg:
		case Levels.ROOMh:
		case Levels.ROOMX:
		case Levels.ROOMY:
		case Levels.ROOM1:
			return true;
		default:
			return false;
		}
		
	}
}
