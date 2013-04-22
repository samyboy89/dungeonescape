package dungeonescape.helper;

import dungeonescape.items.Item;

public final class Type {

	public static final int POTION = 9999;

	public static final void potion_heal_50(Item common) {
		normal.setType(POTION);
		normal.setName("HealingPotion common");
		normal.setHealing(50);
		normal.setRequired(1);
		normal.setValue(10);
	}
	
	public static final void potion_heal_100(Item rare) {
		normal.setType(POTION);
		normal.setName("HealingPotion rare");
		normal.setHealing(100);
		normal.setRequired(3);
		normal.setValue(35);
	}
	
	public static final void potion_heal_150(Item epic) {
		normal.setType(POTION);
		normal.setName("HealingPotion epic");
		normal.setHealing(150);
		normal.setRequired(5);
		normal.setValue(75);
	}
	
	public static final void potion_heal_200(Item legendary) {
		normal.setType(POTION);
		normal.setName("HealingPotion legendary");
		normal.setHealing(200);
		normal.setRequired(8);
		normal.setValue(100);
	}

	public static final int SHIELD = 9899;

	public static final void shield_common(Item common) {
		common.setType(SHIELD);
		common.setName("Shield Common");
		common.setProtection(10);
		common.setRequired(1);
		common.setValue(15);
	}

	public static final void shield_rare(Item rare) {
		rare.setType(SHIELD);
		rare.setName("Shield Rare");
		rare.setProtection(30);
		rare.setRequired(3);
		rare.setValue(100);
	}

	public static final void shield_epic(Item epic) {
		epic.setType(SHIELD);
		epic.setName("Shield Epic");
		epic.setProtection(40);
		epic.setRequired(5);
		epic.setValue(120);
	}

	public static final void shield_legendary(Item legendary) {
		legendary.setType(SHIELD);
		legendary.setName("Shield legendary");
		legendary.setProtection(65);
		legendary.setRequired(8);
		legendary.setValue(200);
	}
	
	public static final int AXE = 9799;
	
	public static final void axe_common(Item common) {
		common.setType(AXE);
		common.setName("Axe common");
		common.setDPS(8);
		common.setRequired(1);
		common.setValue(10);
	}

	public static final void axe_rare(Item rare) {
		rare.setType(AXE);
		rare.setName("Axe rare");
		rare.setDPS(11);
		rare.setRequired(3);
		rare.setValue(20);
	}

	public static final void axe_epic(Item epic) {
		epic.setType(AXE);
		epic.setName("Axe epic");
		epic.setDPS(15);
		epic.setRequired(5);
		epic.setValue(40);
	}
	
	public static final void axe_legendary(Item legendary) {
		legendary.setType(AXE);
		legendary.setName("Axe legendary");
		legendary.setDPS(25);
		legendary.setRequired(8);
		legendary.setValue(100);
	}
	
	public static final int SWORD = 9798;
	
	public static final void sword_common(Item common) {
		common.setType(SWORD);
		common.setName("Sword common");
		common.setDPS(8);
		common.setRequired(1);
		common.setValue(10);
	}

	public static final void sword_rare(Item rare) {
		rare.setType(SWORD);
		rare.setName("Sword rare");
		rare.setDPS(11);
		rare.setRequired(3);
		rare.setValue(20);
	}

	public static final void sword_epic(Item epic) {
		epic.setType(SWORD);
		epic.setName("Sword epic");
		epic.setDPS(15);
		epic.setRequired(5);
		epic.setValue(40);
	}
	
	public static final void sword_legendary(Item legendary) {
		legendary.setType(SWORD);
		legendary.setName("Sword legendary");
		legendary.setDPS(25);
		legendary.setRequired(8);
		legendary.setValue(100);
	}
}
