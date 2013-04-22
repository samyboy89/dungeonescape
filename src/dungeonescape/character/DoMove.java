package dungeonescape.character;

import dungeonescape.level.Collision;
import dungeonescape.level.CollisionMisc;
import dungeonescape.level.Moveable;

public class DoMove {

	private Player player;
	private Collision collision;
	private Moveable moveable;
	private CollisionMisc collisionmisc;

	public DoMove(Player player, Collision collision,
			CollisionMisc collisionmisc, Moveable moveable) {
		this.player = player;
		this.collision = collision;
		this.moveable = moveable;
		this.collisionmisc = collisionmisc;
	}

	public void setDoMove(Player player, Collision collision,
			CollisionMisc collisionmisc, Moveable moveable) {
		this.player = player;
		this.collision = collision;
		this.moveable = moveable;
		this.collisionmisc = collisionmisc;
	}
	
	public void move(Direction direction) {
		Integer[] playToPos = { player.getPlayerX() + direction.dx(),
				player.getPlayerY() + direction.dy() };
		Integer next_x = playToPos[0];
		Integer next_y = playToPos[1];
		Integer next_next_x = playToPos[0] + direction.dx();
		Integer next_next_y = playToPos[1] + direction.dy();

		// Character i = item.getCell(playToPos[0], playToPos[1]);
		if (next_y >= collision.getList().size() || next_x >= collision.getList().get(0).size() || next_x < 0 || next_y < 0) {
		} else {
			if (collision.isNotCollision(next_x, next_y)
					&& collisionmisc.isNotCollision(next_x, next_y)) {
				if (moveable.isMoveable(next_x, next_y)) {
					if (next_next_y >= collision.getList().size() || next_next_x >= collision.getList().get(0).size() || next_next_x < 0 || next_next_y < 0) {
					} else if (collision.isNotCollision(next_next_x, next_next_y)
							&& collisionmisc.isNotCollision(next_next_x,
									next_next_y)
							&& !moveable.isMoveable(next_next_x, next_next_y)) {
						player.setPlayerX(next_x);
						player.setPlayerY(next_y);
						moveable.setCell(moveable.getCell(next_x, next_y), next_next_x,
								next_next_y);
						moveable.removeCell(next_x, next_y);
					}
				} else {
					player.setPlayerX(next_x);
					player.setPlayerY(next_y);
				}
				// plukke opp om det er noe her
				// if (i != ' ') {
				// item.getItem(c, playToPos[0] + dx, playToPos[1] + dy);
				// }
				// sette den cellen man skal bevege seg inn i til playerPos

			}
		}
	}

}
