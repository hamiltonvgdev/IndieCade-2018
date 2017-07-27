package Game;

import java.io.Serializable;

import Player.Player;
import Screen.GameScreen;

public class GameData implements Serializable
{
	private static final long serialVersionUID = 1;
	
	public String name;
	public String currentWeapon;
	public String currentMap;
	
	public GameData()
	{
		
	};
	
	public void init()
	{
		name = "";
		currentWeapon = "";
		currentMap = "Start";
	}
	
	public void write(GameScreen gs, Player player)
	{
		
	}
}
