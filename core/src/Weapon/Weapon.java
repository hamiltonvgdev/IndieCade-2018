package Weapon;

import java.util.ArrayList;

import Environment.Thing;
import Game.Config;
import Game.Core;
import Game.WeaponList;
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
		
		idle = name + "Still";
		p = name + "SwordCombo3";
		o = name + " O";
		l = name + " L";
		k = name + " K";
		
		Hitted = new ArrayList<Thing>();
	}
	
	public void init(WeaponList wl)
	{
		wl.Weapons.put(name, this);
		wl.WeaponNames.add(name);
		wl.Unlocked.add(false);
	}
	
	public void unlock(Core core)
	{
		int index = core.wl.WeaponNames.indexOf(name);
		core.wl.Unlocked.set(index, true);
	}
	
	public void equip()
	{
		player.getWeapon().unequip();
		player.setWeapon(this);
		hilt = player.getPlay().getBone("Hilt");
		blade = player.getPlay().getBone("Blade");
		equiped = true;
	}
	
	public void unequip()
	{
		equiped = false;
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
	}
	
	public void pMove()
	{
		System.out.println("p");
	}
	
	public void oMove()
	{
		System.out.println("o");
	}
	
	public void lMove()
	{
		System.out.println("l");
	}
	
	public void kMove()
	{
		System.out.println("k");
	}
	
	//Return Statements
	public Bone getBlade() {return blade;}
	public Bone getHilt() {return hilt;}
	public String getStillAnimation() {return idle;}
	public String getPAnimation() {return p;}
	public String getOAnimation() {return o;}
	public String getLAnimation() {return l;}
	public String getKAnimation() {return k;}
	
}
