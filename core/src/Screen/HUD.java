package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Environment.Thing;
import Player.Health;

public class HUD{
	GameScreen gs;
	Health health;
	SpriteBatch batch;
	PauseButton.pausebutton pausebutton;
	boolean pause;
	public HUD(GameScreen gs){
		this.gs = gs;
		health = new Health(this.gs);
		pausebutton = new PauseButton.pausebutton(this.gs);
		batch = new SpriteBatch();
		pause = false;
	}
	public void update(float delta){
		health.update(delta);
		
		if(Gdx.input.isKeyJustPressed(Keys.B)){
			gs.getPlayer().health(-1);;
		}
<<<<<<< HEAD
		health.checkHealth();
		health.healthnumber();
		if(Gdx.input.isKeyJustPressed(Keys.P)){
			pause = true;
		}
=======
>>>>>>> fac323bcfd86e88e157412d98264316a171274ee
	}
	public void render()
	{
		batch.begin();
		health.render(batch);
		pausebutton.render(batch);
		batch.end();
	}
<<<<<<< HEAD
	public int healthnumber(){
		return health.healthnumber();
	}
	public boolean pause(){
		return pause();
	}
=======
>>>>>>> fac323bcfd86e88e157412d98264316a171274ee
}
