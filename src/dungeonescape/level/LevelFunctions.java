package dungeonescape.level;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import acm.graphics.GImage;
import dungeonescape.Main;
import dungeonescape.helper.Tile;
import dungeonescape.map.Camera;

abstract class LevelFunctions implements Level {

	public int code;
	public ArrayList<ArrayList<Integer>> list;


	public LevelFunctions() {
		list = new ArrayList<ArrayList<Integer>>();
	}
	
	public LevelFunctions(int level) {
		list = new ArrayList<ArrayList<Integer>>();
		createLevel(level);
	}
	
	@Override
	public void createLevel(int level) {
		getLevelsFromFile(level, getFileLocation());
	}
	
	@Override
	public void setLevelCode(int code) {
		this.code = code;
	}

	@Override
	public int getLevelCode() {
		return this.code;
	}

	public boolean isNotCollision(int dx, int dy) {
		int code = this.getCell(dx, dy);
		String collision_folder = isLevel(code, Tile.collision, Tile.COLLISION_IMG_PATH);
		String collisionmisc_folder = isLevel(code, Tile.collisionmisc, Tile.COLLISIONMISC_IMG_PATH);
		if (!collision_folder.equals("") || !collisionmisc_folder.equals(""))
			return false;
		return true;
	}
	
	@Override
	public ArrayList<ArrayList<Integer>> getList() {
		return this.list;
	}

	@Override
	public int getCell(int dx, int dy) {
		return this.list.get(dy).get(dx);
	}
	
	@Override
	public GImage printMap(int i, int j, int x, int y) {
		return print(i, j, x, y, Tile.PRINT_SIZE_LARGE, 0);
	}

	@Override
	public GImage printMiniMap(int i, int j, double minimap) {
		return print(i, j, 0, 0, Tile.PRINT_SIZE_SMALL, minimap);
	}

	@Override
	public Integer[] getLevelsFromFile(int level, String f) {
		Integer[] level_size = { 1, 1 };
		File file = new File(f);
		Scanner scanner = null;
		boolean foundlevel = false;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (foundlevel && line.contains("----")) {
					break;
				}
				if (foundlevel) {
					ArrayList<Integer> tempGrid = new ArrayList<Integer>();
					String[] codes = parseStoredValue(line);
					for (String s : codes) {
						try {
							tempGrid.add(Integer.parseInt(s));
						} catch (NumberFormatException e) {
							tempGrid.add(0000);
						}
					}
					list.add(tempGrid);
				}
				if (line.contains("LEVEL" + level)) {
					String level2 = line.replace("LEVEL" + level + " - ", "");
					String[] string_level_size = parseStoredLevelSize(level2);
					level_size[0] = Integer.parseInt(string_level_size[0]);
					level_size[1] = Integer.parseInt(string_level_size[1]);
					foundlevel = true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		return level_size;
	}

	public static String[] parseStoredValue(final CharSequence val) {
		if ((val == null) || "".equals(val)) {
			return null;
		}
		return ((String) val).split(":");
	}

	public static String[] parseStoredLevelSize(final CharSequence val) {
		if ((val == null) || "".equals(val)) {
			return null;
		}
		return ((String) val).split("x");
	}

	@Override
	public GImage print(int i, int j, int x, int y, int size, double minimap) {
		try {
			String imagefile = isOfLevelType(this.getCell(j, i));
			if (!imagefile.equals("")) {
				return print(i, j, x, y, size, minimap, imagefile);
			} else {
				return null;
			}
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public GImage print(int i, int j, int x, int y, int size, double minimap,
			String imagefile) {
				GImage image = null;
				if (size == Tile.PRINT_SIZE_SMALL) {
					image = new GImage(imagefile + Tile.IMG_EXTENTION,
							(j * minimap) + 896 + 20, (i * minimap) + 20);
					image.scale(minimap / Camera.IMG_SIZE, minimap
							/ Camera.IMG_SIZE);
				} else {
					image = new GImage(imagefile + Tile.IMG_EXTENTION,
							Camera.IMG_SIZE * x * Camera.IMG_SCALE,
							Camera.IMG_SIZE * y * Camera.IMG_SCALE);
					image.scale(Camera.IMG_SCALE);
				}
				imagefile = "";
				return image;
	}

	@Override
	public String isOfLevelType(int code) {
		return "";
	}

	@Override
	public String isLevel(Integer code, ArrayList<Integer> list, String file) {
		if (list.contains(code))
				return file + code;
		return "";
	}
}
