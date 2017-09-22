package Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class PlayerInput 
{
	Player player;
	
	public boolean Ddis;
	public boolean Adis;
	
	public PlayerInput(Player player)
	{
		this.player = player;
		Ddis = false;
		Adis = false;
	}
	
	public void update()
	{	
		if(!Ddis && Gdx.input.isKeyPressed(Input.Keys.D) && !player.inContact.isEmpty()
				&& player.getBody().getLinearVelocity().x < player.maxSpeed)
		{
			player.getBody().applyForceToCenter(new Vector2(10, 0),  true); 
		}
		
		if(!Adis && Gdx.input.isKeyPressed(Input.Keys.A) && !player.inContact.isEmpty()
				&& player.getBody().getLinearVelocity().x > -player.maxSpeed)
		{
			player.getBody().applyForceToCenter(new Vector2(-10, 0),  true); 
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.W)&& !player.inContact.isEmpty())
		{
			player.getBody().applyForceToCenter(new Vector2(0, 430),  true); 
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.S)&& !player.inContact.isEmpty())
		{
			player.getBody().applyForceToCenter(new Vector2(0, -10),  true); 
		}
	}
}
