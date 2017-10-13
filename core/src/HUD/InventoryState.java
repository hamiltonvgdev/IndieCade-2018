package HUD;

import Game.Core;
import Screen.GameScreen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InventoryState 
{
	Core core;
	int rowNum;
	
	Texture grid;
	float gridSide;
	
	public InventoryState(GameScreen gs)
	{
		core = gs.core;
		rowNum = 10;
		gridSide = 100;
		
		grid = new Texture("Hud/Inventory/Grid.png");
	}
	
	public void update()
	{
		
	}
	
	public void render(SpriteBatch batch)
	{
		for(int i = 0; i < core.wl.getNames().size(); i ++)
		{
			batch.draw(grid, i % rowNum * gridSide, i / rowNum * gridSide, 
					gridSide, gridSide);
		}
		
		
		for(int i = 0; i < core.wl.Unlocked.size(); i ++)
		{
			if(core.wl.Unlocked.get(i) == true)
			{
				
			}
		}
	}
	
	public void pause()
	{
		
	}
	
	public void unpause()
	{
		
	}
}
