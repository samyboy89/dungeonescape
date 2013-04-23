package dungeonescape.map;

public class MapHelper {

	private int level;
	private int state;
	
	public MapHelper(int state, int level) {
		this.level = level;
		this.state = state;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
