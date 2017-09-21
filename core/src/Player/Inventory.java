package Player;

import java.util.ArrayList;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import Game.GameData;
import Weapon.Weapon;

public class Inventory 
{
	public ArrayList<String> Names;
	public ArrayList<Boolean> Unlocked;
	public HashMap<String, Weapon> Weapons;
	
	public Inventory(GameData gd, Player player)
	{
		Names = new ArrayList<String>();
		Unlocked = new ArrayList<Boolean>();
		Weapons = new HashMap<String, Weapon>();
		
		init(gd, player);
	}
	
	public void init(GameData gd, Player player)
	{	
		initWeapons(player);
		
		if(Unlocked.size() == gd.WeaponUnlock.size())
		{
			Unlocked = gd.WeaponUnlock;
		}else
		{
			throw new RuntimeErrorException(null, "Game Data Unlock and Current Unlock "
					+ "does not match up!");
		}
	}
	
	
	
	private void initWeapons(Player player)
	{
		//Initializes all Weapons in Game
		
	}
}
