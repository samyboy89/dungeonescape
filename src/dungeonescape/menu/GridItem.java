package dungeonescape.menu;

import java.awt.Image;

import acm.graphics.GImage;
import dungeonescape.items.Item;

public class GridItem extends GImage {
	
	private GridEntry entry;
	private int id;
	private Item item;
	
	public GridItem(Image arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
	}

	public GridItem(Image arg0) {
		super(arg0);
	}

	public GridItem(String arg0) {
		super(arg0);
	}

	public GridItem(String arg0, double arg1, double arg2) {
		super(arg0, arg1, arg2);
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public GridEntry getEntry() {
		return entry;
	}

	public void setEntry(GridEntry entry) {
		this.entry = entry;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
