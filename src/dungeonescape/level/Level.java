package dungeonescape.level;

import java.util.ArrayList;

import acm.graphics.GImage;

public interface Level {

	public void setLevelCode(int code);
	
	public int getLevelCode();
	
	public void createLevel(int level);
	
	public ArrayList<ArrayList<Integer>> getList();
	
	public int getCell(int dx, int dy);
	
	public GImage printMap(int i, int j, int x, int y);
	
	public GImage printMiniMap(int i, int j, double minimap);
	
	public GImage print(int i, int j, int x, int y, int size, double minimap);
	
	public GImage print(int i, int j, int x, int y, int size, double minimap, String imagefile);
	
	public Integer[] getLevelsFromFile(int level, String f);
	
	public String isOfLevelType(int code);
	
	public String getFileLocation();
	
	public String toString();
	
}
