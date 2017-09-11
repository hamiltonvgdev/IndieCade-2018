package Environment;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionHandler implements ContactListener
{
	Level level;
	Thing t1;
	Thing t2;
	
	ArrayList<Thing> things;
	
	public CollisionHandler(Level level)
	{
		this.level = level;
		t1 = new Thing(level.gs);
		t2 = new Thing(level.gs);
		things = new ArrayList<Thing>();
	}
	
	@Override
	public void beginContact(Contact c)
	{
		t1 = level.getThings().get(Integer.parseInt(
				c.getFixtureA().getUserData().toString().split("-")[1]));
		t2 = level.getThings().get(Integer.parseInt(
				c.getFixtureB().getUserData().toString().split("-")[1]));
		
		things = new ArrayList<Thing>();
		things.add(t1);
		things.add(t2);
		Collections.sort(things);
		
		things.get(0).collideWith(things.get(1));
		System.out.println(things.get(0));
	}

	@Override
	public void preSolve(Contact c, Manifold oldManifold)
	{
		
	}

	@Override
	public void endContact(Contact c)
	{
		t1 = level.getThings().get(Integer.parseInt(
				c.getFixtureA().getUserData().toString().split("-")[1]));
		t2 = level.getThings().get(Integer.parseInt(
				c.getFixtureB().getUserData().toString().split("-")[1]));
		
		things = new ArrayList<Thing>();
		things.add(t1);
		things.add(t2);
		Collections.sort(things);
		
		things.get(0).unCollideWith(things.get(1));
	}

	@Override
	public void postSolve(Contact c, ContactImpulse impulse)
	{
		
		
	}

}
