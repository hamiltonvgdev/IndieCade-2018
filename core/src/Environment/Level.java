package Environment;

import java.util.ArrayList;

import Game.Config;
import Mob.Test;
import Player.Health;
import Player.Player;
import Screen.GameScreen;
import Tile.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Level 
{
	GameScreen gs;
	public boolean reset;
	boolean paused;
	
	ArrayList<Thing> Things;
	ArrayList<Thing> toShow;
	ArrayList<Thing> toDie;
	
	TiledMap map;
	TmxMapLoader tmx;
	TiledMapRenderer tmr;
	String id;
	
	Health health;
	
	//Testing
//	Test test;
	
	public Level(GameScreen gs)
	{
		this.gs = gs;
		
		Things = new ArrayList<Thing>();
		toShow = new ArrayList<Thing>();
		toDie = new ArrayList<Thing>();
		tmx = new TmxMapLoader();
		
		reset = false;
		
		for(int i = 0; i < Things.size(); i ++)
		{
			Things.get(i).setID(i);
			toShow.add(Things.get(i));
		}
		
		paused = false;
		
//		test = new Test(gs);
	}
	
	public void loadMap(String id, Player player)
	{
		this.id = id;
		
		//Clears Previous Map
		Things.clear();
		toShow.clear();
		toDie.clear();
		if(map != null)
		{
			map.dispose();
		}
		
		Things.add(player);

		//Loads New Maps
		map = tmx.load("Maps/" + id + ".tmx");
		tmr = new OrthogonalTiledMapRenderer(map);
		tmr.setView(gs.getCamera());
		
		//Creates Tile Body
		for(MapLayer layer: map.getLayers())
		{
			createTiles((TiledMapTileLayer) layer);
		}

//		addThing(test);
		//Assign IDs to all Entities
		for(int i = 0; i < Things.size(); i ++)
		{
			Things.get(i).setID(i);
			toShow.add(Things.get(i));
		}
	}
	
	public void addThing(Thing thing)
	{
		Things.add(thing);
//		toShow.add(thing);
	}
	
	public void createTiles(TiledMapTileLayer layer)
	{
		Cell cell;
		float size = layer.getTileWidth();
		
		for(int x = 0; x < layer.getWidth(); x ++)
		{
			for(int y = 0; y < layer.getHeight(); y ++)
			{
				cell = layer.getCell(x, y);
				if(cell == null || cell.getTile() == null || decodeTile(layer, x, y) == null)
				{
					continue;
				}
				addThing(decodeTile(layer, 
						(x + 0.5F) * size / Config.PPM, 
						(y + 0.5F) * size / Config.PPM));
			}
		}
	}
	
	public void update(float delta)
	{
		for(Thing t: toShow)
		{
			t.update(delta);
		}
		toShow.removeAll(toDie);
		
		toDie.clear();
		
		if(reset)
		{
			reset();
		}
	}
	
	public void render(SpriteBatch batch)
	{
		tmr.setView(gs.getCamera());
		tmr.render();
		
		for(Thing t: toShow)
		{
			t.render(batch);
		}
	}
	
	public void move(Vector2 moveVec)
	{
		for(Thing t: Things)
		{
			t.move(moveVec);
		}
		gs.getCamera().translate(moveVec);
	}
	
	public void reset()
	{
		toShow.clear();
		toShow.addAll(Things);
		reset = false;
	}
	
	public void pause()
	{
		for(Thing t: Things)
		{
			t.pause();
		}
		paused = true;
	}
	
	public void resume()
	{
		for(Thing t: Things)
		{
			t.resume();
		}
		
		paused = false;
	}
	
	public ArrayList<Thing> getAlive() {return toShow;}
	public ArrayList<Thing> getThings() {return Things;}
	public ArrayList<Thing> getDieing() {return toDie;}
	public String getId() {return id;}
	public TiledMap getMap() {return map;}
	public TmxMapLoader getMapLoader() {return tmx;}
	public TiledMapRenderer getMapRenderer() {return tmr;}
	
	
	
	public Tile decodeTile(TiledMapTileLayer layer, float x, float y)
	{	
		if(layer.getName().contains("Normal"))
		{
			return new Tile(gs, x, y);
		}else if(layer.getName().contains("Hurt"))
		{
			return new HurtTile(gs, x, y).setDamage(
					layer.getProperties().get("Destination").toString());
		}else if(layer.getName().contains("Death"))
		{
			return new HurtTile(gs, x, y).setDamage("10000000000");
		}else if(layer.getName().contains("Portal"))
		{
			return new PortalTile(gs, x, y).
					setDestination(layer.getName(), 
							layer.getProperties().get("Cost").toString());
		}
		
		
		else
		{
			return null;
		}
	}
}
