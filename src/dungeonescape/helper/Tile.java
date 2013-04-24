package dungeonescape.helper;

import java.util.ArrayList;
import java.util.Arrays;

public final class Tile {

	public static final int PRINT_SIZE_SMALL = 0;
	public static final int PRINT_SIZE_LARGE = 1;
	public static final String IMG_EXTENTION = ".png";

	public static final int GROUND = 0;
	public static final int COLLISION = 1;
	public static final int COLLISIONMISC = 2;
	public static final int MOVEABLE = 3;
	public static final int MISC = 4;
	public static final int TOPLAYER = 5;
	public static final int DOOR = 6;
	public static final int PICKUPITEMS = 7;

	public static final String GROUND_TXT = "levels/ground.txt";
	public static final String COLLISION_TXT = "levels/collision.txt";
	public static final String COLLISIONMISC_TXT = "levels/collisionmisc.txt";
	public static final String MOVEABLE_TXT = "levels/moveable.txt";
	public static final String MISC_TXT = "levels/misc.txt";
	public static final String DOOR_TXT = "levels/door.txt";
	public static final String TOPLAYER_TXT = "levels/toplayer.txt";
	public static final String PICKUPITEMS_TXT = "levels/items.txt";
	public static final String CHESTS_TXT = "levels/chests.txt";

	public static final String GROUND_IMG_PATH = "ground/";
	public static final String COLLISION_IMG_PATH = "collision/";
	public static final String COLLISIONMISC_IMG_PATH = "collisionmisc/";
	public static final String MISC_IMG_PATH = "misc/";
	public static final String MOVEABLE_IMG_PATH = "moveable/";
	public static final String TOPLAYER_IMG_PATH = "toplayer/";
	public static final String PICKUPITEMS_IMG_PATH = "items/";
	public static final String CHESTS_IMG_PATH = "chests/";

	public final static Integer[] ground_list = { 2001, 2002, 2003, 2004, 3001,
			7001, 7002, 7014, 7015, 7016, 7017, 7018, 7019, 7020, 7021, 7022,
			7023, 7024, 7025, 7026, 7027, 7028, 7029, 7030, 7031, 7032, 7033,
			7034, 7035, 7036, 7037, 7038, 7039, 7040, 7041, 7055, 7056, 7057 };
	public final static Integer[] collision_list = { 6001, 6002, 6003, 6005,
			6007, 6009, 6011, 6012, 6013, 6014, 6015, 6016, 6017, 6018, 6019,
			6020, 6021, 6022, 6023, 6024, 6025, 6026, 6029, 6030, 7052, 7053,
			7054, 7055, 7056, 7057, 7003, 7004, 7005, 7006, 7007, 7008, 7009,
			7010, 7011, 7012, 7013, 7048, 7049, 7050, 7051 };
	public final static Integer[] collisionmisc_list = { 3003, 3006, 3009,
			3012, 3013, 3014, 3015, 3016, 3017, 3018, 3019, 3020, 3021, 3022,
			3023, 3024, 3025, 3026, 3027, 3028, 3029, 3030, 3031, 3032, 3033,
			3034, 3035, 3036, 3037, 3038, 3039, 3040, 3041, 3042, 3043, 3044,
			3045, 3046, 3047, 3048, 3049, 3050, 3051, 3052, 3053, 3056, 3057,
			3059, 3062, 3063, 3064, 3065, 3066, 3067, 3068, 3069, 3070, 3071,
			3072, 3073, 5002, 5004, 5005, 5007, 5008, 5013, 5014, 5015, 5016,
			5017, 5018, 5019, 5020, 5021, 5022, 5023, 5024, 5025, 5030, 5031,
			5032, 5033, 5034, 5035, 5036, 5037, 5038, 5039, 5040, 5041, 5042,
			5043, 5044, 6027, 6028 };
	public final static Integer[] misc_list = { 3004, 3005, 3007, 3008, 3010,
			3011, 3054, 3055, 3058, 3060, 3061, 4001, 4002, 4003, 4004, 4005,
			4006, 4007, 4008, 4009, 4010, 4011, 4012, 4013, 4014, 4015, 4016,
			4017, 4018, 4019, 4020, 4021, 5009, 5010, 5011, 5012, 5026, 5027,
			5028, 5029, 5030, 5031, 5032, 5033, 5034, 5035, 5036, 5037, 5038,
			5039, 5040, 5041, 5042, 5043, 5044, 7058, 7059, 7042, 7043, 7044,
			7045, 7046, 7047, 6004, 6006, 6008, 6010, 6027, 6028, 5003 };
	public final static Integer[] items_list = { 9999, 9998, 9997, 9996, 9995,
			9989, 9988, 9987, 9979, 9978, 9977, 9976, 9969, 9968, 9967, 9966,
			9959, 9958, 9957, 9949, 9948, 9939, 9938, 9937, 9936, 9929, 9928,
			9927, 9926, 9919, 9918, 9917 };
	public final static Integer[] chest_list = { 5004, 5005 };
	public final static Integer[] moveable_list = { 5006, 5024, 5025 };
	public final static Integer[] toplayer_list = { 5002, 5013, 5042, 5043,
			6027 };

	public final static ArrayList<Integer> ground = new ArrayList<Integer>(
			Arrays.asList(ground_list));
	public final static ArrayList<Integer> collision = new ArrayList<Integer>(
			Arrays.asList(collision_list));
	public final static ArrayList<Integer> collisionmisc = new ArrayList<Integer>(
			Arrays.asList(collisionmisc_list));
	public final static ArrayList<Integer> misc = new ArrayList<Integer>(
			Arrays.asList(misc_list));
	public final static ArrayList<Integer> moveable = new ArrayList<Integer>(
			Arrays.asList(moveable_list));
	public final static ArrayList<Integer> toplayer = new ArrayList<Integer>(
			Arrays.asList(toplayer_list));
	public final static ArrayList<Integer> items = new ArrayList<Integer>(
			Arrays.asList(items_list));
	public final static ArrayList<Integer> chests = new ArrayList<Integer>(
			Arrays.asList(chest_list));
}
