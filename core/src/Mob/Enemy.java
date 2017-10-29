package Mob;

import Game.Config;
import Game.EnemyList;
import Player.Player;
import Screen.GameScreen;

import com.badlogic.gdx.maps.MapProperties;

public class Enemy extends Mob
{
	Player player;
	
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
}
