package Renders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheetAnimation 
{
	TextureRegion[][] Animations;
	Animation[] Animation;
	int state;
	
	float stateTime;
	boolean looping;
	
	public SpriteSheetAnimation(Texture spritesheet, int width, int height)
	{
		Animations = TextureRegion.split(spritesheet, width, height);
		
		Animation = new Animation[Animations.length];
		
		looping = true;
	}
	
	public SpriteSheetAnimation setStep(float delta)
	{
		for(int i = 0; i < Animations.length; i ++)
		{
			Animation[i] = new Animation(delta, Animations[i]);
		}
		
		return this;
	}
	
	public SpriteSheetAnimation setStep(float[] delta)
	{	
		for(int i = 0; i < Animations.length; i ++)
		{
			Animation[i] = new Animation(delta[i], Animations[i]);
		}
		
		return this;
	}
	
	public SpriteSheetAnimation setLoop(boolean looping)
	{
		this.looping = looping;
		return this;
	}
	
	public void changeState(int state)
	{
		this.state = state;
	}
	
	
	
	public void changeDelta(int index, float delta)
	{
		Animation[index].setFrameDuration(delta);
	}
	
	public void render(float x, float y, float width, float height, float scaleX, float scaleY, 
			float originX, float originY, float rot, SpriteBatch batch)
	{
		batch.draw((TextureRegion) Animation[state].getKeyFrame(stateTime, looping), 
				x, y, originX, originY, width, height, scaleX, scaleY, rot, false);
		stateTime ++;	
	}
	
	public TextureRegion getCurrentFrame(){
		return (TextureRegion) Animation[state].getKeyFrame(stateTime, looping);}
}
