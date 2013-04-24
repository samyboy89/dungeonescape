package dungeonescape.helper;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import dungeonescape.Main;
import dungeonescape.map.Map;

public class GameMenu {

	private Map map;

	private int state = 0;
	public static final int MENU = 0;
	public static final int RULES = 1;
	
	GCanvas rules;

	public final static String IMG_LOCATION = "gamemenu/";
	public final static String IMG_PNG = ".png";

	public final static String IMG_LOGO = IMG_LOCATION + "logo" + IMG_PNG;
	public final static String IMG_BUTTON = IMG_LOCATION + "button" + IMG_PNG;

	public GCanvas gcanvas = new GCanvas();

	public GameMenu(Map map) {
		this.map = map;
		this.gcanvas.setSize(Window.WINDOW_X, Window.WINDOW_Y);
		this.gcanvas.setBackground(Color.BLACK);

		printLogo();
		printStartGameButton();
		printGameRulesButton();
		printQuitGameButton();
	}

	// ************ //
	// KEY LISTENER //
	// ************ //

	public void onKey(int key) {
		if (state == MENU) {
			switch (key) {
			case KeyEvent.VK_S:
				startGame();
				break;
			case KeyEvent.VK_Q:
				System.exit(0);
				break;
			case KeyEvent.VK_K:
				printGameRules();
				break;
			}
			
		} else if (state == RULES) {
			switch (key) {
			case KeyEvent.VK_SPACE:
				gcanvas.remove(rules);
				state = MENU;
				break;
			}
		}

	}

	// ******** //
	// GRAPHICS //
	// ******** //

	private void add(GObject gobject) {
		gcanvas.add(gobject);
	}

	private void printLogo() {
		GImage logo = new GImage(IMG_LOGO);
		logo.setSize((Window.WINDOW_X * 2) / 3, 100);
		logo.setLocation((Window.WINDOW_X / 2) - (logo.getWidth() / 2),
				(Window.WINDOW_Y / 2) - (logo.getHeight() / 2) - 130);
		add(logo);

	}

	private void printGameRules() {
		state = RULES;
		rules = new GCanvas();
		rules.setSize(Window.WINDOW_X / 2, Window.WINDOW_Y / 2);
		rules.setLocation(Window.WINDOW_X / 4, Window.WINDOW_Y / 4);
		GImage image = new GImage(IMG_LOCATION + "keys" + IMG_PNG);
		rules.add(image);
		gcanvas.add(rules);
	}

	private void printStartGameButton() {
		GImage start = new GImage(IMG_BUTTON);
		start.setSize(250, 60);
		start.setLocation((Window.WINDOW_X / 2) - (start.getWidth() / 2),
				(Window.WINDOW_Y / 2) - (start.getHeight() / 2));
		GLabel label = new GLabel("Start Game (S)");
		label.setFont(Main.main.font.deriveFont(20f));
		label.setLocation(
				start.getX() + (start.getWidth() / 2) - (label.getWidth() / 2),
				start.getY() + (start.getHeight() / 2)
						+ (label.getHeight() / 4));
		add(start);
		add(label);
		start.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				startGame();
			}
		});
	}

	private void printGameRulesButton() {
		GImage rules = new GImage(IMG_BUTTON);
		rules.setSize(250, 60);
		rules.setLocation((Window.WINDOW_X / 2) - (rules.getWidth() / 2),
				(Window.WINDOW_Y / 2) - (rules.getHeight() / 2) + 65);
		GLabel label = new GLabel("Key bindings (K)");
		label.setFont(Main.main.font.deriveFont(20f));
		label.setLocation(
				rules.getX() + (rules.getWidth() / 2) - (label.getWidth() / 2),
				rules.getY() + (rules.getHeight() / 2)
						+ (label.getHeight() / 4));
		add(rules);
		add(label);
		rules.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				printGameRules();
			}
		});
	}

	private void printQuitGameButton() {
		GImage quit = new GImage(IMG_BUTTON);
		quit.setSize(250, 60);
		quit.setLocation((Window.WINDOW_X / 2) - (quit.getWidth() / 2),
				(Window.WINDOW_Y / 2) - (quit.getHeight() / 2) + 130);
		GLabel label = new GLabel("Quit Game (Q)");
		label.setFont(Main.main.font.deriveFont(20f));
		label.setLocation(
				quit.getX() + (quit.getWidth() / 2) - (label.getWidth() / 2),
				quit.getY() + (quit.getHeight() / 2) + (label.getHeight() / 4));
		add(quit);
		add(label);
		quit.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
	}

	// ******* //
	// ACTIONS //
	// ******* //

	private void startGame() {
		Main.main.getGCanvas().add(Main.main.map.gcanvas);
		Main.main.getGCanvas().add(Main.main.menu.gcanvas);
		Main.main.getGCanvas().remove(gcanvas);
		Main.main.setState(Main.MAP);
	}
}
