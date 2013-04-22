package dungeonescape.helper;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import dungeonescape.character.Direction;
import dungeonescape.character.Player;
import dungeonescape.map.Map;

public class Game {

	private Preferences prefs;
	private Player player;
	private Map map;

	public Game(Player player, Map map) {
		this.prefs = Preferences.userNodeForPackage(dungeonescape.Main.class);
		this.player = player;
		this.map = map;
	}

	public void saveGame() {
		prefs.putInt(PrefKey.PLAYER_X, player.getCharacterX());
		prefs.putInt(PrefKey.PLAYER_Y, player.getCharacterY());
		prefs.putInt(PrefKey.PLAYER_GENDER, player.getGender());
		prefs.putInt(PrefKey.PLAYER_STATE, player.getCharacterState());
		prefs.putInt(PrefKey.PLAYER_DIRECTION_X, player.getDirectionX());
		prefs.putInt(PrefKey.PLAYER_DIRECTION_Y, player.getDirectionY());
		prefs.putInt(PrefKey.PLAYER_DIRECTION_STATE, player.getLastMovedState());
		prefs.putInt(PrefKey.CURRENT_LEVEL, map.getLevelCode());
	}

	public void loadGame() {
		player.setCharacterX(prefs.getInt(PrefKey.PLAYER_X, 4));
		player.setCharacterY(prefs.getInt(PrefKey.PLAYER_Y, 4));
		player.setGender(prefs.getInt(PrefKey.PLAYER_GENDER, 0));
		player.setCounterState(prefs.getInt(PrefKey.PLAYER_STATE, 0));
		player.setLastMovedState(prefs
				.getInt(PrefKey.PLAYER_DIRECTION_STATE, 0));
		player.setLastDirection(getDirection(
				(prefs.getInt(PrefKey.PLAYER_DIRECTION_X, 1)),
				(prefs.getInt(PrefKey.PLAYER_DIRECTION_Y, 1))));
		map.resetLevelCode();
		map.setLevelCode(prefs.getInt(PrefKey.CURRENT_LEVEL, 0));
	}

	public void clearSavedGame() {
		try {
			prefs.clear();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	private Direction getDirection(int x, int y) {
		if (x == 0 && y == -1) {
			return Direction.NORTH;
		} else if (x == 0 && y == 1) {
			return Direction.SOUTH;
		} else if (x == -1 && y == 0) {
			return Direction.WEST;
		} else {
			return Direction.EAST;
		}
	}
}