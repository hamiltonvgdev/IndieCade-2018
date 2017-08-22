package Screen;

import Environment.CollisionHandler;
import Environment.Level;
import Game.Config;
import Game.Core;
import Game.GameData;
import Player.Player;
import Renders.SpriterAnimationEngine;

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
	//Test
	
	public GameScreen(Core core, GameData gd, String id)
	{
		super(core);
		this.gd = gd;
		
		Camera = new OrthographicCamera(Config.GAME_WIDTH , Config.GAME_HEIGHT);
		B2Dcam = new OrthographicCamera(Config.GAME_WIDTH / Config.PPM, 
				Config.GAME_HEIGHT / Config.PPM);
		
		world = new World(new Vector2(0, 0), true);
		b2dr = new Box2DDebugRenderer();

		level = new Level(this);
		//level.loadMap(id);
		renderer = new SpriterAnimationEngine(Camera.combined);
		player =  new Player(this).setID(0);
		level.addThing(player);
		
		level.loadMap(id);
		
		world.setContactListener(new CollisionHandler(level));
		
		hud = new HUD(this);
	}
	
	@Override
	public void update(float delta) 
	{
		player.update(delta);
		world.step(delta, 1, 1);
		
		renderer.update(Camera.combined);
		
		level.update(delta);
		Camera.update();
		B2Dcam.update();
		hud.update();
	}

	@Override
	public void render(float delta) 
	{
		super.render(delta);
		//core.batch.setProjectionMatrix(Camera.combined);
		level.render(core.batch);
		b2dr.render(world, B2Dcam.combined);
		player.render(core.batch);
		hud.render();
	}
	
	public World getWorld() {return world;}
	public Level getLevel() {return level;}
	public Player getPlayer() {return player;}
	public GameData getGD() {return gd;}
	public OrthographicCamera getB2Dcam() {return B2Dcam;}
	public OrthographicCamera getCamera() {return Camera;}
	public SpriterAnimationEngine getRenderer() {return renderer;}
	
}
