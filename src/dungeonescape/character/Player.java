package dungeonescape.character;

import dungeonescape.level.Collision;
import dungeonescape.level.CollisionMisc;
import dungeonescape.level.Door;
import dungeonescape.level.Moveable;
import dungeonescape.map.Camera;
import dungeonescape.player.CharacterFunctions;

public class Player extends CharacterFunctions {

	public Player() {
		super();
		setInventory(new Inventory());
		setStats(new Stats(this, getInventory()));
	}

	public Player(Camera camera, Collision collision,
			CollisionMisc collisionmisc, Moveable moveable, Door door,
			Integer location) {
		setInventory(new Inventory());
		setCamera(camera);
		setMove(new DoMove(this, collision, collisionmisc, moveable));
		setStats(new Stats(this, getInventory()));
		this.setPlayer(camera, collision, collisionmisc, moveable, door,
				location);
		
	}

	public void setPlayer(Camera camera, Collision collision,
			CollisionMisc collisionmisc, Moveable moveable, Door door,
			Integer location) {
		setInventory(new Inventory());
		setCamera(camera);
		setMove(new DoMove(this, collision, collisionmisc, moveable));
		setStats(new Stats(this, getInventory()));
		int[] playersNewPosition = getPlayersNewPosition(door, location);
		setCharacterX(playersNewPosition[0]);
		setCharacterY(playersNewPosition[1]);

	}

	public int[] getPlayersNewPosition(Door door, int location) {
		if (location == -1) {
			return new int[] { getCharacterX(), getCharacterY() };
		}
		for (int i = 0; i < door.getList().size(); i++) {
			for (int j = 0; j < door.getList().get(0).size(); j++) {
				int c = door.getCell(j, i);
				if (c == location) {
					return new int[] { j, i };
				}
			}
		}
		return new int[] { getCharacterX(), getCharacterY() };
	}
}
