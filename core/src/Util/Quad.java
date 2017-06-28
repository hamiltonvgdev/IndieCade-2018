package Util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Quad 
{
	public float x, y, width, height;
	
	public Quad(float x, float y, float width, float height)
	{
		this.x = x - width / 2;
		this.y = y - height / 2;
		this.width = width;
		this.height = height;
	}
	
	public void update(float x, float y, float width, float height)
	{
		this.x = x - width / 2;
		this.y = y - height / 2;
		this.width = width;
		this.height = height;
	}
	
	public void render(SpriteBatch batch)
	{
		batch.draw(new Texture("Basic Square.png"), 
				x, y + height / 2, width, height);
	}
	
	public boolean check(float xa, float ya)
	{
		return (x <= xa) && (y <= ya) && (x + width >= xa) && (y + height >= ya);
	}
	
	public boolean check(Quad quad)
	{
		boolean intersect = false;
		for(float x = 0; x < quad.x + quad.width; x ++)
		{
			for(float y = 0; x < quad.y + quad.height; y ++)
			{
				if(check(x, y))
				{
					intersect = true;
					break;
				}
			}
		}
		
		return intersect;
	}
}
