package dungeonescape.level;

import java.util.ArrayList;

import acm.graphics.GCanvas;
import acm.util.RandomGenerator;
import dungeonescape.Main;
import dungeonescape.helper.Tile;
import dungeonescape.items.Item;
import dungeonescape.items.Items;

public class Chests extends LevelFunctions {

	public GCanvas gcanvas = new GCanvas();
	
	public Chests() {
		super();
	}
	
	public Chests(int level) {
		super(level);
	}
	
	@Override
	public String isOfLevelType(int code) {
		return isLevel(code, Tile.chests, Tile.CHESTS_IMG_PATH);
	}
	
	@Override
	public String getFileLocation() {
		return Tile.CHESTS_TXT;
	}
	
	public void changeCell(int x, int y, int code) {
		this.list.get(y).set(x, code);
	}

	public boolean isChest(int code) {
		if (Tile.chests.contains(code))
			return true;
		return false;
	}
	
	public void openChest() {
		Items items = new Items();
		ArrayList<Item> itemsList = items.getItems(Main.main.player.getLevel());
		int random = RandomGenerator.getInstance().nextInt(0, itemsList.size()-1);
		Item item = itemsList.get(random);
		
		if (item != null)
			Main.main.player.getInventory().addItem(item);
	}
}
