package dungeonescape.helper;

import dungeonescape.items.Item;

public final class Type {

	public static final int POTION = 9999;

	public static final void potion_heal_50(Item normal) {
		normal.setType(POTION);
		normal.setName("HealingPotion");
		normal.setHealing(50);
		normal.setRequired(1);
		normal.setValue(10);
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

	public static final void shield_ledgendary(Item ledgendary) {
		ledgendary.setType(SHIELD);
		ledgendary.setName("Shield Ledgendary");
		ledgendary.setProtection(65);
		ledgendary.setRequired(8);
		ledgendary.setValue(200);
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
	
	public static final void axe_ledgendary(Item ledgendary) {
		ledgendary.setType(AXE);
		ledgendary.setName("Axe ledgendary");
		ledgendary.setDPS(25);
		ledgendary.setRequired(8);
		ledgendary.setValue(100);
	}
}
