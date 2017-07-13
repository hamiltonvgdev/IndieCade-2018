package Game;

import java.io.Serializable;

import Player.Player;
import Screen.GameScreen;

public class GameData implements Serializable
{
	private static final long serialVersionUID = 1;
	
	public String name;
	public String currentWeapon;
	
	public GameData()
	{
		
	}
	
	public void init()
	{
		name = "";
		currentWeapon = "";
	}
	
	public void write(GameScreen gs, Player player)
	{
		
	}
}
