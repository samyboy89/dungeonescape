package dungeonescape.items;

import acm.graphics.GObject;
import dungeonescape.character.Action;

public abstract class ItemMain implements Item {

	private String name;
	private int type;
	private int value;
	private int dps;
	private int protection;
	private int healing;
	private int required;
	private GObject view;
	private Action action;
	private boolean isActive;
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getDPS() {
		return this.dps;
	}

	public void setDPS(int dps) {
		this.dps = dps;
	}

	public int getProtection() {
		return this.protection;
	}

	public void setProtection(int protection) {
		this.protection = protection;
	}

	public int getHealing() {
		return this.healing;
	}

	public void setHealing(int healing) {
		this.healing = healing;
	}
	
	public int getRequired() {
		return this.required;
	}
	
	public void setRequired(int required) {
		this.required = required;
	}

	public GObject getView() {
		return this.view;
	}

	public void setView(GObject gobject) {
		this.view = gobject;
	}

	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	public void doAction() {
		
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
