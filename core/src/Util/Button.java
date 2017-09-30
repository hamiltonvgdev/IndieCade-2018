package Util;

import Game.Config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blueacorn.spriter.LibGdxDrawer;
import com.blueacorn.spriter.LibGdxLoader;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Play;
import com.brashmonkey.spriter.PlayTweener;
import com.brashmonkey.spriter.SCMLReader;

public class Button
{
	Play play;
	LibGdxDrawer draw;
	SpriteBatch batch;
	
	float x, y, width, height;
	float scale;
	
	public boolean confirmed;
	boolean hovered;
	
	public Button(float x, float y, float width, float height, float scale) 
	{
		this.x = x;
		this.y = y;
		this.width = width * scale;
		this.height = height * scale;
		
		this.scale = scale;
		
		confirmed = false;
		hovered = false;
		
		batch = new SpriteBatch();
	}
	
	public Button setSprite(String ref, ShapeRenderer renderer)
	{
		FileHandle handle = Gdx.files.internal(ref);
		Data data = new SCMLReader(handle.read()).getData();
		
		LibGdxLoader loader = new LibGdxLoader(data);
		loader.load(handle.file());
		
		play = new Play(data.getEntity(0));
		play.scale(scale);
		
		draw = new LibGdxDrawer(loader, batch, renderer);
		
		return this;
	}
	
	public void update(float delta)
	{
		play.setPosition(x, y);
		play.update();
		
		if(x - width / 2 <= Gdx.input.getX() && x + width / 2 >= Gdx.input.getX() && 
				y - height / 2 <= Gdx.input.getY() && y + height / 2 >= Gdx.input.getY())
		{
			hovered = true;
			
			if(Gdx.input.justTouched())
			{
				confirmed = true;
			} 
		}else
		{
			hovered = false;
		}
	}
	
	public void render()
	{
		
		if(hovered && !play.getAnimation().name.equals("Selected"))
		{
			play.setAnimation("Selected");
		}else if (!hovered && !play.getAnimation().name.equals("Idle"))
		{
			play.setAnimation("Idle");
		}
		
		draw.draw(play);
	}
	
	public void dispose()
	{
		
	}
}
