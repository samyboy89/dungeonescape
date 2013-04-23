package dungeonescape.combat;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import acm.graphics.GDimension;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import dungeonescape.map.Map;

public class Animations {

	private Map map;
	private ArrayList<AnimationDoneListener> animationDoneListeners;
	private ArrayList<AnimationDeleteListener> animationDeleteListeners;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	public static final int ATTACK = 10;
	public static final int MOVE = 11;
	
	public Animations(Map map) {
		animationDoneListeners = new ArrayList<Animations.AnimationDoneListener>();
		animationDeleteListeners = new ArrayList<Animations.AnimationDeleteListener>();
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
								fireAnimationDoneListener(MOVE);
								this.cancel();
							}
						}
					}, 100, 20);
					this.cancel();
				}
			}
		}, 100, 20);
	}
	
	public void moveForwardAndBack(final GImage gimage, final GPoint location, final GDimension size,  final int left_right) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				count++;
				gimage.setLocation(left_right == LEFT ? gimage.getX()-9 : gimage.getX()+9, gimage.getY());
				if (count == 50) {
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						int count = 0;
						@Override
						public void run() {
							count++;
							gimage.setLocation(left_right == LEFT ? gimage.getX()+9 : gimage.getX()-9, gimage.getY());
							if (count == 50) {
								gimage.setSize(size);
								gimage.setLocation(location);
								fireAnimationDoneListener(MOVE);
								this.cancel();
							}
						}
					}, 100, 10);
					this.cancel();
				}
			}
		}, 100, 10);
	}

	public void swordAttack(final GImage gimage) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				count++;
				gimage.setLocation(gimage.getX()+3, gimage.getY()-2);
				if (count == 60) {
					fireAnimationDeleteListener(gimage);
					fireAnimationDoneListener(ATTACK);
					this.cancel();
				}
			}
		}, 100, 20);
	}

	public void npcSmachAttack(final GOval image) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				count++;
				image.setLocation(count % 2 == 0 ? image.getX()+3 : image.getX()-2, count % 2 == 0 ? image.getY()-2 : image.getY()+3);
				image.setVisible(count % 5 == 0 ? false : true);
				if (count == 70) {
					fireAnimationDeleteListener(image);
					fireAnimationDoneListener(ATTACK);
					this.cancel();
				}
			}
		}, 100, 20);
	}
	
	public void npcSlashAttack(final GImage gimage) {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				count++;
				gimage.setLocation(gimage.getX()-3, gimage.getY()-2);
				if (count == 60) {
					fireAnimationDeleteListener(gimage);
					fireAnimationDoneListener(ATTACK);
					this.cancel();
				}
			}
		}, 100, 20);
	}
	
	private void fireAnimationDoneListener(int action) {
		for (AnimationDoneListener l : animationDoneListeners) {
			l.onDone(action);
		}
	}
	
	public void setAnimationDoneListener (AnimationDoneListener l) {
		if (!animationDoneListeners.contains(l)) {
			animationDoneListeners.add(l);
		}
	}
	
	public static interface AnimationDoneListener {
		void onDone(int action);
	}
	
	private void fireAnimationDeleteListener(GObject gimage) {
		for (AnimationDeleteListener l : animationDeleteListeners) {
			l.onDelete(gimage);
		}
	}
	
	public void setAnimationDeleteListener (AnimationDeleteListener l) {
		if (!animationDeleteListeners.contains(l)) {
			animationDeleteListeners.add(l);
		}
	}
	
	public static interface AnimationDeleteListener {
		void onDelete(GObject gimage);
	}

}
