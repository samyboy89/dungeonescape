package dungeonescape.character;

import dungeonescape.Main;
import dungeonescape.level.Chests;
import dungeonescape.level.Collision;
import dungeonescape.level.CollisionMisc;
import dungeonescape.level.Moveable;
import dungeonescape.map.Map;

public class DoMove {

	public static final int CELL_EMPTY = 0;
	public static final int CELL_PLAYER = 1;
	public static final int CELL_MOVEABLE = 2;
	public static final int CELL_COLLISION = 3;
	public static final int CELL_OUT_OF_BOUNDARIES = 3;

	private dungeonescape.character.Character player;
	private Collision collision;
	private Moveable moveable;
	private CollisionMisc collisionmisc;
	private Chests chest;

	public DoMove(dungeonescape.character.Character player, Map map) {
		this.player = player;
		this.collision = map.collision;
		this.moveable = map.moveable;
		this.collisionmisc = map.collisionMisc;
		this.chest = map.chest;
	}

	public void setDoMove(Player player, Map map) {
		this.player = player;
		this.collision = map.collision;
		this.moveable = map.moveable;
		this.collisionmisc = map.collisionMisc;
		this.chest = map.chest;
	}

	public void move(Direction direction) {
		Integer[] playToPos = { player.getCharacterX() + direction.dx(),
				player.getCharacterY() + direction.dy() };
		Integer next_x = playToPos[0];
		Integer next_y = playToPos[1];
		Integer next_next_x = playToPos[0] + direction.dx();
		Integer next_next_y = playToPos[1] + direction.dy();

		if (next_y >= collision.getList().size()
				|| next_x >= collision.getList().get(0).size() || next_x < 0
				|| next_y < 0) {
		} else {
			if (collision.isNotCollision(next_x, next_y)
					&& collisionmisc.isNotCollision(next_x, next_y)
					&& chest.isNotCollision(next_x, next_y)) {
				if (moveable.isMoveable(next_x, next_y)) {
					if (next_next_y >= collision.getList().size()
							|| next_next_x >= collision.getList().get(0).size()
							|| next_next_x < 0 || next_next_y < 0) {
					} else if (collision.isNotCollision(next_next_x,
							next_next_y)
							&& collisionmisc.isNotCollision(next_next_x,
									next_next_y)
							&& chest.isNotCollision(next_next_x, next_next_y)
							&& !moveable.isMoveable(next_next_x, next_next_y)) {
						player.setCharacterX(next_x);
						player.setCharacterY(next_y);
						moveable.setCell(moveable.getCell(next_x, next_y),
								next_next_x, next_next_y);
						moveable.removeCell(next_x, next_y);
					}
				} else {
					player.setCharacterX(next_x);
					player.setCharacterY(next_y);
				}
				// plukke opp om det er noe her
				// if (i != ' ') {
				// item.getItem(c, playToPos[0] + dx, playToPos[1] + dy);
				// }
				// sette den cellen man skal bevege seg inn i til playerPos
			}
		}
	}

	public int canMove(Direction direction, Integer[] testFromLocation) {
		Integer[] playToPos = { testFromLocation[0] + direction.dx(),
				testFromLocation[1] + direction.dy() };
		Integer next_x = playToPos[0];
		Integer next_y = playToPos[1];
		Integer next_next_x = playToPos[0] + direction.dx();
		Integer next_next_y = playToPos[1] + direction.dy();
		if (next_y >= collision.getList().size()
				|| next_x >= collision.getList().get(0).size() || next_x <= 0
				|| next_y <= 0) {
			return CELL_OUT_OF_BOUNDARIES;
		} else {
			if (collision.isNotCollision(next_x, next_y)
					&& collisionmisc.isNotCollision(next_x, next_y)) {
				if (moveable.isMoveable(next_x, next_y)) {
					if (next_next_y >= collision.getList().size()
							|| next_next_x >= collision.getList().get(0).size()
							|| next_next_x < 0 || next_next_y < 0) {
						return CELL_COLLISION;
					} else if (collision.isNotCollision(next_next_x,
							next_next_y)
							&& collisionmisc.isNotCollision(next_next_x,
									next_next_y)
							&& chest.isNotCollision(next_next_x, next_next_y)
							&& !moveable.isMoveable(next_next_x, next_next_y)) {
						return CELL_MOVEABLE;
					}
				} else if (playToPos[0] == Main.main.player.getCharacterX()
						&& playToPos[1] == Main.main.player.getCharacterY()) {
					return CELL_PLAYER;
				} else {
					return CELL_EMPTY;
				}
			}
			return CELL_COLLISION;
		}
	}
}
