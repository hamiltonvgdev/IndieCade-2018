package Mob;

import Game.Config;
import Game.EnemyList;
import Player.Player;
import Screen.GameScreen;
import Util.M;

import com.badlogic.gdx.maps.MapProperties;

public class Enemy extends Mob
{
	//constants 
	float between = 100;//what value set as distance
	
	Player player;
	float distance;
	
	public Enemy() 
	{		
		super(null);
	}
	
	public Enemy init(EnemyList el, String name)
	{
		el.Entities.put(name, this);
		return this;
	}
	
	public Enemy create(GameScreen gs, Player player, MapProperties props)
	{
		this.player = player;
		
		draw = gs.getRenderer().getDrawer(
				(String) props.get("Name"), 
				"Enemies/" + (String) props.get("Name") + ".scml");
		play = gs.getRenderer().getPlayer(
				(String) props.get("Name"), 
				"Enemies/" + (String) props.get("Name") + ".scml");
		
		play.scale( Float.parseFloat((String) props.get("Scale")));
		
		x = Float.parseFloat(props.get("x").toString()) / Config.PPM;
		y = Float.parseFloat(props.get("y").toString()) / Config.PPM;
		
		this.gs = gs;
		initHitbox();
		
		return this;
	}
	public void follow(float x, float y){
		distance = M.distance(x, y, this.x, this.y);
		float deltaX = 0;
		if(distance<=between && y==this.y){
			deltaX = x-this.x;
		}
		this.x = this.x+deltaX;
		
	}
}
