package Environment;

import Entities.AI;
import Screen.GameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Thing implements Comparable<Thing>
{
	
	protected GameScreen gs;
	
	AI ai;
	protected String id;
	
	float health, maxHealth;
	
	protected float x, y;
	
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
}
