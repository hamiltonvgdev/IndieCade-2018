package Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
	public ArrayList<Boolean> WeaponUnlock;
	
	public GameData()
	{
		playerHealth = 10;
		WeaponUnlock = new ArrayList<Boolean>();
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
		WeaponUnlock = gs.getPlayer().getInventory().Unlocked;
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
