package Screen;

import Environment.CollisionHandler;
import Environment.Level;
import Game.Config;
import Game.Core;
import Game.GameData;
import Player.Player;
import Renders.SpriterAnimationEngine;
import Util.ScreenshotFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.blueacorn.spriter.LibGdxDrawer;

public class GameScreen extends ModScreen
{
	OrthographicCamera GenCam;
	OrthographicCamera Camera;
	GameData gd;
	
	World world;
	Box2DDebugRenderer b2dr;
	
	Player player;
	Level level;
	SpriterAnimationEngine renderer;
	
	
	public GameScreen(Core core, GameData gd)
	{
		super(core);
		this.gd = gd;
		
		GenCam = new OrthographicCamera(Config.GAME_WIDTH / 2, Config.GAME_HEIGHT / 2);
		Camera = new OrthographicCamera(Config.GAME_WIDTH / Config.PPM, 
				Config.GAME_HEIGHT / Config.PPM);
		
		world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer();

		level = new Level(this);
		renderer = new SpriterAnimationEngine(core);
		player =  new Player(this).setID(0);
		level.addThing(player);

		world.setContactListener(new CollisionHandler(level));
	}
	
	@Override
	public void update(float delta) 
	{
		world.step(delta, 1, 1);
		
		level.update(delta);
		player.update(delta);
		
		GenCam.translate(1, 1);
		GenCam.update();
		
	}

	@Override
	public void render(float delta) 
	{
		super.render(delta);
		//core.batch.setProjectionMatrix(Camera.combined);
		b2dr.render(world, Camera.combined);
		//level.render(core.batch);
		player.render(core.batch);
	}
	
	public World getWorld() {return world;}
	public Level getLevel() {return level;}
	public GameData getGD() {return gd;}
	public OrthographicCamera getCamera() {return Camera;}
	public OrthographicCamera getGenCam() {return GenCam;}
	public SpriterAnimationEngine getRenderer() {return renderer;}

}
