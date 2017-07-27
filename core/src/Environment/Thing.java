package Environment;

import Entities.AI;
import Screen.GameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Thing implements Comparable<Thing>
{
	//GameScreen
	protected GameScreen gs;
	
	//AI and id
	AI ai;
	protected String id;
	
	//Combat
	float health, maxHealth;
	
	//Position
	protected float x, y;
	
	//Hitbox
	protected BodyDef bdef;
	protected Body body;
	protected FixtureDef fdef;
	protected PolygonShape shape;
	
	public Thing(GameScreen gs)
	{
		this.gs = gs;
		health = 0;
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
		if(health <= 0)
		{
			die();
		}
	}
	
	public void render(SpriteBatch batch)
	{
		
	}
	
	public void die()
	{
		gs.getLevel().toDie.add(this);
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
		System.out.println(moveVec);
	}
	
	public void setPosition(float x, float y)
	{
		body.getPosition().set(x, y);
//		this.x = x;
//		this.y = y;
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

}
