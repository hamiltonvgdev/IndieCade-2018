package PauseMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Environment.Thing;
import Player.Health;
import Screen.GameScreen;

public class PausingState extends Thing{
	Texture resumeTexture;
	Texture exitTexture;
	Sprite resumeSprite;
	Sprite exitSprite;
	SpriteBatch batch;
	public PausingState(GameScreen gs) {
		super(gs);
		// TODO Auto-generated constructor stub
		resumeTexture = new Texture(Gdx.files.internal("pauseButton/resume.png"));
		resumeSprite = new Sprite(resumeTexture);
		exitTexture = new Texture(Gdx.files.internal("pauseButton/exit.png"));
		exitSprite = new Sprite(exitTexture);
		batch = new SpriteBatch();
	}
	public void update(float delta) {
		// TODO Auto-generated method stub
		super.update(delta);
		
	}
	public void render(SpriteBatch batch,boolean pause){
		this.batch = batch;
		if(pause == true){
			this.batch.draw(resumeSprite, 600,360);
			this.batch.draw(exitSprite, 600,240);
		}
	

	}
}
