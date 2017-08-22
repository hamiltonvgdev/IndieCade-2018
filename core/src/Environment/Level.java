package Environment;

import java.util.ArrayList;

import Game.Config;
import Player.Health;
import Screen.GameScreen;
import Tile.Tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Level 
{
	GameScreen gs;
	
	ArrayList<Thing> Things;
	ArrayList<Thing> toShow;
	ArrayList<Thing> toDie;
	
	TiledMap map;
	TmxMapLoader tmx;
	TiledMapRenderer tmr;
	
	Health health;
	
	public Level(GameScreen gs)
	{
		this.gs = gs;
		
		Things = new ArrayList<Thing>();
		toShow = new ArrayList<Thing>();
		toDie = new ArrayList<Thing>();
		tmx = new TmxMapLoader();
		
		
		
		for(int i = 0; i < Things.size(); i ++)
		{
			Things.get(i).setID(i);
			toShow.add(Things.get(i));
		}
	}
	
	public void loadMap(String id)
	{
		//Clears Previous Map
		Things.clear();
		toShow.clear();
		toDie.clear();
		if(map != null)
		{
			map.dispose();
		}
		
		
		//Loads New Maps
//		map = tmx.load("Maps/" + id + ".tmx");
//		tmr = new OrthogonalTiledMapRenderer(map);
//		tmr.setView(gs.getCamera());
//		
		//Creates Tile Body
//		createTiles((TiledMapTileLayer)(map.getLayers().get("Normal")), "Normal");
		
		
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
		toShow.add(thing);
	}
	
	public void createTiles(TiledMapTileLayer layer, String name)
	{
		Cell cell;
		float size = layer.getTileWidth();
		
		for(int x = 0; x < layer.getWidth(); x ++)
		{
			for(int y = 0; y < layer.getHeight(); y ++)
			{
				cell = layer.getCell(x, y);
				if(cell == null || cell.getTile() == null)
				{
					continue;
				}
				addThing(new Tile(gs,
						(x + 0.5f) * size / 32, 
						(y + 0.5f) * size / 32));
			}
		}
	}
	
	public void update(float delta)
	{
		toDie.clear();
		
		for(Thing t: toShow)
		{
			t.update(delta);
		}
		
		toShow.remove(toDie);
	}
	
	public void render(SpriteBatch batch)
	{
//		tmr.setView(gs.getCamera());
//		tmr.render();
		
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
	
	public ArrayList<Thing> getAlive() {return toShow;}
	public ArrayList<Thing> getThings() {return Things;}
	public ArrayList<Thing> getDieing() {return toDie;}
}
