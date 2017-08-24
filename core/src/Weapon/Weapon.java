package Weapon;

import java.util.ArrayList;

import Environment.Thing;
import Game.Config;
import Player.Player;
import Screen.GameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brashmonkey.spriter.Rectangle;
import com.brashmonkey.spriter.Timeline.Key.Bone;

public class Weapon
{
	//Important Bones
	Bone hilt;
	Bone blade;
	
	//Player and other important stuff
	Player player;
	GameScreen gs;
	String name;
	boolean equiped;
	
	//Combat
	public ArrayList<Thing> Hitted;
	
	//Play Animations
	String idle;
	String o;
	String p;
	String k;
	String l;
	
	public Weapon(String name, Player player) 
	{
		this.player = player;
		
		gs = player.getGS();
		
		this.name = name;
		equiped = false;
		
		idle = name + " Idle";
		p = name + " P";
		o = name + " O";
		l = name + " L";
		k = name + " K";
		
		Hitted = new ArrayList<Thing>();
	}
	
	public void equip()
	{
		player.getPlay().setAnimation(idle);
		hilt = player.getPlay().getBone("Hilt");
		blade = player.getPlay().getBone("Blade");
		equiped = true;
	}
	
	public void update(float delta)
	{
		if(equiped)
		{
			for(Thing thing: player.getGS().getLevel().getAlive())
			{
				if(thing.getID().split("-")[0].equals(Config.ENTITY_Z))
				{
					if(Rectangle.areIntersecting(
							thing.getPlay().getBoundingRectangle(thing.getPlay().
									getBone("Hitbox")),
							player.getPlay().getBoundingRectangle(blade))
							)
							{
								if(!Hitted.contains(thing))
								{
									attack(thing);
								}
							}	
				}
			}
		}
	}
	
	public void render(SpriteBatch batch)
	{
		if(equiped)
		{
			player.getPlay().getBoundingRectangle(blade).render(batch);
		}
	}
	
	public void attack(Thing thing)
	{
		Hitted.add(thing);
		System.out.println("Derp");
	}
	
	public void pMove()
	{
		player.getPlay().setAnimation(p);
	}
	
	public void oMove()
	{
		player.getPlay().setAnimation(o);
	}
	
	public void lMove()
	{
		player.getPlay().setAnimation(l);
	}
	
	public void kMove()
	{
		player.getPlay().setAnimation(k);
	}
	
	//Return Statements
	public Bone getBlade() {return blade;}
	public Bone getHilt() {return hilt;}
	public String getIdleAnimation() {return idle;}
	public String getPAnimation() {return p;}
	public String getOAnimation() {return o;}
	public String getLAnimation() {return l;}
	public String getKAnimation() {return k;}
	
}
