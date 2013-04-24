package dungeonescape.items;

import java.awt.Color;

import dungeonescape.character.Action;
import dungeonescape.menu.GridItem;

public interface Item {

	public Color getBackground();
	
	public String getName();

	public void setName(String name);
	
	public int getType();

	public void setType(int type);

	public int getValue();

	public void setValue(int value);

	public int getDPS();

	public void setDPS(int dps);

	public int getProtection();

	public void setProtection(int protection);

	public int getHealing();

	public void setHealing(int healing);

	public int getRequired();
	
	public void setRequired(int required);

	public GridItem getView();

	public Action getAction();

	public void setAction(Action action);
	
	public void doAction();

	public void setActive(boolean active);
	
	public boolean isActive();

	public String getMSS();
	
	public String getSTSS();
	
	public int getParentType();


}
