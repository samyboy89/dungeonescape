package dungeonescape.character;

import java.util.ArrayList;

import acm.graphics.GObject;
import dungeonescape.items.Item;

public class Inventory {

	private ArrayList<Item> items;
	
	public Inventory () {
		this.items = new ArrayList<Item>();
	}

	public ArrayList<Item> getItemsList(){
		return this.items;
	}
	
	public Item getItem(int position) {
		return this.items.get(position);
	}

	public void addItem(Item item) {
		this.items.add(item);
	}
	
	public void setItem(Item item, int position) {
		this.items.set(position, item);
	}
	
	public void removeItem(Item item) {
		this.items.remove(item);
	}
	
	public void removeItem(int position) {
		this.items.remove(position);
	}
	
	public ArrayList<GObject> getAllItemsAsViews(){
		ArrayList<GObject> objects = new ArrayList<GObject>();
		for (Item i : items)
			objects.add(i.getView());		
		return objects;
	}
	
	public void useItem(Item item) {
		item.doAction();
	}
	
}
