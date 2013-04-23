package dungeonescape.combat;

import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GDimension;
import acm.graphics.GImage;
import acm.graphics.GPoint;
import dungeonescape.map.Map;

public class Animations {

	private Map map;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	public Animations(Map map) {
		this.map = map;
	}

	public void scaleUpScaleDown(final GImage gimage, final GPoint location, final GDimension size) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int count = 0;

			@Override
			public void run() {
				count++;
				gimage.scale(1.01);
				if (count == 45) {
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						int count = 0;

						@Override
						public void run() {
							count++;
							gimage.scale(0.99);
							if (count == 45) {
								gimage.setSize(size);
								gimage.setLocation(location);
								this.cancel();
							}
						}
					}, 100, 5);
					this.cancel();
				}
			}
		}, 100, 5);
	}
	
	public void moveForwardAndBack(final GImage gimage, final GPoint location, final GDimension size,  final int left_right) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int count = 0;

			@Override
			public void run() {
				count++;
				gimage.setLocation(left_right == LEFT ? gimage.getX()-8 : gimage.getX()+8, gimage.getY());
				if (count == 40) {
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						int count = 0;
						@Override
						public void run() {
							count++;
							gimage.setLocation(left_right == LEFT ? gimage.getX()+8 : gimage.getX()-8, gimage.getY());
							if (count == 40) {
								gimage.setSize(size);
								gimage.setLocation(location);
								this.cancel();
							}
						}
					}, 100, 5);
					this.cancel();
				}
			}
		}, 100, 5);
	}

}
