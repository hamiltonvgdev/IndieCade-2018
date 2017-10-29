package Mob;

import Player.Player;
import Screen.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;

public class Test extends Enemy
{

	public Test()
	{
		super();
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			body.applyForceToCenter(0, 10, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			body.applyForceToCenter(0, -10, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			body.applyForceToCenter(10, 0, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			body.applyForceToCenter(-10, 0, true);
		}
	}
	
	@Override
	public void render(SpriteBatch batch)
	{
		super.render(batch);
		
		play.getBoundingRectangle(play.getBone("Hitbox")).render(batch);
	}
	
}
