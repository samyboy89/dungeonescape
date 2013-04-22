package dungeonescape.character;

public enum Direction {
	NORTH(0, -1),
	SOUTH(0, 1),
	WEST(-1, 0),
	EAST(1, 0);
	
	private final int dx, dy;
	
	Direction(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public int dx(){
		return dx;
	}
	
	public int dy(){
		return dy;
	}
	
	public Direction opposite(){
		switch (this){
		case NORTH: return SOUTH;
		case SOUTH: return NORTH;
		case WEST: return EAST;
		case EAST: return WEST;
		
		default: return null; // Should/will never happen
		}
	}
	
	public static Direction getDirection(int x, int y) {
        switch (x) {
        case -1:
                return Direction.WEST;
        case 0:
                if (y == -1) {
                        return Direction.NORTH;
                } else if (y == 1) {
                        return Direction.SOUTH;
                }
        case 1:
                return Direction.EAST;
        }
        return Direction.NORTH;
}
}
