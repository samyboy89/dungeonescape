package dungeonescape.items;

import acm.graphics.GObject;
import dungeonescape.character.Action;

public interface Item {

	public String getName();

	public void setName(String name);

	public int getValue();

	public void setValue(int value);

	public int getDPS();

	public void setDPS(int dps);

	public int getProtection();

	public void setProtection(int protection);

	public int getHealing();

	public void setHealing(int healing);

	public GObject getView();

	public void setView(GObject gobject);

	public Action getAction();

	public void setAction(Action action);
	
	public void doAction();


}
