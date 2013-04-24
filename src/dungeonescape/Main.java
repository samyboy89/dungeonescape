package dungeonescape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.jws.Oneway;
import javax.swing.JFrame;

import acm.program.GraphicsProgram;
import dungeonescape.ai.NPC;
import dungeonescape.character.Direction;
import dungeonescape.character.Player;
import dungeonescape.combat.Combat;
import dungeonescape.helper.AePlayWave;
import dungeonescape.helper.Game;
import dungeonescape.helper.GameMenu;
import dungeonescape.map.Map;
import dungeonescape.menu.RightMenu;

@SuppressWarnings("serial")
public class Main extends GraphicsProgram {

	public static Main main;
	private Game game;
	private GameMenu gameMenu;
	public Player player;
	public NPC npc;
	public Map map;
	public Combat combat;
	public RightMenu menu;

	public static final int MAP = 0;
	public static final int COMBAT = 1;
	public static final int MENU = 2;
	public static final int GAMEMENU = 3;
	private static int state = GAMEMENU;

	private boolean isCombat = false;

	private AePlayWave aw;

	@Override
	public void init() {
		// initWindow();
		setBackground(Color.BLACK);
		main = this;
		this.player = new Player();
		super.init();
	}

	@Oneway
	@Override
	public void run() {
		this.map = new Map(player);
		this.menu = new RightMenu(map);
		this.game = new Game(player, map);
		this.gameMenu = new GameMenu(map);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				if (!getGCanvas().hasFocus())
					getGCanvas().requestFocus();

			}
		}, 500, 500);
		getGCanvas().add(gameMenu.gcanvas);
		addKeyListeners();
		super.run();
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
					aw = new AePlayWave("sounds/0001_world.wav");
					aw.start();
					break;
				case KeyEvent.VK_Q:
					// destroy();
					// exit();
					player.addExperience(10);
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
				if (isCombat) {
					startCombat();
				} else {
					map.redrawViews();
				}
				break;
			case COMBAT:
				if (combat != null)
					combat.onKey(key);
				break;
			case GAMEMENU:
				if (gameMenu != null)
					gameMenu.onKey(key);
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
					setCombat(false);
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

	public void startCombat() {
		combat = new Combat(player, map, npc);
	}

	public int getState() {
		return state;
	}

	public void setState(int key_state) {
		state = key_state;
	}

	public int isCombat() {
		return state;
	}

	public void setCombat(boolean iscombat) {
		isCombat = iscombat;
	}

	private void initWindow() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Window window = new Window();
		gs[0].setFullScreenWindow(window);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		getGCanvas().setSize(screenSize);
		int x = screenSize.width;
		int y = screenSize.height;
		getGCanvas().setLocation(((x / 2) - 648), ((y / 2) - 320));
		window.setBackground(Color.black);
		window.add(getGCanvas());
	}

	public class Window extends JFrame {
		Window() {
			super();
			this.setLocation(0, 0);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setSize(screenSize);
			this.setResizable(false);
			this.setUndecorated(true);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					if (aw.isAlive())
						aw.stopMusic();
				}
			});
		}
	}
}
