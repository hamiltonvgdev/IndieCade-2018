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
		if(!Ddis && Gdx.input.isKeyPressed(Input.Keys.D))
		{
			player.hitbox.torso.applyForceToCenter(new Vector2(1, 0),  true); 
		}
		
		if(!Adis && Gdx.input.isKeyPressed(Input.Keys.A))
		{
			player.hitbox.torso.applyForceToCenter(new Vector2(-1, 0),  true); 
		}
	}
}
