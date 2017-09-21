package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Environment.Thing;
import Player.Health;

public class HUD{
	//Traveling between Gamescreen is strictly not allowed
	//Wait until Daniel Completes pause menu for further modification
	GameScreen gs;
	Health health;
	SpriteBatch batch;
	PauseMenu.pausebutton pausebutton;
	boolean pause;
	public HUD(GameScreen gs){
		this.gs = gs;
		health = new Health(this.gs);
		pausebutton = new PauseMenu.pausebutton(this.gs);
		batch = new SpriteBatch();
		pause = false;
	}
	public void update(float delta){
		health.update(delta);
		
		if(Gdx.input.isKeyJustPressed(Keys.B)){
			gs.getPlayer().health(-1);;
		}
	}
	public void render()
	{
		batch.begin();
		health.render(batch);
		pausebutton.render(batch);
		batch.end();
	}
}
