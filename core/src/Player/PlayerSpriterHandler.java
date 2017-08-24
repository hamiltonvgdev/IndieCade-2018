package Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.brashmonkey.spriter.Animation;
import com.brashmonkey.spriter.Mainline.Key;
import com.brashmonkey.spriter.Play;
import com.brashmonkey.spriter.Play.PlayerListener;

public class PlayerSpriterHandler implements PlayerListener
{	
	
	Player player;
	
	public PlayerSpriterHandler(Player player)
	{
		this.player = player;
	}
	
	@Override
	public void animationFinished(Animation animation) 
	{
		if(Gdx.input.isKeyPressed(Input.Keys.P))
		{
			player.getPlay().setAnimation(player.getWeapon().getPAnimation());
		}else if(Gdx.input.isKeyPressed(Input.Keys.O))
		{
			player.getPlay().setAnimation(player.getWeapon().getOAnimation());
		}else if(Gdx.input.isKeyPressed(Input.Keys.L))
		{
			player.getPlay().setAnimation(player.getWeapon().getLAnimation());
		}else if(Gdx.input.isKeyPressed(Input.Keys.K))
		{
			player.getPlay().setAnimation(player.getWeapon().getKAnimation());
		}else
		{
			player.getPlay().setAnimation(player.getWeapon().getIdleAnimation());
		}
		
		player.getWeapon().Hitted.clear();
	}

	@Override
	public void animationChanged(Animation oldAnim, Animation newAnim) 
	{
		
	}

	@Override
	public void preProcess(Play play) 
	{
		if(play.getAnimation().name.contains("Idle") ||
				play.getAnimation().name.contains("Jump"))
		{
			if(Gdx.input.isKeyPressed(Input.Keys.P))
			{
				player.getPlay().setAnimation(player.getWeapon().getPAnimation());
			}else if(Gdx.input.isKeyPressed(Input.Keys.O))
			{
				player.getPlay().setAnimation(player.getWeapon().getOAnimation());
			}else if(Gdx.input.isKeyPressed(Input.Keys.L))
			{
				player.getPlay().setAnimation(player.getWeapon().getLAnimation());
			}else if(Gdx.input.isKeyPressed(Input.Keys.K))
			{
				player.getPlay().setAnimation(player.getWeapon().getKAnimation());
			}
		}
	}

	@Override
	public void postProcess(Play play) 
	{
		if((play.flippedX() < 0 && player.getBody().getLinearVelocity().x > 0) ||
				(play.flippedX() > 0 && player.getBody().getLinearVelocity().x < 0))
		{
			play.flipX();
		}
		
		if(play.getAnimation().name.contains("Idle"))
		{
			if(player.getBody().getLinearVelocity().y != 0)
			{
				if(player.getBody().getLinearVelocity().y > 0)
				{
					play.setAnimation("Rise Jump");
				}else if(player.getBody().getLinearVelocity().y < 0)
				{
					play.setAnimation("Fall Jump");
				}
			}
		}
	}

	@Override
	public void mainlineKeyChanged(Key prevKey, Key newKey)
	{
		
	}

}
