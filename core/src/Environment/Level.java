package Environment;

import java.util.ArrayList;

import Screen.GameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Level 
{
	GameScreen gs;
	
	ArrayList<Thing> Things;
	ArrayList<Thing> toShow;
	ArrayList<Thing> toDie;
	
	TiledMap map;
	TmxMapLoader tmx;
	TiledMapRenderer tmr;
	
	public Level(GameScreen gs)
	{
		this.gs = gs;
		
		Things = new ArrayList<Thing>();
		toShow = new ArrayList<Thing>();
		toDie = new ArrayList<Thing>();
		tmx = new TmxMapLoader();
		
		map = tmx.load("C:/Users/jahu2/Hummus Files/Tiled Map Editor/examples/desert.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);
	}
	
	public void loadMap(int id)
	{
		//Clears Previous Map
		Things.clear();
		toShow.clear();
		toDie.clear();
		
		//Loads New Maps
		map = tmx.load("Maps/" + id + ".tmx");
		tmr = new OrthogonalTiledMapRenderer(map);
		
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
		tmr.setView(gs.getGenCam());
		tmr.render();
		
		for(Thing t: toShow)
		{
			t.render(batch);
		}
	}
	
	public ArrayList<Thing> getAlive() {return toShow;}
	public ArrayList<Thing> getThings() {return Things;}
	public ArrayList<Thing> getDieing() {return toDie;}
}
