package Screen;

import Game.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public abstract class ModScreen implements Screen
{
	Core core;
	
	public ModScreen(Core core)
	{
		this.core = core;
	}
	
	public void create()
	{
		core.batch.begin();
	}
	
	public abstract void update(float delta);
	
	public void render(float delta)
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public void show()
	{
		core.batch.begin();
	}
	
	public void hide()
	{
		core.batch.end();
	}
	
	public void dispose()
	{
		core.batch.end();
	}
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
