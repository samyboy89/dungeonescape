package dungeonescape.level;

import dungeonescape.Main;
import dungeonescape.character.CharacterFunctions;
import dungeonescape.helper.Levels;
import dungeonescape.helper.Tile;
import dungeonescape.helper.Type;
import dungeonescape.items.Item;

public class Door extends LevelFunctions {

	public Door() {
		super();
	}
	
	public Door(int level) {
		super(level);
	}

	@Override
	public String getFileLocation() {
		return Tile.DOOR_TXT;
	}
	
	public boolean isDoor(int room) {
		if (room == Levels.ROOME) {
			// F¿rste pulje med n¿kler
			if (Main.main.player.getInventory().getKeysList().size() == 3 || Main.main.player.getKeyRounds() >= 1) {
				Main.main.player.getInventory().clearKeyList();
				Main.main.player.setKeyRounds(1);
				Main.main.player.firePlayerChange(CharacterFunctions.CHANGE_KEY);
				return true;
			} else {
				// Gi beskjed
				Main.main.map.overlayText.printKeyRequirements();
				return false;
			}
			
			
		} else if (room == Levels.ROOM1) {
			// Andre pulje med n¿kler
			if (Main.main.player.getInventory().getKeysList().size() == 3 || Main.main.player.getKeyRounds() >= 2) {
				Main.main.player.getInventory().clearKeyList();
				Main.main.player.setKeyRounds(2);
				Main.main.player.firePlayerChange(CharacterFunctions.CHANGE_KEY);
				return true;
			} else {
				// Gi beskjed
				Main.main.map.overlayText.printKeyRequirements();
				return false;
			}
		} else if (room == Levels.ROOM2) {
			// Siste pulje med n¿kler
			if (Main.main.player.getInventory().getKeysList().size() == 3) {
				// Vunnet
				Main.main.map.printVictory();
				return true;
			} else {
				// Gi beskjed
				Main.main.map.overlayText.printKeyRequirements();
				return false;
			}
		}
		
		switch(room) {
		case Levels.ROOMA:
		case Levels.ROOMB:
		case Levels.ROOMC:
		case Levels.ROOMD:
		case Levels.ROOME:
		case Levels.ROOMF:
		case Levels.ROOMG:
		case Levels.ROOMH:
		case Levels.ROOMI:
		case Levels.ROOMJ:
		case Levels.ROOMa:
		case Levels.ROOMe:
		case Levels.ROOMf:
		case Levels.ROOMg:
		case Levels.ROOMh:
		case Levels.ROOMX:
		case Levels.ROOMY:
		case Levels.ROOM1:
		case Levels.ROOM2:
			return true;
		default:
			return false;
		}
		
	}
}
