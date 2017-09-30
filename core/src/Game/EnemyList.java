package Game;

import java.util.HashMap;

import Mob.Mob;

public class EnemyList 
{
	public static HashMap<String, Mob> Enemies;
	
	public static void init()
	{
		Enemies = new HashMap<String, Mob>();
	}
}
