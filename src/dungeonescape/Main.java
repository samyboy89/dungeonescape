package dungeonescape;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

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
	Combat combat;

	public static final int MAP = 0;
	public static final int COMBAT = 1;
	public static final int MENU = 2;
	private static int state = MAP;
	
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
		combat = new Combat(player, map, new NPC(NPC_const.BALLROG, map));
		super.run();
	
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				if (!getGCanvas().hasFocus())
					getGCanvas().requestFocus();
				
			}
		}, 500, 500);
		setSize(getWidth()+400, getHeight());
		// NPC test = new NPC(NPC_const.BALLROG);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		try {
			int key = event.getKeyCode();
			switch (state) {
			case MAP:
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
			case COMBAT:
				switch (key) {
				case KeyEvent.VK_UP:
					combat.updatePlayerHealth(+10);
					break;
				case KeyEvent.VK_DOWN:
					combat.updatePlayerHealth(-10);
					break;
				case KeyEvent.VK_LEFT:
					
					break;
				case KeyEvent.VK_RIGHT:
					combat.moveForwardAndBackGObject();
					break;
				case KeyEvent.VK_SPACE:
					combat.scaleUnScaleGObject();
					break;
				case KeyEvent.VK_Q:
					setState(MAP);
					map.redrawViews();
					break;
				}
				break;
			case MENU:
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
					setState(MAP);
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
	
	public static int getState() {
		return state;
	}

	public static void setState(int key_state) {
		state = key_state;
	}

}
