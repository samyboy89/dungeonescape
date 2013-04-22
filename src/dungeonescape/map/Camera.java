package dungeonescape.map;


public class Camera {

	public static final int IMG_SIZE = 32;
	public static int IMG_SCALE = 2;

	private Map map;
	
	private int mapX = 0;
	private int mapY = 0;
	private int windowX = 14;
	private int windowY = 10;
	private int offsetX, offsetY = 0;
	private int visibleTilesX, visibleTilesY = 0;

	public static final int MIN_X_OFFSET = 0;
	public static final int MIN_Y_OFFSET = 0;
	private int maxOffsetX = 0;
	private int maxOffsetY = 0;
	
	public Camera(Map map) {
		this.map = map;
	}
	
	public void setCamera() {
		setMapX(map.getMapX());
		setMapY(map.getMapY());
		setWindowX(getWindowX());
		setWindowY(getWindowY());
		setMaxOffsetX();
		setMaxOffsetY();
		calculateCurrentScreen();
	}
	
	public void getCamera() {
		calculateCurrentScreen();
	}
	
	
	private void calculateCurrentScreen() {
		offsetX = (windowX <= mapX ? (map.getPlayer().getPlayerX()) - (windowX / 2) : 0);
		if (offsetX <= MIN_X_OFFSET) {
			offsetX = MIN_X_OFFSET;
		} else if (offsetX >= getMaxOffsetX()) {
			offsetX = getMaxOffsetX();
		}
		offsetY = (windowY <= mapY ? (map.getPlayer().getPlayerY()) - (windowY / 2) : 0);
		if (offsetY <= MIN_Y_OFFSET) {
			offsetY = MIN_Y_OFFSET;
		} else if (offsetY >= getMaxOffsetY()) {
			offsetY = getMaxOffsetY();
		}
		setVisibleTilesX((windowX) + offsetX);
		setVisibleTilesY((windowY) + offsetY);
	}

	public double getBigMapMeasurments() {
		if (getMapX() > getMapY()) {
			return getWindowX() / getMapX();
		} else {
			return getWindowY() / getMapY();
		}
	}
	
	public void setMaxOffsetX() {
		this.maxOffsetX = ((getMapX() - getWindowX()));
	}

	public void setMaxOffsetY() {
		this.maxOffsetY = ((getMapY() - getWindowY()));
	}
	
	public int getMaxOffsetX() {
		return this.maxOffsetX;
	}

	public int getMaxOffsetY() {
		return this.maxOffsetY;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public int getWindowY() {
		return windowY;
	}

	public void setWindowY(int windowY) {
		this.windowY = windowY;
	}

	public int getWindowX() {
		return windowX;
	}
	
	public void setWindowX(int windowX) {
		this.windowX = windowX;
	}
	
	public int getMapY() {
		return mapY;
	}

	public void setMapY(int mapY) {
		this.mapY = mapY;
	}

	public int getMapX() {
		return mapX;
	}

	public void setMapX(int mapX) {
		this.mapX = mapX;
	}


	public int getVisibleTilesX() {
		return visibleTilesX;
	}


	public void setVisibleTilesX(int visibleTilesX) {
		this.visibleTilesX = visibleTilesX;
	}


	public int getVisibleTilesY() {
		return visibleTilesY;
	}


	public void setVisibleTilesY(int visibleTilesY) {
		this.visibleTilesY = visibleTilesY;
	}
	
}
