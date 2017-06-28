package Game;

import java.io.Serializable;

public class GameData implements Serializable
{
	private static final long serialVersionUID = 1;
	
	public static String name;
	public static String currentWeapon;
	
	public static void init()
	{
		name = "";
		currentWeapon = "";
	}
}
