package Environment;

import com.badlogic.gdx.physics.box2d.Body;

public class Bodi 
{
	Body body;
	int id;
	
	public Bodi(Body body, int id)
	{
		this.body = body;
		this.id = id;
	}
	
	public Body getBody(){return body;}
	public int getID(){return id;}
}
