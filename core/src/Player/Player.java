package Player;

import Environment.Thing;
import Game.Config;
import Screen.GameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Player extends Thing
{
	//Input
	PlayerInput input;
	
	//Collision
	PlayerHitbox hitbox;
	
	//Testing
	
	public Player(GameScreen gs)
	{
		super(gs);
		
		input = new PlayerInput(this);	
		
		hitbox = new PlayerHitbox(gs, this);
	}
	
	@Override
	public Player setID(int id)
	{
		this.id = Config.PLAYER_Z + "-" + id;
		return this;
	}
	
	public void update(float delta)
	{	
		getGS().getGeoCam().position.set(x * Config.PPM, y * Config.PPM, 0);
		
		x = hitbox.torso.getPosition().x;
		y = hitbox.torso.getPosition().y;
		
		input.update();
	}
	
	public void render(SpriteBatch batch)
	{
		
	}
	
	public void move()
	{
		
	}
	
	@Override
	public void move(Vector2 movveVec){}
	@Override 
	public void setPosition(float x, float y){}
	
	//Return Statements
	public PlayerHitbox getHitbox() {return hitbox;}
	public PlayerInput getInput() {return input;}
	
}
