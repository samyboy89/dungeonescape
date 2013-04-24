package dungeonescape.character;

import java.util.ArrayList;

import acm.graphics.GObject;
import dungeonescape.items.Item;

public class Inventory {

	private dungeonescape.character.Character character;
	private ArrayList<Item> items;
	
	public Inventory (dungeonescape.character.Character character) {
		this.character = character;
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
		character.firePlayerChange(CharacterFunctions.CHANGE_INVENTORY);
	}
	
	public void setItem(Item item, int position) {
		this.items.set(position, item);
		character.firePlayerChange(CharacterFunctions.CHANGE_INVENTORY);
	}
	
	public void removeItem(Item item) {
		this.items.remove(item);
		character.firePlayerChange(CharacterFunctions.CHANGE_INVENTORY);
	}
	
	public void removeItem(int position) {
		this.items.remove(position);
		character.firePlayerChange(CharacterFunctions.CHANGE_INVENTORY);
	}
	
	public ArrayList<GObject> getAllItemsAsViews(){
		ArrayList<GObject> objects = new ArrayList<GObject>();
		for (Item i : items)
			objects.add(i.getView());		
		return objects;
	}
	
	public void useItem(Item item) {
		item.doAction();
		character.firePlayerChange(CharacterFunctions.CHANGE_INVENTORY);
	}
	
}
