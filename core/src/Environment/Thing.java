package Environment;

import Entities.AI;
import Game.Config;
import Mob.Enemy;
import Player.Player;
import Screen.GameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Play;

public class Thing implements Comparable<Thing>
{
	//GameScreen
	protected GameScreen gs;
	
	//AI and id
	AI ai;
	protected String id;
	protected String name;
	
	//enemy
	Enemy enemy;
	
	//Combat
	protected float health, maxHealth;
	
	//Position
	protected float x, y;
	
	//Hitbox
	protected BodyDef bdef;
	protected Body body;
	protected FixtureDef fdef;
	protected PolygonShape shape;
	
	//Renders
	protected Drawer draw;
	protected Play play;
	
	public Thing(GameScreen gs)
	{
		this.gs = gs;
		health = 1;
	}
	public Thing setEnemy(Player player, MapProperties props){
		enemy = new Enemy();
		enemy.setEnemy(player, props);
		return this;
	}
	
	public Thing setAI(AI ai)
	{
		this.ai = ai.setThing(this);
		return this;
	}
	
	public Thing setID(int id)
	{
		this.id = "-" + id;
		return this;
	}
	
	public void update(float delta)
	{
		if(play != null)
		{
			setEnemy(gs.getPlayer(),player.Entity().get);
			play.update();
		}
		
		if(health <= 0)
		{
			die();
		}
	}
	
	public void render(SpriteBatch batch)
	{
		if(play != null)
		{
			draw.draw(play);
		}
	}
	
	public void die()
	{
		Filter f = new Filter();
	    f.categoryBits = 1;
	    f.groupIndex = 2;
	    f.maskBits = (short)0;
		
		gs.getLevel().toDie.add(this);
		body.setLinearVelocity(0, 0);
		body.setAwake(false);
		body.getFixtureList().get(0).setFilterData(f);
	}
	
	public void collideWith(Thing thing)
	{
		
	}

	public void unCollideWith(Thing thing) 
	{
		
	}
	
	public void move(Vector2 moveVec) 
	{
		body.getPosition().add(moveVec);
	}
	
	public void setPosition(float x, float y)
	{
		body.setTransform(x, y , 0);
		this.x = x;
		this.y = y;
		play.setPosition(x, y);
	}
	
	public void pause()
	{
		
	}
	
	public void resume()
	{
		
	}

	@Override
	public int compareTo(Thing t) 
	{
		 return Integer.compare(Integer.parseInt(id.split("-")[0]),
				 Integer.parseInt(t.id.split("-")[0]));
	}
	
	public Thing clone()
	{
		return new Thing(gs).setAI(ai).
				setID(Integer.parseInt(id.split("-")[1]));
	}

	//Return Methods
	public float getX(){return x;}
	public float getY() {return y;}
	public String getID() {return id;}
	public GameScreen getGS() {return gs;}
	public Body getBody() {return body;}
	public float getHealth() {return health;}
	public Play getPlay() {return play;}
	public Drawer getDraw() {return draw;}
	
}
