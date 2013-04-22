package dungeonescape.map;

import java.awt.Color;
import java.awt.Font;
import java.util.Timer;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import dungeonescape.Main;
import dungeonescape.helper.Levels;

public class OverlayText {

	private Map map;
	private boolean doneShowing = false;
	private GObject o;
	
	public java.util.Timer timer;

	private int SECONDS = 1000;

	public OverlayText(Map map) {
		this.map = map;
	}

	public boolean isDoneShowing() {
		return doneShowing;
	}

	public void setDoneShowing(boolean doneShowing) {
		this.doneShowing = doneShowing;
	}

	public void printLables() {
		if (timer != null)
			timer.cancel();
		doneShowing = false;
		String level = getLevelFromCharacter(map.getLevelCode());
		GLabel l = new GLabel(level);
		l.setColor(Color.WHITE);
		l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		l.setLocation((map.camera.getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE)
				- (l.getWidth()) - 60, 60);
		o = l;
		Main.main.add(o);
		timer = new Timer();
		timer.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				doneShowing = true;
				Main.main.remove(o);
			}
		}, 3 * SECONDS);
	}

	public void printLablesAgain() {
		String level = getLevelFromCharacter(map.getLevelCode());
		GLabel l = new GLabel(level);
		l.setColor(Color.WHITE);
		l.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		l.setLocation((map.camera.getWindowX() * Camera.IMG_SIZE * Camera.IMG_SCALE)
				- (l.getWidth()) - 60, 60);
		o = l;
		Main.main.add(o);
	}

	private String getLevelFromCharacter(int code) {
		switch (code) {
		case Levels.ROOMA:
			return "Room A";
		case Levels.ROOMB:
			return "Room B";
		case Levels.ROOMC:
			return "Room C";
		case Levels.ROOMD:
			return "Room D";
		case Levels.ROOME:
			return "Room E";
		case Levels.ROOMF:
			return "Room F";
		case Levels.ROOMG:
			return "Room G";
		case Levels.ROOMH:
			return "Room H";
		case Levels.ROOMI:
			return "Room I";
		case Levels.ROOMJ:
			return "Kings tomb";
		case Levels.ROOMa:
			return "Lucky secret";
		case Levels.ROOMe:
		case Levels.ROOMf:
		case Levels.ROOMg:
		case Levels.ROOMh:
		case Levels.ROOMX:
		case Levels.ROOMY:
		case Levels.ROOM1:
			return "Garden";
		default:
			return "Glitch";
		}
	}

}
