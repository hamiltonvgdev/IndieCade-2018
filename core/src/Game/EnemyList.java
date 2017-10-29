package Game;

import java.util.ArrayList;
import java.util.HashMap;

import Mob.Enemy;
import Mob.Test;

public class EnemyList 
{
	public static HashMap<String, Enemy> Entities;
	
	public EnemyList()
	{
		Entities = new HashMap<String, Enemy>();
		
		initEntities();
	}
	
	private void initEntities()
	{
		//Creates Skeleton of Entity
		Enemy derp = new Test().init(this, "Test");
	}
}
