package Mob;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brashmonkey.spriter.Rectangle;

import Environment.Thing;
import Game.Config;
import Screen.GameScreen;

public class Test extends Mob
{

	public Test(GameScreen gs)
	{
		super(gs);
		
		draw = gs.getRenderer().getDrawer("Circle", "test/mouse test/Basic Circle.scml");
		play = gs.getRenderer().getPlayer("Circle", "test/mouse test/Basic Circle.scml");
		
		play.scale(10);
		
		initHitbox();
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			body.applyForceToCenter(0, 1, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			body.applyForceToCenter(0, -1, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			body.applyForceToCenter(1, 0, true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			body.applyForceToCenter(-1, 0, true);
		}
	}
	
	@Override
	public void render(SpriteBatch batch)
	{
		super.render(batch);
		
		play.getBoundingRectangle(play.getBone("Hitbox")).render(batch);
	}
	
}
