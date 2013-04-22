package dungeonescape.ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import acm.graphics.GImage;
import dungeonescape.Main;
import dungeonescape.ai.model.DijkstraAlgorithm;
import dungeonescape.ai.model.Edge;
import dungeonescape.ai.model.Graph;
import dungeonescape.ai.model.Vertex;
import dungeonescape.character.Direction;
import dungeonescape.character.DoMove;
import dungeonescape.helper.NPC_const;
import dungeonescape.helper.PlayerImg;
import dungeonescape.map.Camera;
import dungeonescape.map.Map;
import dungeonescape.player.CharacterFunctions;

public class NPC extends CharacterFunctions {
	private int ID;
	private int room;
	private Map map;
	
	// Dijkstras-algoritme
	private List<Vertex> nodes;
	private List<Edge> edges;
	private LinkedList<Vertex> path;


	// Konstruktør
	public NPC(int NPC_ID, Map map) {
		super();
		this.map = map;
		
		setID(NPC_ID);
		getNPCValuesAndKeysFromFile(NPC_ID);

		setCamera(map.camera);
		setMove(new DoMove(this, map.collision, map.collisionMisc, map.moveable));
	}
	
	private void createGraphFromMap() {

		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		
		// Konverter hvert punkt på kartet til noder

		for (int y = 0; y < map.getMapY(); y++) {
			for (int x = 0; x < map.getMapX(); x++) {
				Vertex location = new Vertex(x + "x" + y, x + "x" + y);
				nodes.add(location);
			}
		}
		
		// Lag kanter mellom hver node
		for (int y = 0; y < map.getMapY(); y++) {
			for (int x = 0; x < map.getMapX(); x++) {
				if (canMove(Direction.NORTH, new Integer[] { x , y })) {
					addLane("Edge_" + x + "x" + y + "_1", ((y*map.getMapX())+x), (((y-1)*map.getMapX())+x), 1);
				}
				if (canMove(Direction.EAST, new Integer[] { x , y })) {
					addLane("Edge_" + x + "x" + y + "_2", ((y*map.getMapX())+x), (((y)*map.getMapX())+x+1), 1);
				}
				if (canMove(Direction.SOUTH, new Integer[] { x , y })) {
					addLane("Edge_" + x + "x" + y + "_3", ((y*map.getMapX())+x), (((y+1)*map.getMapX())+x), 1);
				}
				if (canMove(Direction.WEST, new Integer[] { x , y })) {
					addLane("Edge_" + x + "x" + y + "_4", ((y*map.getMapX())+x), (((y)*map.getMapX())+x-1), 1);
				}
			}
		}
		
	}
	
	public void moveCloserToPlayer() {
		createGraphFromMap();
		
		Graph graph = new Graph(nodes, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		dijkstra.execute(nodes.get(((getCharacterY())*map.getMapX())+getCharacterX()));
		path = dijkstra.getPath(nodes.get(((Main.main.player.getCharacterY())*map.getMapX())+Main.main.player.getCharacterX()));
		
		if (path.size() > 1) {
			String[] next_move = path.get(1).toString().split("x");
			
			Direction next_move_direction = Direction.getDirection((Integer.parseInt(next_move[0])-(getCharacterX())),(Integer.parseInt(next_move[1])-(getCharacterY())));
			
			if ( getMove().canMove(next_move_direction, new Integer[] {Integer.parseInt(next_move[0]), Integer.parseInt(next_move[1])}) == DoMove.CELL_PLAYER ) {
				// Fight
			} else {
				move(next_move_direction);
			}
		}
		
	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo,
			int duration) {
		Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		edges.add(lane);
	}
	
	private boolean canMove(Direction direction, Integer[] testFromLocation) {
		int nextMove = getMove().canMove(direction, testFromLocation);
		if (nextMove == DoMove.CELL_EMPTY || nextMove == DoMove.CELL_PLAYER) {
			return true;
		}
		
		return false;
	}

	// Class functions
	public void getNPCValuesAndKeysFromFile(int ID) {
		File file = new File(NPC_const.NPC_PATH + ID + ".txt");
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				String [] key_and_value = line.split(":");
				
				if (!key_and_value[0].equals("")) {
					addValueToNPC( key_and_value );
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
	
	@Override
	public GImage getCharacterImageState(int code, int size, double minimap) {
		GImage image = null;
        lastMoveCounter = (lastMoveCounter > 3 ? 1 : lastMoveCounter);
        if (size == PlayerImg.PLAYER_MAP_SIZE_SMALL) {
                image = new GImage(PlayerImg.IMG_LOCATION
                                + (code + lastMoveCounter) + PlayerImg.IMG_EXTENTION,
                                (getCharacterX() * minimap) + 20, ((getCharacterY() * minimap) + 15));
                image.scale(minimap / Camera.IMG_SIZE, minimap / Camera.IMG_SIZE);
        } else {
                image = new GImage(PlayerImg.IMG_LOCATION
                                + (code + lastMoveCounter) + PlayerImg.IMG_EXTENTION,
                                (getCharacterX() - map.camera.getOffsetX()) * Camera.IMG_SIZE
                                                * Camera.IMG_SCALE,
                                ((getCharacterY() - map.camera.getOffsetY()) * Camera.IMG_SIZE
                                                * Camera.IMG_SCALE)
                                                - ((Camera.IMG_SIZE / 2) * Camera.IMG_SCALE));
                image.scale(Camera.IMG_SCALE);
        }
        return image;
	}
	
	private void addValueToNPC(String[] key_and_value) {
		String key = key_and_value[0];
		String value = key_and_value[1];
		
		if (key.equals("NAME")) {
			setName(value);
			
		} else if (key.equals("LIFE")) {
			setHealth(Integer.parseInt(value));
			
		} else if (key.equals("DAMAGE")) {
			setDamage(Integer.parseInt(value));
			
		} else if (key.equals("LEVEL")) {
			setLevel(Integer.parseInt(value));
			
		} else if (key.equals("ROOM")) {
			setRoom(Integer.parseInt(value));
			
		} else if (key.equals("LOCATION_X")) {
			setCharacterX(Integer.parseInt(value));
			
		} else if (key.equals("LOCATION_Y")) {
			setCharacterY(Integer.parseInt(value));
		}
		
	}
	
	@Override
	public int getCharacterState() {
		// return getID();
		return PlayerImg.WARRIOR_HEAVY;
	}
	
	// Getters and setters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}
	
	
}
