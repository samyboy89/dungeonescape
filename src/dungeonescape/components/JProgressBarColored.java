package dungeonescape.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class JProgressBarColored extends JComponent {

	private static final long serialVersionUID = -8975294828927240779L;

	private Rectangle clipRect = new Rectangle(); // creating new rectangular
													// area to draw on

	private double accuracy = 100; // how accurately the progress bar is drawn
	private double percentage = 0; // progress bar percentage (%)
	private double maximumValue = 100; // maximum value of progress bar
	private double value = 0; // value of progress bar

	private volatile Color backgroundColor = Color.black; // background color of
															// progress bar
	private volatile Color foregroundColor = Color.green; // foreground color of
															// progress bar

	private int hPadding = 10; // horizontal padding
	private int vPadding = 10; // vertical padding

	/**
	 * 
	 * Creates a new ProgressBarColored with a maximum value and initial
	 * starting value
	 * 
	 * @param maximumValue
	 * @param initialValue
	 */
	public JProgressBarColored(final int maximumValue, int initialValue) {
		this.maximumValue = maximumValue;
		this.value = initialValue;
		this.setDoubleBuffered(true);
		setValue(value);

		setPreferredSize(new Dimension(100, 30));
		setMinimumSize(new Dimension(10, 5));
	}

	@Override
	protected void paintComponent(Graphics g) {
		accuracy = getWidth() / maximumValue;

		g.getClipBounds(clipRect); // get progress bar's drawing area
		Graphics2D g2d = (Graphics2D) g.create(); // create Graphics2D object
													// based on clipped graphics
													// area

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON); // set rendering hints
		drawBackground(g2d); // draw progress bar background
		drawForground(g2d); // draw progress bar progress graphic in foreground

		g2d.dispose(); // clean up
	}

	private void drawBackground(Graphics2D g2d) {
		g2d.setColor(backgroundColor);
		g2d.fillRect(hPadding / 2, vPadding / 2, getWidth() - hPadding,
				getHeight() - vPadding);
	}

	private void drawForground(Graphics2D g2d) {
		g2d.setColor(foregroundColor);
		g2d.fillRect(
				hPadding / 2,
				vPadding / 2,
				(int) ((getWidth() - hPadding) / ((maximumValue * accuracy) / (value * accuracy))),
				getHeight() - vPadding);
	}

	/**
	 * 
	 * Sets the value of the progress bar and automatically assigns the color of
	 * it
	 * 
	 * @param newValue
	 */
	public void setValue(double newValue) {
		value = newValue;
		accuracy = getWidth() / maximumValue;
		percentage = ((getValue() * 1.0) / getMaximum()) * 100;

		repaint();
	}

	public double getValue() {
		return this.value;
	}

	public void setMaximumValue(double maximumValue) {
		this.maximumValue = maximumValue;
		repaint();
	}

	public double getMaximum() {
		return this.maximumValue;
	}

	public double getPercentage() {
		return this.percentage;
	}

	@Override
	public void setForeground(Color color) {
		this.foregroundColor = color;
	}

	@Override
	public Color getForeground() {
		return this.foregroundColor;
	}

	@Override
	public void setBackground(Color color) {
		this.backgroundColor = color;
	}

	@Override
	public Color getBackground() {
		return this.backgroundColor;
	}

}
