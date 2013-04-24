package dungeonescape.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import dungeonescape.Main;
import dungeonescape.map.Camera;
import dungeonescape.map.Map;

public class GameMenu {

	private Map map;
	
	public GCanvas gcanvas = new GCanvas();
	
	public GameMenu(Map map) {
		this.map = map;
		this.gcanvas.setSize(Window.WINDOW_X, Window.WINDOW_Y);
		this.gcanvas.setBackground(Color.RED);
		printStartGameButton();
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
				(Window.WINDOW_Y / 2) - (start.getHeight() / 2));
		GLabel label = new GLabel("Start Game (S)");
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		label.setLocation(
				start.getX() + (start.getWidth() / 2)
						- (label.getWidth() / 2),
				start.getY() + (start.getHeight() / 2)
						+ (label.getHeight() / 4));
		add(start);
		add(label);
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
