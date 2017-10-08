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
		if(player.getPlay().getAnimation().name.equals("Death"))
		{
			player.Die();
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.P))
		{
			player.getPlay().setAnimation(player.getWeapon().getPAnimation());
			player.getWeapon().pMove();
		}else if(Gdx.input.isKeyPressed(Input.Keys.O))
		{
			player.getPlay().setAnimation(player.getWeapon().getOAnimation());
			player.getWeapon().oMove();
		}else if(Gdx.input.isKeyPressed(Input.Keys.L))
		{
			player.getPlay().setAnimation(player.getWeapon().getLAnimation());
			player.getWeapon().lMove();
		}else if(Gdx.input.isKeyPressed(Input.Keys.K))
		{
			player.getPlay().setAnimation(player.getWeapon().getKAnimation());
			player.getWeapon().kMove();
		}else
		{	
			if(player.getBody().getLinearVelocity().x > 0.0001)
			{
				player.getPlay().setAnimation("Walk");
			}else
			{
				player.getPlay().setAnimation(player.getWeapon().getStillAnimation());
			}
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
		if(player.getHealth() > 0)
		{
			if(play.getAnimation().name.contains("Still") ||
					play.getAnimation().name.contains("Jump")||
					play.getAnimation().name.contains("Walk"))
			{
				if(Gdx.input.isKeyPressed(Input.Keys.P))
				{
					player.getPlay().setAnimation(player.getWeapon().getPAnimation());
					player.getWeapon().pMove();
				}else if(Gdx.input.isKeyPressed(Input.Keys.O))
				{
					player.getPlay().setAnimation(player.getWeapon().getOAnimation());
					player.getWeapon().oMove();
				}else if(Gdx.input.isKeyPressed(Input.Keys.L))
				{
					player.getPlay().setAnimation(player.getWeapon().getLAnimation());
					player.getWeapon().lMove();
				}else if(Gdx.input.isKeyPressed(Input.Keys.K))
				{
					player.getPlay().setAnimation(player.getWeapon().getKAnimation());
					player.getWeapon().kMove();
				}
			}
		}else if(!player.getPlay().getAnimation().name.equals("Death"))
		{
			player.getPlay().setAnimation("Death");
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
		
		if(play.getAnimation().name.contains("Still") || 
				play.getAnimation().name.contains("Jump")|| 
				play.getAnimation().name.contains("Walk"))
		{
			if(Math.abs(player.getBody().getLinearVelocity().y) > 0.01)
			{
				if(player.getBody().getLinearVelocity().y > 0)
				{
					play.setAnimation("Rise Jump");
				}else if(player.getBody().getLinearVelocity().y < 0 && 
						Math.abs(player.getBody().getLinearVelocity().y) > 0.25)
				{
					play.setAnimation("Fall Jump");
				}
			}else
			{
				if(player.getBody().getLinearVelocity().x != 0)
				{
					player.getPlay().setAnimation("Walk");
				}else
				{
					player.getPlay().setAnimation(player.getWeapon().getStillAnimation());
				}
			}
		}
	}

	@Override
	public void mainlineKeyChanged(Key prevKey, Key newKey)
	{
		
	}

}
