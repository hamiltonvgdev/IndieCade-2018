package Tile;

import Mob.Mob;
import Player.Player;
import Screen.GameScreen;

public class HurtTile extends Tile
{

	float dmg;
	
	public HurtTile(GameScreen gs, float x, float y) 
	{
		super(gs, x, y);
	}
	
	public HurtTile setDamage(String dmg)
	{
		this.dmg = Float.parseFloat(dmg);
		return this;
	}
	
	public void collideWithEnt(Mob ent)
	{
		if(collide)
		{
			ent.health(-dmg);
		}
	}
	
	public void collideWithPlayer(Player player)
	{
		if(collide)
		{
			player.health(-dmg);
		}
	}
}
