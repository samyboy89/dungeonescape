package dungeonescape.helper;

import dungeonescape.items.Item;


public final class Type {

	public static final int POTION = 9999;
	public static final void potion_heal_50 (Item normal) {
		normal.setHealing(50);
	}
	
	public static final int SHIELD_COMMON = 0;
	public static final int SHIELD_LIGHT = 1;
	public static final int SHIELD_LEATHER = 2;
	public static final int SHIELD_TOWER = 3;
	
	public static final int SHIELD_COMMON_PROTECTION = 4;
	public static final int SHIELD_LIGHT_PROTECTION = 10;
	public static final int SHIELD_LEATHER_PROTECTION = 16;
	public static final int SHIELD_TOWER_PROTECTION = 24;
}
