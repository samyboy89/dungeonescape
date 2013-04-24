package dungeonescape.menu;

import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GCanvas;
import dungeonescape.character.CharacterFunctions;
import dungeonescape.helper.Window;
import dungeonescape.items.Item;

public class InventoryGrid {

	public RightMenu menu;

	GCanvas gcanvas = new GCanvas();
	int columns;
	int rows;
	int x;
	int y;
	int padding_inside;
	int a_column;
	int a_row;

	public ArrayList<GridEntry> entries;
	public ArrayList<GridItem> items;

	public InventoryGrid(RightMenu menu) {
		this.menu = menu;

		entries = new ArrayList<GridEntry>();
		items = new ArrayList<GridItem>();
	}

	public void printPlayerInventory() {
		gcanvas = new GCanvas();
		gcanvas.setBackground(Color.DARK_GRAY);
		gcanvas.setSize(Window.MENU_X - 40, Window.WINDOW_Y - 400);
		gcanvas.setLocation(20, 400);

		columns = 6;
		rows = 5;
		x = gcanvas.getWidth();
		y = gcanvas.getHeight();
		padding_inside = 6;
		a_column = (x - padding_inside * (columns - 1)) / columns;
		a_row = (y - padding_inside * (rows - 1)) / rows;

		initGrid();
		menu.gcanvas.add(gcanvas);
	}

	private void initGrid() {
		int counter = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				GridEntry rect = new GridEntry(a_column, a_row);
				rect.setColor(Color.GRAY);
				rect.setFillColor(Color.GRAY);
				rect.setFilled(true);
				rect.setLocation((j * (x / columns)) + 2, (i * (y / rows)) + 2);
				rect.setId(counter);
				rect.setGrid(this);
				entries.add(rect);
				gcanvas.add(rect);
				counter++;
			}
		}
		updateInventoryGrid();
	}

	public void updateInventoryGrid() {
		for (GridEntry e : entries) {
			e.setFillColor(Color.GRAY);
		}
		for (GridItem i : items) {
			gcanvas.remove(i);
		}
		items.removeAll(items);
		ArrayList<Item> items = menu.player.getInventory().getItemsList();
		int counter = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				if (items.size() > (counter)) {
					Item it = items.get(counter);
					GridItem item = it.getView();
					item.setSize(a_row, a_row);
					item.setLocation((j * (x / columns)) + 6,
							(i * (y / rows)) + 2);
					item.setId(counter);
					item.setEntry(entries.get(counter));
					item.setItem(it);

					if (it.isActive())
						item.getEntry().setFillColor(Color.green);
					else
						item.getEntry().setFillColor(it.getBackground());

					this.items.add(item);
					gcanvas.add(item);
				} else {
					break;
				}
				counter++;
			}
		}
	}

	public Item getItemFromItems(int code) {
		if (items.size() > code) {
			return items.get(code).getItem();
		}
		return null;
	}

	public void deleteIfCan() {
		for (GridEntry e : entries) {
			if (e.isPushedDown()) {
				try {
					menu.player.getInventory().removeItem(e.getId());
				} catch (ArrayIndexOutOfBoundsException i) {
					i.printStackTrace();
				}
			}
		}
	}

}
