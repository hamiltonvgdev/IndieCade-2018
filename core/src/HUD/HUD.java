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
	
	Health health;
	
	PauseButton pausebutton;
	PausingState pausing;
	boolean pause;
	
	InventoryButton invbutton;
	InventoryState inventorying;
	boolean inventory;
	
	Core core;
	public HUD(GameScreen gs, Core core)
	{
		core = this.core;
		this.gs = gs;
		batch = new SpriteBatch();
		
		health = new Health(this.gs);
		
		pausebutton = (PauseButton) new PauseButton(this, 0).
				setSprite("Hud/Pause/Hud Button.png");
		pause = false;
		pausing = new PausingState(this.gs);

		invbutton = (InventoryButton) new InventoryButton(this, 1).
				setSprite("Hud/Inventory/Hud Button.png");
		inventorying = new InventoryState(gs);
		inventory = false;
	}
	
	public void update(float delta)
	{
		health.update(delta);

		pausebutton.update(delta);
		invbutton.update(delta);
		inventorying.update();
		
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
		
		if(pause)
		{
			pausing.render(batch);
		}
		
		if(inventory)
		{
			inventorying.render(batch);
		}
		
		batch.end();
	}
	
	public void pause(String id, boolean state)
	{
		if(state)
		{
			gs.pause();
		}else
		{
			gs.resume();
		}
		
		if(id.equals("Pause"))
		{
			pause = state;
			if(pause)
			{
				inventory = false;
			}
		}else if(id.equals("Inventory"))
		{
			inventory = state;
			if(inventory)
			{
				pause = false;
			}
		}
	}
}
