package Renders;

import java.util.HashMap;

import Game.Config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.blueacorn.spriter.LibGdxDrawer;
import com.blueacorn.spriter.LibGdxLoader;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Play;
import com.brashmonkey.spriter.SCMLReader;

public class SpriterAnimationEngine 
{	
	ShapeRenderer renderer;
	
	HashMap<String, Play> Players;
	HashMap<String, LibGdxDrawer> Drawers;
	
	SpriteBatch batch;
	
	public SpriterAnimationEngine(Matrix4 proj)
	{
		this.batch = new SpriteBatch();
		batch.setProjectionMatrix(proj);
		renderer = new ShapeRenderer();
		
		Players = new HashMap<String, Play>();
		Drawers = new HashMap<String, LibGdxDrawer>();
	}
	
	public void load(String name, String ref)
	{
		FileHandle handle = Gdx.files.internal(ref);
		Data data = new SCMLReader(handle.read()).getData();
		
		LibGdxLoader loader = new LibGdxLoader(data);
		loader.load(handle.file());
		
		Play player = new Play(data.getEntity(0));
		player.scale(1.0F / Config.PPM);
		
		LibGdxDrawer drawer = new LibGdxDrawer(loader, batch, renderer);
		
		Players.put(name, player);
		Drawers.put(name, drawer);
	}
	
	public Play getPlayer(String name, String ref)
	{
		if(Players.containsKey(name))
		{
			return Players.get(name);
		}else
		{
			load(name, ref);
			return getPlayer(name, ref);
		}
	}
	
	public LibGdxDrawer getDrawer(String name, String ref)
	{
		if(Drawers.containsKey(name))
		{
			return Drawers.get(name);
		}else
		{
			load(name, ref);
			return getDrawer(name, ref);
		}
	}

	public void update(Matrix4 combined) 
	{
		for(LibGdxDrawer draw: Drawers.values())
		{
			draw.update(combined);
		}
	}
}
