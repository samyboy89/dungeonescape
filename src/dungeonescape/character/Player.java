package dungeonescape.character;

import dungeonescape.helper.Type;
import dungeonescape.level.Door;
import dungeonescape.map.Map;

public class Player extends CharacterFunctions {
	
	public Player() {
		super();
		setName("Dragonborn");
		setLife(3);
		setHealth(135);
		setDamage(33);
		// getInventory().addKey(Type.key_epic());
		getInventory().addItem(Type.hands_legendary());
	}

	public Player(Map map) {
		setMap(map);
		setCamera(map.camera);
		setMove(new DoMove(this, map));
		this.setPlayer(map);
	}
	
	public void setPlayer(Map map) {
		setMap(map);
		setCamera(map.camera);
		setMove(new DoMove(this, map));
		int[] playersNewPosition = getPlayersNewPosition(map.door, map.location);
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
