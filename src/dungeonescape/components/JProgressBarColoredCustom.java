package dungeonescape.components;

import java.awt.Color;

public class JProgressBarColoredCustom extends JProgressBarColored {

	private static final long serialVersionUID = 4630010912604045235L;

	private Color startColor = new Color(0, 255, 0);
	private Color upperColor = new Color(255, 255, 0);
	private Color middleColor = new Color(228, 121, 34);
	private Color endColor = new Color(204, 0, 0);

	public JProgressBarColoredCustom(int maximumValue, int initialValue) {
		super(maximumValue, initialValue);
	}

	@Override
	public void setValue(double newValue) {
		super.setValue(newValue);

		if (getPercentage() <= 100 && getPercentage() >= 60) {
			setForeground(startColor);
		} else if (getPercentage() >= 35 && getPercentage() < 60) {
			setForeground(upperColor);
		} else if (getPercentage() >= 20 && getPercentage() < 35) {
			setForeground(middleColor);
		} else if (getPercentage() >= 0 && getPercentage() < 20) {
			setForeground(endColor);
		}

		repaint();
	}

}