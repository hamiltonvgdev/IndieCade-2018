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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.blueacorn.spriter.LibGdxDrawer;

public class GameScreen extends ModScreen
{
	OrthographicCamera GeoCam;
	OrthographicCamera Camera;
	GameData gd;
	
	World world;
	Box2DDebugRenderer b2dr;
	
	Player player;
	Level level;
	SpriterAnimationEngine renderer;
	
	//Test
	
	public GameScreen(Core core, GameData gd, String id)
	{
		super(core);
		this.gd = gd;
		
		GeoCam = new OrthographicCamera(Config.GAME_WIDTH , Config.GAME_HEIGHT);
		Camera = new OrthographicCamera(Config.GAME_WIDTH / Config.PPM, 
				Config.GAME_HEIGHT / Config.PPM);
		
		world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer();

		level = new Level(this);
		//level.loadMap(id);
		renderer = new SpriterAnimationEngine(Camera.combined);
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
		GeoCam.update();
		Camera.update();
	}

	@Override
	public void render(float delta) 
	{
		super.render(delta);
		level.render(core.batch);
		b2dr.render(world, Camera.combined);
		player.render(core.batch);
	}
	
	public World getWorld() {return world;}
	public Level getLevel() {return level;}
	public GameData getGD() {return gd;}
	public OrthographicCamera getCamera() {return Camera;}
	public OrthographicCamera getGeoCam() {return GeoCam;}
	public SpriterAnimationEngine getRenderer() {return renderer;}

}
