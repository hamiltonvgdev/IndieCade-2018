package PauseButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Environment.Thing;
import Screen.GameScreen;

public class pausebutton extends Thing{
	public int scale = 50;
	private Texture texture;
	public Sprite pausebutton;
	public pausebutton(GameScreen gs) {
		super(gs);
		// TODO Auto-generated constructor stub
		texture = new Texture(Gdx.files.internal("test/pausebutton.png"));
		pausebutton = new Sprite(texture);
		pausebutton.setX(1230);
		pausebutton.setY(670);
	}
	public void update(float delta){
		
	}
	public void render(SpriteBatch batch){
		super.render(batch);
		batch.draw(pausebutton, 1230, 670,scale,scale);
	}
}
