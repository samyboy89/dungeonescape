package dungeonescape.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRectangle;
import dungeonescape.Main;
import dungeonescape.map.Map;

public class GameMenu {

	private Map map;
	
	public GCanvas gcanvas = new GCanvas();
	
	
	public GameMenu(Map map) {
		this.map = map;
		this.gcanvas.setSize(Window.WINDOW_X, Window.WINDOW_Y);
		this.gcanvas.setBackground(Color.RED);
		printStartGameButton();
		printGameRulesButton();
		printQuitGameButton();
	}


	// ************ //
	// KEY LISTENER //
	// ************ //

	public void onKey(int key) {

		switch (key) {
		case KeyEvent.VK_S:
			startGame();
			break;
		}

	}
	
	// ******** //
	// GRAPHICS //
	// ******** //

	private void add(GObject gobject) {
		gcanvas.add(gobject);
	}
	
	private void printStartGameButton() {
		GImage start = new GImage("combat_back/0499.png");
		start.setSize(250, 60);
		start.setLocation(
				(Window.WINDOW_X / 2) - (start.getWidth() / 2),
				(Window.WINDOW_Y / 2) - (start.getHeight() / 2) - 65);
		GLabel label = new GLabel("Start Game (S)");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label.setLocation(
				start.getX() + (start.getWidth() / 2)
						- (label.getWidth() / 2),
				start.getY() + (start.getHeight() / 2)
						+ (label.getHeight() / 4));
		add(start);
		add(label);
		start.addMouseListener( new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				startGame();
			}
		});
	}
	
	private void printGameRulesButton() {
		GImage rules = new GImage("combat_back/0499.png");
		rules.setSize(250, 60);
		rules.setLocation(
				(Window.WINDOW_X / 2) - (rules.getWidth() / 2),
				(Window.WINDOW_Y / 2) - (rules.getHeight() / 2));
		GLabel label = new GLabel("Game Rules (G)");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label.setLocation(
				rules.getX() + (rules.getWidth() / 2)
						- (label.getWidth() / 2),
				rules.getY() + (rules.getHeight() / 2)
						+ (label.getHeight() / 4));
		add(rules);
		add(label);
		rules.addMouseListener( new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {}
		});
	}
	
	private void printQuitGameButton() {
		GImage quit = new GImage("combat_back/0499.png");
		quit.setSize(250, 60);
		quit.setLocation(
				(Window.WINDOW_X / 2) - (quit.getWidth() / 2),
				(Window.WINDOW_Y / 2) - (quit.getHeight() / 2) + 65);
		GLabel label = new GLabel("Quit Game (Q)");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label.setLocation(
				quit.getX() + (quit.getWidth() / 2)
						- (label.getWidth() / 2),
				quit.getY() + (quit.getHeight() / 2)
						+ (label.getHeight() / 4));
		add(quit);
		add(label);
		quit.addMouseListener( new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
			@Override
			public void mousePressed(MouseEvent arg0) {}
			
			@Override
			public void mouseExited(MouseEvent arg0) {}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			
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
