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
	
	//Player stats;
	public float playerHealth;
	
	public GameData()
	{
		
	}
	
	public void init()
	{
		name = "";
		currentWeapon = "";
		currentMap = "test";
	}
	
	public void write(GameScreen gs)
	{
		playerHealth = gs.getPlayer().getHealth();
	}
	
	public boolean ready()
	{
		if(name.equals(""))
		{
			return false;
		}else
		{
			return true;
		}
	}
}
