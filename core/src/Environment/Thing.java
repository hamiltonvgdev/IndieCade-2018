package Environment;

import Entities.AI;
import Screen.GameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Thing implements Comparable<Thing>
{
	
	GameScreen gs;
	
	AI ai;
	protected String id;
	
	float health, maxHealth;
	
	protected float x, y;
	
	public Thing(GameScreen gs)
	{
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
	
	public void collidedWith(Thing thing)
	{
		
	}

	@Override
	public int compareTo(Thing t) 
	{
		 return Integer.compare(Integer.parseInt(id.split("-")[0]),
				 Integer.parseInt(t.id.split("-")[0]));
	}
	

	//Return Methods
	public float getX(){return x;}
	public float getY() {return y;}
}
