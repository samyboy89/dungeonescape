package dungeonescape.ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import dungeonescape.character.Direction;
import dungeonescape.helper.NPC_const;
import dungeonescape.items.Item;
import dungeonescape.player.CharacterFunctions;

public class NPC extends CharacterFunctions {
	private String Name;
	
	private int life;
	private int damage;
	private int level;
	
	private int location_x;
	private int location_y;
	
	private int room;
	
	private Direction direction;
	
	private ArrayList<Item> items;

	// Constructor
	public NPC(int NPC_ID) {
		getNPCValuesAndKeysFromFile(NPC_ID);
		direction = Direction.SOUTH;
	}
	
	// Class functions
	public void getNPCValuesAndKeysFromFile(int NPC_ID) {
		File file = new File(NPC_const.NPC_PATH + NPC_ID + ".txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				String [] key_and_value = line.split(":");
				
				if (!key_and_value[0].equals("")) {
					addValueToNPC( key_and_value );
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
	
	private void addValueToNPC(String[] key_and_value) {
		String key = key_and_value[0];
		String value = key_and_value[1];
		
		if (key.equals("NAME")) {
			setName(value);
			
		} else if (key.equals("LIFE")) {
			setLife(Integer.parseInt(value));
			
		} else if (key.equals("DAMAGE")) {
			setDamage(Integer.parseInt(value));
			
		} else if (key.equals("LEVEL")) {
			setLevel(Integer.parseInt(value));
			
		} else if (key.equals("ROOM")) {
			setRoom(Integer.parseInt(value));
			
		} else if (key.equals("LOCATION_X")) {
			setLocation_x(Integer.parseInt(value));
			
		} else if (key.equals("LOCATION_Y")) {
			setLocation_y(Integer.parseInt(value));
		}
		
	}
	
	
	private boolean canMove(Direction direction) {
		if (!willHitPlayer(direction)) {
				
		}
		return false;
	}
	
	private boolean willHitPlayer(Direction direction) {
		return false;
	}

	// Getters and setters
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getLocation_x() {
		return location_x;
	}

	public void setLocation_x(int location_x) {
		this.location_x = location_x;
	}

	public int getLocation_y() {
		return location_y;
	}

	public void setLocation_y(int location_y) {
		this.location_y = location_y;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	
}
