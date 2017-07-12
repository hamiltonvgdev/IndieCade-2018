package Renders;

import java.util.HashMap;

import Game.Config;
import Game.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blueacorn.spriter.LibGdxDrawer;
import com.blueacorn.spriter.LibGdxLoader;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.SCMLReader;

public class SpriterAnimationEngine 
{	
	ShapeRenderer renderer;
	
	HashMap<String, Player> Players;
	HashMap<String, LibGdxDrawer> Drawers;
	
	Core core;
	
	public SpriterAnimationEngine(Core core)
	{
		this.core = core;
		renderer = new ShapeRenderer();
		
		Players = new HashMap<String, Player>();
		Drawers = new HashMap<String, LibGdxDrawer>();
	}
	
	public void load(String name, String ref)
	{
		FileHandle file = Gdx.files.internal(ref); 
		Data data =  new SCMLReader(file.read()).getData();
		
		Player player = new Player(data.getEntity(0));
		player.scale(1.0F / Config.PPM);
		
		LibGdxLoader loader = new LibGdxLoader(data);
		loader.load(file.file());
		
		LibGdxDrawer drawer = new LibGdxDrawer(loader, core.batch, renderer);
		
		Players.put(name, player);
		Drawers.put(name, drawer);
	}
	
	public Player getPlayer(String name, String ref)
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
}
