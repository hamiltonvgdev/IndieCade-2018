package Tile;

import Environment.Thing;
import Game.Config;
import Mob.Mob;
import Player.Player;
import Screen.GameScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Tile extends Thing
{
	boolean collide;
	
	int inContactIndex;
	
	ChainShape shape;
	
	public Tile(GameScreen gs, float x, float y)
	{
		super(gs);
		collide = true;
		
//		shape = new PolygonShape();
//		shape.setAsBox(0.5F, 0.5F);

		//Do some logic, get the coordinates. Starbucks tmrw!
		shape = new ChainShape();
		Vector2[] v = new Vector2[5];
		v[0] = new Vector2(-32 / 2 / Config.PPM, -32 / 2 / Config.PPM);
		v[1] = new Vector2(-32 / 2 / Config.PPM, 32 / 2 / Config.PPM);
		v[2] = new Vector2(32 / 2 / Config.PPM, 32 / 2 / Config.PPM);
		v[3] = new Vector2(32 / 2 / Config.PPM, -32 / 2 / Config.PPM);
		v[4] = new Vector2(-32 / 2 / Config.PPM, -32 / 2 / Config.PPM);
		shape.createChain(v);
		
		this.x = x;
		this.y = y;
		
		
		bdef = new BodyDef();
		bdef.position.set(x, y);
		bdef.type = BodyType.KinematicBody;
		body = gs.getWorld().createBody(bdef);
		
		fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.4F;
		fdef.restitution = 0;
		fdef.filter.categoryBits = Config.BIT_TILE;
		
		inContactIndex = -1;
	}
	
	@Override
	public Tile setID(int id)
	{
		this.id = Config.TILE_Z + "-" + id;
		body.createFixture(fdef).setUserData(Config.TILE_Z + "-" + id);
		shape.dispose();
		return this;
	}
	
	@Override
	public void collideWith(Thing thing)
	{
		if(thing.getID().split("-")[0].equals(Config.ENTITY_Z))
		{
			collideWithEnt((Mob) thing);
		}else if(thing.getID().split("-")[0].equals(Config.PLAYER_Z))
		{
			collideWithPlayer((Player) thing);
		}
	}
	
	@Override
	public void unCollideWith(Thing thing)
	{
		if(thing.getID().split("-")[0].equals(Config.ENTITY_Z))
		{
			uncollideWithEnt((Mob) thing);
		}else if(thing.getID().split("-")[0].equals(Config.PLAYER_Z))
		{
			uncollideWithPlayer((Player) thing);
		}
	}
	
	public void collideWithEnt(Mob ent)
	{
		if(collide)
		{
			
		}
	}
	
	public void collideWithPlayer(Player player)
	{
		if(collide)
		{
			player.inContact.add(this);
		}
	}
	
	public void uncollideWithEnt(Mob ent)
	{
		
	}
	
	public void uncollideWithPlayer(Player player)
	{
		if(collide)
		{
			player.inContact.remove(this);
		}
	}
}
