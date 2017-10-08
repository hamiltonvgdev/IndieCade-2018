package HUD;

import Game.Config;
import Game.Core;
import Player.Health;
import Screen.GameScreen;
import Util.Button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HUD{
	//Traveling between Gamescreen is strictly not allowed
	GameScreen gs;
	SpriteBatch batch;
	static boolean pause;
	
	Health health;
	
	PauseButton pausebutton;
	PausingState pausing;
	
	InventoryButton invbutton;
	InventoryState inventorying;
	
	OrthographicCamera cam;
	Core core;
	public HUD(GameScreen gs, Core core)
	{
		core = this.core;
		this.gs = gs;
		batch = new SpriteBatch();
		
		health = new Health(this.gs);
		
		pausebutton = (PauseButton) new PauseButton(this, 0).
				setSprite("test/pausebutton.png");
		invbutton = (InventoryButton) new InventoryButton(this, 1).
				setSprite("test/pausebutton.png");
		pause = false;
		pausing = new PausingState(this.gs,this.core);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 1280, 720);
	}
	
	public void update(float delta)
	{
		health.update(delta);

		pausebutton.update(delta);
		invbutton.update(delta);
		
		pausing.update(delta);
		
		//Testing
		if(Gdx.input.isKeyJustPressed(Keys.B)){
			gs.getPlayer().health(-1);
		}
	}
	
	public void render()
	{
		batch.begin();
		health.render(batch);
		
		pausebutton.render(batch);
		invbutton.render(batch);
		
		pausing.render(batch,pause);
		
		batch.end();
	}
	
	public void pause()
	{
		gs.pause();
		pause = true;
	}
}
