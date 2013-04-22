package dungeonescape.helper;

import dungeonescape.items.Item;

public final class Type {

	public static final int POTION_RARE = 9999;
	public static final int POTION_EPIC = 9998;
	public static final int POTION_LEGENDARY = 9997;
	public static final int POTION_ATTACK_SPEED = 9996;
	public static final int POTION_PROTECTION = 9995;

	public static final int SHIELD_RARE = 9989;
	public static final int SHIELD_EPIC = 9988;
	public static final int SHIELD_LEGENDARY = 9987;

	public static final int HEAD_COMMON = 9979;
	public static final int HEAD_RARE = 9978;
	public static final int HEAD_EPIC = 9977;
	public static final int HEAD_LEGENDARY = 9976;

	public static final int CHEST_COMMON = 9969;
	public static final int CHEST_RARE = 9968;
	public static final int CHEST_EPIC = 9967;
	public static final int CHEST_LEGENDARY = 9966;

	public static final int HANDS_RARE = 9959;
	public static final int HANDS_EPIC = 9958;
	public static final int HANDS_LEGENDARY = 9957;

	public static final int FEET_EPIC = 9949;
	public static final int FEET_LEGENDARY = 9948;

	public static final int SWORD_COMMON = 9939;
	public static final int SWORD_RARE = 9938;
	public static final int SWORD_EPIC = 9937;
	public static final int SWORD_LEGENDARY = 9936;

	public static final int STAFF_COMMON = 9929;
	public static final int STAFF_RARE = 9928;
	public static final int STAFF_EPIC = 9927;
	public static final int STAFF_LEGENDARY = 9926;

	public static final int KEY_RARE = 9919;
	public static final int KEY_EPIC = 9918;
	public static final int KEY_LEGENDARY = 9917;

	// Potion
	public static final void potion_heal_rare(Item rare) {
		rare.setType(POTION_RARE);
		rare.setName("Healing Potion Rare");
		rare.setHealing(50);
		rare.setRequired(1);
		rare.setValue(10);
	}

	public static final void potion_heal_epic(Item epic) {
		epic.setType(POTION_EPIC);
		epic.setName("Healing Potion epic");
		epic.setHealing(150);
		epic.setRequired(5);
		epic.setValue(75);
	}

	public static final void potion_heal_legendary(Item legendary) {
		legendary.setType(POTION_LEGENDARY);
		legendary.setName("Healing Potion Legendary");
		legendary.setHealing(200);
		legendary.setRequired(8);
		legendary.setValue(100);
	}

	public static final void potion_attack_speed(Item legendary) {
		legendary.setType(POTION_ATTACK_SPEED);
		legendary.setName("Attack Speed Potion Legendary");
		legendary.setDPS(200);
		legendary.setRequired(8);
		legendary.setValue(100);
	}

	public static final void potion_protection(Item legendary) {
		legendary.setType(POTION_PROTECTION);
		legendary.setName("Protection Potion Legendary");
		legendary.setProtection(200);
		legendary.setRequired(8);
		legendary.setValue(100);
	}

	// Shield
	public static final void shield_rare(Item rare) {
		rare.setType(SHIELD_RARE);
		rare.setName("Shield Rare");
		rare.setProtection(30);
		rare.setRequired(3);
		rare.setValue(100);
	}

	public static final void shield_epic(Item epic) {
		epic.setType(SHIELD_EPIC);
		epic.setName("Shield Epic");
		epic.setProtection(40);
		epic.setRequired(5);
		epic.setValue(120);
	}

	public static final void shield_legendary(Item legendary) {
		legendary.setType(SHIELD_LEGENDARY);
		legendary.setName("Shield legendary");
		legendary.setProtection(65);
		legendary.setRequired(8);
		legendary.setValue(200);
	}

	// Head
	public static final void head_common(Item common) {
		common.setType(HEAD_COMMON);
		common.setName("Head common");
		common.setProtection(8);
		common.setRequired(1);
		common.setValue(10);
	}

	public static final void head_rare(Item rare) {
		rare.setType(HEAD_RARE);
		rare.setName("Head rare");
		rare.setProtection(11);
		rare.setRequired(3);
		rare.setValue(20);
	}

	public static final void head_epic(Item epic) {
		epic.setType(HEAD_EPIC);
		epic.setName("Head epic");
		epic.setProtection(15);
		epic.setRequired(5);
		epic.setValue(40);
	}

