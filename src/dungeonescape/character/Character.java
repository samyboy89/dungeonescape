package dungeonescape.character;

import acm.graphics.GImage;
import dungeonescape.map.Camera;

public interface Character {

	public void move(Direction direction);

	public int getCharacterX();

	public void setCharacterX(int playerX);

	public int getCharacterY();

	public void setCharacterY(int playerY);

	public void addToCounter();

	public GImage getCharacterView(int size, double minimap);

	public GImage getCharacterImageState(int code, int size, double minimap);

	public int getCharacterState();
	
	public GImage getCombatView();

	public void changeGender();

	public int getGender();

	public int getCounterState();

	public void setGender(int gender);
	
	public void setCounterState(int state);
	
	public int getLastMovedState();

	public void setLastMovedState(int lastMovedState);
	
	public int getDirectionX();
	
	public int getDirectionY();

	public void setLastDirection(Direction direction);

	public boolean hasMoved();

	public void setMoved(boolean hasMoved);
	
	public int getExperience();

	public void addExperience(int exp);

	public String getName();
	
	public void setName(String name);
	
	public int getDamage();

	public void setDamage(int dmg);
	
	public int getGold();

	public void setGold(int gold);

	public int getMaxHealth();
	
	public int getHealth();

	public void setHealth(int health);
	
	public int getProtection();

	public void setProtection(int protecion);
	
	public int getLevel();

	public void setLevel(int level);
	
	public DoMove getMove();
	
	public void setMove(DoMove move);
	
	public Stats getStats();

	public void setStats(Stats stats);

	public Camera getCamera();

	public void setCamera(Camera camera);
	
	public Inventory getInventory();

	public void setInventory(Inventory inventory);
	
	public void firePlayerChange(int change);
}
