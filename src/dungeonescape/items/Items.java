package dungeonescape.items;

import java.util.ArrayList;
import java.util.Collections;

import dungeonescape.helper.Type;

public class Items {

	private ArrayList<Item> items;
	private ArrayList<Item> keys;

	public static final int COMMON_ITEM = 0;
	public static final int RARE_ITEM = 1;
	public static final int EPIC_ITEM = 2;
	public static final int LEGENDARY_ITEM = 3;

	public static final int KEY_RARE = 0;
	public static final int KEY_EPIC = 1;
	public static final int KEY_LEGENDARY = 2;

	public Items() {
		keys = new ArrayList<Item>();
		items = new ArrayList<Item>();
		items.add(Type.chest_common());
		items.add(Type.chest_rare());
		items.add(Type.chest_epic());
		items.add(Type.chest_legendary());
		items.add(Type.feet_epic());
		items.add(Type.feet_legendary());
		items.add(Type.hands_epic());
		items.add(Type.hands_legendary());
		items.add(Type.hands_rare());
		items.add(Type.head_common());
		items.add(Type.head_rare());
		items.add(Type.head_legendary());
		items.add(Type.head_epic());
		items.add(Type.potion_attack_speed());
		items.add(Type.potion_heal_epic());
		items.add(Type.potion_heal_legendary());
		items.add(Type.potion_heal_rare());
		items.add(Type.potion_protection());
		items.add(Type.shield_epic());
		items.add(Type.shield_legendary());
		items.add(Type.shield_rare());
		items.add(Type.staff_common());
		items.add(Type.staff_epic());
		items.add(Type.staff_legendary());
		items.add(Type.staff_rare());
		items.add(Type.sword_common());
		items.add(Type.sword_epic());
		items.add(Type.sword_rare());
		items.add(Type.sword_legendary());
		
		keys.add(Type.key_epic());
		keys.add(Type.key_legendary());
		keys.add(Type.key_rare());
	}
	
	public Item getKey(int key_type) {
		switch (key_type) {
		case KEY_RARE:
			return Type.key_rare();
		case KEY_EPIC:
			return Type.key_epic();
		case KEY_LEGENDARY:
			return Type.key_legendary();
		}
		return null;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public ArrayList<Item> getKeys() {
		return keys;
	}

	public ArrayList<Item> getItems(int level) {
		int type = getLevelType(level);
		ArrayList<Item> it = new ArrayList<Item>();
		for (Item i : items) {
			switch (type) {
			case COMMON_ITEM:
				if (i instanceof ItemCommon)
					it.add(i);
				break;
			case RARE_ITEM:
				if (i instanceof ItemRare)
					it.add(i);
				break;
			case EPIC_ITEM:
				if (i instanceof ItemEpic)
					it.add(i);
				break;
			case LEGENDARY_ITEM:
				if (i instanceof ItemLegendary)
					it.add(i);
				break;
			}
		}
		Collections.shuffle(it);
		return it;
	}

	private int getLevelType(int level) {
		switch (level) {
		case 0:
		case 1:
		case 2:
			return COMMON_ITEM;
		case 3:
		case 4:
		case 5:
			return RARE_ITEM;
		case 7:
		case 8:
		case 6:
			return EPIC_ITEM;
		case 9:
		case 10:
			return LEGENDARY_ITEM;
		default:
			return RARE_ITEM;
		}
	}
}
