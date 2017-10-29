package HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.brashmonkey.spriter.Rectangle;

import Environment.Thing;
import Game.Config;
import Game.Core;
import Player.Health;
import Screen.GameScreen;
import Screen.MenuScreen;

public class PausingState extends Thing{
	
	// pausing stuff
	Texture pausingstate;
	Sprite pausing;
	Texture pausingbackground;
	Sprite pausingback;
	
	//current batch
	SpriteBatch batch;
	
	//resume/exit
	Texture Resume;
	Sprite ResumeS;
	Texture Exit;
	Sprite ExitS;
	OrthographicCamera cam;
	Core core;
	MenuScreen menu;
	
	//Misc
	final int offset = -25;
	
	public PausingState(GameScreen gs) {
		super(gs);
		this.core = gs.core;
		// TODO Auto-generated constructor stub
		batch = new SpriteBatch();
		pausingstate = new Texture(Gdx.files.internal("Hud/Pause/detail_pause.png"));
		pausing = new Sprite(pausingstate);
		pausingbackground = new Texture(Gdx.files.internal("Black_Screen.png"));
		pausingback = new Sprite(pausingbackground);
		pausingback.setAlpha((float) 0.5);
		pausingback.setScale(100);
		pausing.setScale(5.25F);
		pausing.setX((Config.GAME_WIDTH) / 2 + offset);
		pausing.setY((Config.GAME_HEIGHT) / 2 + offset * 2F);
		//resume/exit
		Resume = new Texture(Gdx.files.internal("Hud/Pause/pasue_optains_resume.png"));
		ResumeS = new Sprite(Resume);
		ResumeS.setScale(2F);
		ResumeS.setX((Config.GAME_WIDTH) / 2 + offset);
		ResumeS.setY((Config.GAME_HEIGHT) / 2 - offset);
		Exit = new Texture(Gdx.files.internal("Hud/Pause/pasue_optains_exit.png"));
		ExitS = new Sprite(Exit);
		ExitS.setScale(2F);
		ExitS.setX((Config.GAME_WIDTH) / 2 + offset);
		ExitS.setY((Config.GAME_HEIGHT) / 2 + offset * 4F);
		//camera setting
		cam = new OrthographicCamera();
		cam.setToOrtho(true,Config.GAME_WIDTH,Config.GAME_HEIGHT);
		//menuScreen
		menu = new MenuScreen(core);
	}
	public void update(float delta) {
		// TODO Auto-generated method stub
		super.update(delta);
//		cam.unproject(input);
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if(ResumeS.getBoundingRectangle().contains(Gdx.input.getX(),
					Config.GAME_HEIGHT - Gdx.input.getY())){
				gs.getHud().pause("Pause", false);
			}
			if(ExitS.getBoundingRectangle().contains(Gdx.input.getX(),
					Config.GAME_HEIGHT - Gdx.input.getY())){
				gs.core.setScreen(new MenuScreen(gs.core));
			}
		}
	}
	public void render(SpriteBatch batch){
		this.batch = batch;
		pausingback.draw(batch);
		pausing.draw(batch);
		ResumeS.draw(batch);
		ExitS.draw(batch);
	}
}
