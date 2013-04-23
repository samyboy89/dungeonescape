package dungeonescape.components;

import java.awt.Color;

public class JProgressBarColoredExperience extends JProgressBarColored {

	private static final long serialVersionUID = 4630010912604045235L;

	private Color endColor = new Color(31, 123, 217);

	public JProgressBarColoredExperience(int maximumValue, int initialValue) {
		super(maximumValue, initialValue);
	}

	@Override
	public void setValue(double newValue) {
		super.setValue(newValue);
		setBackground(Color.white);
		setForeground(endColor);
		repaint();
	}

}