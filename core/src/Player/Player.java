package Player;

import Environment.Thing;
import Game.Config;
import Screen.GameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Thing
{
	//Collision
	PlayerHitbox Hitbox;
	
	//Testing
	
	public Player(GameScreen gs)
	{
		super(gs);
		
		Hitbox = new PlayerHitbox(gs, this);
	}
	
	@Override
	public Player setID(int id)
	{
		this.id = Config.PLAYER_Z + "-" + id;
		Hitbox.Foot.getBody().setUserData(Config.PLAYER_Z + "-id");
		Hitbox.Torso.getBody().setUserData(Config.PLAYER_Z + "-id");
		Hitbox.Head.getBody().setUserData(Config.PLAYER_Z + "-id");
		return this;
	}
	
	public void update(float delta)
	{
		x = Hitbox.torso.getPosition().x;
		y = Hitbox.torso.getPosition().y;
	}
	
	public void render(SpriteBatch batch)
	{
		
	}
	
	public void move()
	{
		
	}
}
