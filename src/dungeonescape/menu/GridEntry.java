package dungeonescape.menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import acm.graphics.GRect;
import dungeonescape.items.Item;

public class GridEntry extends GRect {

	private InventoryGrid grid;
	private int id;
	private boolean isPushed;

	public GridEntry(double x, double y) {
		super(x, y);
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				isPushed = false;
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				isPushed = true;
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (grid != null) {
					Item item = grid.getItemFromItems(getId());
					if (item != null) {
						item.setActive(item.isActive() ? false : true);
					}
				}	
			}
		});
	}

	public GridEntry(double x, double y, double z, double w) {
		super(x, y, z, w);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InventoryGrid getGrid() {
		return grid;
	}

	public void setGrid(InventoryGrid grid) {
		this.grid = grid;
	}

	public boolean isPushedDown() {
		return isPushed;
	}
}
