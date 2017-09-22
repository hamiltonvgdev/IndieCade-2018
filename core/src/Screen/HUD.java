package Screen;

import Game.Config;
import PauseMenu.PausingState;
import PauseMenu.HudButton;
import Player.Health;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class HUD{
	//Traveling between Gamescreen is strictly not allowed
	//Wait until Daniel Completes pause menu for further modification
	GameScreen gs;
	Health health;
	SpriteBatch batch;
	HudButton pausebutton;
	boolean pause;
	PausingState pausing;
	OrthographicCamera cam;
	public HUD(GameScreen gs){
		this.gs = gs;
		health = new Health(this.gs);
		pausebutton = new HudButton(this.gs, this, 0);
		batch = new SpriteBatch();
		pause = false;
		pausing = new PausingState(this.gs);
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 1280, 720);

	}
	public void update(float delta){
		health.update(delta);
		int x1 = Gdx.input.getX();
		int y1 = Gdx.input.getY();
		Vector3 input = new Vector3(x1, y1, 0);
		if(Gdx.input.isKeyJustPressed(Keys.B)){
			gs.getPlayer().health(-1);
		}
		
		pausebutton.update(delta);
	}
	public void render()
	{
		batch.begin();
		pausing.render(batch,pause);
		health.render(batch);
		pausebutton.render(batch);
		
		batch.end();
	}
	
	public void pause()
	{
		gs.pause();
		pause = true;
	}
}