	public static final void head_legendary(Item legendary) {
		legendary.setType(HEAD_LEGENDARY);
		legendary.setName("Head legendary");
		legendary.setProtection(25);
		legendary.setRequired(8);
		legendary.setValue(100);
	}

	// Chest
	public static final void chest_common(Item common) {
		common.setType(CHEST_COMMON);
		common.setName("Chest common");
		common.setProtection(10);
		common.setRequired(1);
		common.setValue(10);
	}

	public static final void chest_rare(Item rare) {
		rare.setType(CHEST_RARE);
		rare.setName("Chest rare");
		rare.setProtection(15);
		rare.setRequired(3);
		rare.setValue(20);
	}

	public static final void chest_epic(Item epic) {
		epic.setType(CHEST_EPIC);
		epic.setName("Chest epic");
		epic.setProtection(25);
		epic.setRequired(5);
		epic.setValue(40);
	}

	public static final void chest_legendary(Item legendary) {
		legendary.setType(CHEST_LEGENDARY);
		legendary.setName("Chest legendary");
		legendary.setProtection(30);
		legendary.setRequired(8);
		legendary.setValue(100);
	}

	// Hands
	public static final void hands_rare(Item rare) {
		rare.setType(HANDS_RARE);
		rare.setName("Hands rare");
		rare.setProtection(10);
		rare.setRequired(3);
		rare.setValue(20);
	}

	public static final void hands_epic(Item epic) {
		epic.setType(HANDS_EPIC);
		epic.setName("Hands epic");
		epic.setProtection(15);
		epic.setRequired(5);
		epic.setValue(40);
	}

	public static final void hands_legendary(Item legendary) {
		legendary.setType(HANDS_LEGENDARY);
		legendary.setName("Hands legendary");
		legendary.setProtection(25);
		legendary.setRequired(8);
		legendary.setValue(100);
	}

	// Feet
	public static final void feet_epic(Item epic) {
		epic.setType(FEET_EPIC);
		epic.setName("Feet epic");
		epic.setProtection(15);
		epic.setRequired(5);
		epic.setValue(40);
	}

	public static final void feet_legendary(Item legendary) {
		legendary.setType(FEET_LEGENDARY);
		legendary.setName("Feet legendary");
		legendary.setProtection(25);
		legendary.setRequired(8);
		legendary.setValue(100);
	}

	// Staff
	public static final void staff_common(Item common) {
		common.setType(STAFF_COMMON);
		common.setName("Staff common");
		common.setDPS(8);
		common.setRequired(1);
		common.setValue(10);
	}

	public static final void staff_rare(Item rare) {
		rare.setType(STAFF_RARE);
		rare.setName("Staff rare");
		rare.setDPS(11);
		rare.setRequired(3);
		rare.setValue(20);
	}

	public static final void staff_epic(Item epic) {
		epic.setType(STAFF_EPIC);
		epic.setName("Staff epic");
		epic.setDPS(15);
		epic.setRequired(5);
		epic.setValue(40);
	}

	public static final void staff_legendary(Item legendary) {
		legendary.setType(STAFF_LEGENDARY);
		legendary.setName("Staff legendary");
		legendary.setDPS(25);
		legendary.setRequired(8);
		legendary.setValue(100);
	}

	// Sword
	public static final void sword_common(Item common) {
		common.setType(SWORD_COMMON);
		common.setName("Sword common");
		common.setDPS(8);
		common.setRequired(1);
		common.setValue(10);
	}

	public static final void sword_rare(Item rare) {
		rare.setType(SWORD_RARE);
		rare.setName("Sword rare");
		rare.setDPS(11);
		rare.setRequired(3);
		rare.setValue(20);
	}

	public static final void sword_epic(Item epic) {
		epic.setType(SWORD_EPIC);
		epic.setName("Sword epic");
		epic.setDPS(15);
		epic.setRequired(5);
		epic.setValue(40);
	}

	public static final void sword_legendary(Item legendary) {
		legendary.setType(SWORD_LEGENDARY);
		legendary.setName("Sword legendary");
		legendary.setDPS(25);
		legendary.setRequired(8);
		legendary.setValue(100);
	}
	
	public static final void key_rare(Item rare) {
		rare.setType(KEY_RARE);
		rare.setName("Key rare");
		rare.setRequired(3);
	}

	public static final void key_epic(Item epic) {
		epic.setType(KEY_EPIC);
		epic.setName("Key epic");
		epic.setRequired(5);
	}

	public static final void key_legendary(Item legendary) {
		legendary.setType(KEY_LEGENDARY);
		legendary.setName("Key legendary");
		legendary.setRequired(8);
	}
}
