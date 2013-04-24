package dungeonescape.character;

import java.util.ArrayList;

import acm.graphics.GObject;
import dungeonescape.items.Item;

public class Inventory {

	private dungeonescape.character.Character character;
	private ArrayList<Item> items;
	private ArrayList<Item> keys;
	
	public Inventory (dungeonescape.character.Character character) {
		this.character = character;
		this.items = new ArrayList<Item>();
		this.keys = new ArrayList<Item>();
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
	
	public ArrayList<Item> getKeysList(){
		return this.keys;
	}
	
	public Item getKeys(int position) {
		return this.keys.get(position);
	}

	public void addKey(Item item) {
		this.keys.add(item);
		character.firePlayerChange(CharacterFunctions.CHANGE_KEY);
	}
	
	public void setKey(Item item, int position) {
		this.keys.set(position, item);
		character.firePlayerChange(CharacterFunctions.CHANGE_KEY);
	}
	
	public void removeKey(Item item) {
		this.keys.remove(item);
		character.firePlayerChange(CharacterFunctions.CHANGE_KEY);
	}
	
	public void removeKey(int position) {
		this.keys.remove(position);
		character.firePlayerChange(CharacterFunctions.CHANGE_KEY);
	}
	
	
}
