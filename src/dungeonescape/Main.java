package dungeonescape;

import java.awt.event.KeyEvent;

import javax.jws.Oneway;

import acm.program.GraphicsProgram;
import dungeonescape.character.Direction;
import dungeonescape.character.Player;
import dungeonescape.helper.AePlayWave;
import dungeonescape.helper.Game;
import dungeonescape.map.Map;

@SuppressWarnings("serial")
public class Main extends GraphicsProgram {

	public static Main main;

	private Game game;

	private Player player;
	private Map map;

	@Override
	public void init() {
		main = this;
		this.player = new Player();
		super.init();
	}

	@Oneway
	@Override
	public void run() {
		this.map = new Map(player);
		this.game = new Game(player, map);
		addKeyListeners();
		super.run();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		try {
			int key = event.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				map.getPlayer().move(Direction.NORTH);
				break;
			case KeyEvent.VK_DOWN:
				map.getPlayer().move(Direction.SOUTH);
				break;
			case KeyEvent.VK_LEFT:
				map.getPlayer().move(Direction.WEST);
				break;
			case KeyEvent.VK_RIGHT:
				map.getPlayer().move(Direction.EAST);
				break;
			case KeyEvent.VK_R:
				map.restartCurrentLevel();
				break;
			case KeyEvent.VK_SPACE:
				map.moveToNextRoom();
				break;
			case KeyEvent.VK_E:
				player.addToCounter();
				break;
			case KeyEvent.VK_W:
				player.changeGender();
				break;
			case KeyEvent.VK_T:
				AePlayWave aw = new AePlayWave("sounds/0001_world.wav");
				aw.start();
				break;
			case KeyEvent.VK_Q:
				// destroy();
				// exit();
				break;
			case KeyEvent.VK_Y:
				game.clearSavedGame();
				break;
			case KeyEvent.VK_S:
				game.saveGame();
				break;
			case KeyEvent.VK_L:
				game.loadGame();
				break;
			}
			map.redrawViews();
			super.keyPressed(event);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

}
