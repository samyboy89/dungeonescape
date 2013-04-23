package dungeonescape.level;

import dungeonescape.helper.Tile;

public class PickUpItems extends LevelFunctions {

	public PickUpItems() {
		super();
	}
	
	public PickUpItems(int level) {
		super(level);
	}
	
	@Override
	public String isOfLevelType(int code) {
		return isLevel(code, Tile.items, Tile.PICKUPITEMS_IMG_PATH);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.PICKUPITEMS_TXT;
	}

	public boolean isItem(int code) {
		if (Tile.items.contains(code))
			return true;
		return false;
	}
}
