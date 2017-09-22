package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import Environment.Thing;
import PauseMenu.PausingState;
import Player.Health;

public class HUD{
	//Traveling between Gamescreen is strictly not allowed
	//Wait until Daniel Completes pause menu for further modification
	GameScreen gs;
	Health health;
	SpriteBatch batch;
	PauseMenu.pausebutton pausebutton;
	boolean pause;
	PausingState pausing;
	OrthographicCamera cam;
	public HUD(GameScreen gs){
		this.gs = gs;
		health = new Health(this.gs);
		pausebutton = new PauseMenu.pausebutton(this.gs);
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
		if(Gdx.input.isKeyJustPressed(Keys.M)&& pause == false){
			pause = true;
		}
		else if(Gdx.input.isKeyJustPressed(Keys.M)&& pause == true){
			pause = false;
		}
		System.out.print(input.x);
		if(pausebutton.pausebutton.getBoundingRectangle().contains(input.x, input.y)&& pause == false) {
			pause = true;
		}
		
	}
	public void render()
	{
		batch.begin();
		pausing.render(batch,pause);
		health.render(batch);
		pausebutton.render(batch);
		
		batch.end();
	}
}
