package dungeonescape.level;

import dungeonescape.helper.Tile;
import dungeonescape.helper.TileDecider;

public class Moveable extends LevelFunctions {
	
	private boolean hasMoved = false;

	public Moveable() {
		super();
	}
	
	public Moveable(int level) {
		super(level);
	}

	public void setCell(int i, int x, int y) {
		this.list.get(y).set(x, i);
		hasMoved = true;
	}

	public void removeCell(int x, int y) {
		this.list.get(y).set(x, 0);
	}

	public boolean isMoveable(int dx, int dy) {
		int code = this.getCell(dx, dy);
		switch (code) {
		case Tile.OBJECTS_BOX_EMPTY:
		case Tile.OBJECTS_BOX_SWORDS:
		case Tile.OBJECTS_BOX:
			return true;
		default:
			return false;
		}
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}

	public void setMoved() {
		this.hasMoved = false;
	}
	
	@Override
	public String isOfLevelType(int code) {
		return TileDecider.isMoveable(code);
	}

	@Override
	public String getFileLocation() {
		return Tile.MOVEABLE_TXT;
	}
}
