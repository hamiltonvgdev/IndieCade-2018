package Game;

import java.util.ArrayList;
import java.util.HashMap;

import Weapon.Weapon;

public class WeaponList 
{
	public static HashMap<String, Weapon> Weapons;

	public static ArrayList<String> WeaponNames;
	public static ArrayList<Boolean> Unlocked;
	
	public static void init()
	{
		Weapons = new HashMap<String, Weapon>();
		Unlocked = new ArrayList<Boolean>();
		WeaponNames = new ArrayList<String>();
		
		initWeapons();
		initNames();
	}
	
	private static void initWeapons()
	{
		
	}
	
	private static void initNames()
	{
		Object[] names =Weapons.keySet().toArray();
		
		
		for(int i = 0; i < names.length; i ++)
		{
			WeaponNames.add(names[i].toString());
		}
	}
	
	public static ArrayList<String> getNames()
	{
		return WeaponNames;
	}
	
	public static void setUnlock(ArrayList<Boolean> unlock)
	{
		Unlocked = unlock;
	}
	
	
}
