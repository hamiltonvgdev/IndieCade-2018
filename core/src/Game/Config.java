package Game;

import java.awt.GraphicsEnvironment;


public class Config 
{
	//Math Constants
	public static final float FOOT_2_METER = 0.3048F;
	
	
	//Game Variables
	public static final float screenScale = 2F;
	public static final float GAME_WIDTH = (int) (GraphicsEnvironment.
			getLocalGraphicsEnvironment().getDefaultScreenDevice().
			getDisplayMode().getWidth() / screenScale);
	public static final float GAME_HEIGHT = (int) (GraphicsEnvironment.
			getLocalGraphicsEnvironment().getDefaultScreenDevice().
			getDisplayMode().getHeight() / screenScale);
	public static final String GAME_TITLE = "Jump and Shoot Man";
	
	//Menu Button Variables
	public static final float MENU_BUTTON_WIDTH = 104;
	public static final float MENU_BUTTON_HEIGHT = 150;
	
	//Box 2d Variables
	public static final float PPM = 32;
	public static final short BIT_TILE = 2;
	public static final short BIT_PLAYER = 4;
	public static final short BIT_ENEMY = 6;
	public static final short BIT_PROJ = 8;
	public static final short BIT_DEAD = 10;
	
	//Player Dimensions
	public static final float HEAD_WIDTH = 1 * FOOT_2_METER;
	public static final float HEAD_HEIGHT = 1.5F * FOOT_2_METER;
	public static final float TORSO_WIDTH = 3 * FOOT_2_METER;
	public static final float TORSO_HEIGHT = 5 * FOOT_2_METER;
	public static final float FOOT_WIDTH = 2 * FOOT_2_METER;
	public static final float FOOT_HEIGHT = 1  * FOOT_2_METER;
	
	//World Dimensions
	public static final float WORLD_SCALE = 1;
	public static final String WEAPON_Z = "0";
	public static final String TILE_Z = "1";
	public static final String ENTITY_Z = "2";
	public static final String PLAYER_Z = "3";
}
