package Screen;

import Environment.CollisionHandler;
import Environment.Level;
import Game.Config;
import Game.Core;
import Game.GameData;
import HUD.HUD;
import Player.Player;
import Renders.SpriterAnimationEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GameScreen extends ModScreen
{
	OrthographicCamera Camera;
	OrthographicCamera B2Dcam;
	GameData gd;
	
	World world;
	Box2DDebugRenderer b2dr;
	
	Player player;
	Level level;
	SpriterAnimationEngine renderer;
	HUD hud;
	
	public boolean paused;
	//Test
	
	public GameScreen(Core core, GameData gd, String id)
	{
		super(core);
		this.gd = gd;
		
		core.wl.setUnlock(gd.WeaponUnlock);
		
		Camera = new OrthographicCamera(Config.GAME_WIDTH , Config.GAME_HEIGHT);
		B2Dcam = new OrthographicCamera(Config.GAME_WIDTH / Config.PPM, 
				Config.GAME_HEIGHT / Config.PPM);
		renderer = new SpriterAnimationEngine(Camera.combined);
		
		world = new World(new Vector2(0, -9.81F), true);
		b2dr = new Box2DDebugRenderer();

		level = new Level(this);
		player = new Player(this);

		level.loadMap(id, this.player);
		player.Die();
		
		world.setContactListener(new CollisionHandler(level));
		
		hud = new HUD(this,core);
		
		paused = false;
		core.boss.setGS(this);
	}
	
	public GameScreen setHUD(HUD hud)
	{
		this.hud = hud;
		return this;
	}
	
	@Override
	public void update(float delta) 
	{
		if(!paused)
		{
			level.update(delta);
			world.step(delta, 1, 1);
			renderer.update(Camera.combined);
		}
		
		Camera.update();
		B2Dcam.update();
		hud.update(delta);
	}
	
	@Override
	public void render(float delta) 
	{
		super.render(delta);

//		core.batch.setProjectionMatrix(Camera.combined);
		
		
		level.render(core.batch);
		b2dr.render(world, B2Dcam.combined);

		hud.render();
	} 
	
	@Override
	public void pause()
	{
		level.pause();
		paused = true;
	}
	
	@Override
	public void resume()
	{
		level.resume();
		paused = false;
	}
	
	@Override
	public void dispose()
	{
		super.dispose();
		core.boss.suspend();
	}
	
	public World getWorld() {return world;}
	public Level getLevel() {return level;}
	public Player getPlayer() {return player;}
	public GameData getGD() {return gd;}
	public OrthographicCamera getB2Dcam() {return B2Dcam;}
	public OrthographicCamera getCamera() {return Camera;}
	public SpriterAnimationEngine getRenderer() {return renderer;}
	public HUD getHud() {return hud;}
	
}
