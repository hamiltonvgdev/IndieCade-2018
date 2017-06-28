package Screen;

import Game.Config;
import Game.Core;
import Game.GameData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen extends MyScreen
{
	Core core;
	OrthographicCamera camera;
	
	public GameScreen(Core core, GameData gd)
	{
		this.core = core;
		camera = new OrthographicCamera(Config.GAME_WIDTH, Config.GAME_HEIGHT);
	}
	
	@Override
	public void show() 
	{
		
	}
	
	@Override
	public void update(float delta) 
	{
		
	}

	@Override
	public void render(float delta) 
	{
		core.batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void resize(int width, int height)
	{
		
	}

	@Override
	public void pause()
	{
		
	}

	@Override
	public void resume() 
	{
		
	}

	@Override
	public void hide() 
	{
		
	}

	@Override
	public void dispose() 
	{
		
	}

}
