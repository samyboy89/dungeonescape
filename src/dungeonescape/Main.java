package dungeonescape;

import java.awt.event.KeyEvent;

import javax.jws.Oneway;

import acm.program.GraphicsProgram;
import dungeonescape.ai.NPC;
import dungeonescape.character.Direction;
import dungeonescape.character.Player;
import dungeonescape.combat.Combat;
import dungeonescape.helper.AePlayWave;
import dungeonescape.helper.Game;
import dungeonescape.helper.NPC_const;
import dungeonescape.map.Map;

@SuppressWarnings("serial")
public class Main extends GraphicsProgram {

	public static Main main;
	private Game game;
	public Player player;
	public Map map;

	private int key_state = KEY_MAP;
	public static final int KEY_MAP = 0;
	public static final int KEY_COMBAT = 1;
	public static final int KEY_MENU = 2;
	
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
		Combat combat = new Combat(player, map, new NPC(NPC_const.BALLROG));
		super.run();
		// NPC test = new NPC(NPC_const.BALLROG);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		try {
			int key = event.getKeyCode();
			switch (key_state) {
			case KEY_MAP:
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
				break;
			case KEY_COMBAT:
				switch (key) {
				case KeyEvent.VK_UP:
					
					break;
				case KeyEvent.VK_DOWN:
					
					break;
				case KeyEvent.VK_LEFT:
					
					break;
				case KeyEvent.VK_RIGHT:
					
					break;
				case KeyEvent.VK_SPACE:
					
					break;
				case KeyEvent.VK_Q:
					setKeyState(KEY_MAP);
					map.redrawViews();
					break;
				}
				break;
			case KEY_MENU:
				switch (key) {
				case KeyEvent.VK_UP:
					
					break;
				case KeyEvent.VK_DOWN:
					
					break;
				case KeyEvent.VK_LEFT:
					
					break;
				case KeyEvent.VK_RIGHT:
					
					break;
				case KeyEvent.VK_SPACE:
					
					break;
				case KeyEvent.VK_Q:
					setKeyState(KEY_MAP);
					map.redrawViews();
					break;
				}
				break;
			}
			super.keyPressed(event);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}
	
	public int getKeyState() {
		return key_state;
	}

	public void setKeyState(int key_state) {
		this.key_state = key_state;
	}

}
