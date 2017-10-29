package Player;

import java.util.ArrayList;

import Environment.Thing;
import Game.Config;
import Mob.Mob;
import Screen.GameScreen;
import Screen.TransitionScreen;
import Tile.Tile;
import Weapon.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Player extends Mob
{
	//Input
	PlayerInput input;
	
	//Collision
	public ArrayList<Tile> inContact;
	
	
	//Combat
	float maxSpeed;
	
	//Combat Support
	
	//Testingq
	Weapon weapon;
	
	
	public Player(GameScreen gs)
	{
		super(gs);
		
		input = new PlayerInput(this);

		play = gs.getRenderer().getPlayer("Player", "Player Animation/Character.scml");
		draw = gs.getRenderer().getDrawer("Player", "Player Animation/Character.scml");
		
		play.setScale(0.1F);
		
		initHitbox();
		
		weapon = new Weapon("", this);
		
		play.setAnimation(weapon.getStillAnimation());
		play.addListener(new PlayerSpriterHandler(this));
		
		health = gs.getGD().playerHealth;
//		maxSpeed = 4F;
		maxSpeed = 20F;
	}
	
	@Override
	public void initHitbox()
	{
		inContact = new ArrayList<Tile>();
		
		bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody;
		bdef.position.set(x, y);
		body = gs.getWorld().createBody(bdef);
		

		shape = new PolygonShape();
		
		shape.setAsBox(
				play.getBoundingRectangle(play.
						getBone("Torso")).size.width * 0.1F /
						Config.PPM * play.getScale(), 
				play.getBoundingRectangle(play.
						getBone("Torso")).size.height * 0.1F / 
						Config.PPM * play.getScale());
		
		
		fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.friction = 0.4F;
		fdef.filter.categoryBits = Config.BIT_PLAYER;
		fdef.filter.maskBits = Config.BIT_TILE;
	}

	
	@Override
	public Player setID(int id)
	{
		this.id = Config.PLAYER_Z + "-" + id;
		return this;
	}
	
	public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
		play.setAnimation(weapon.getStillAnimation());
	}
	
	public void update(float delta)
	{	
		if(body.getFixtureList().size == 0)
		{
			body.createFixture(fdef).setUserData(this.id);
		}
		
		super.update(delta);
		
		getGS().getCamera().position.set(x, y , 0);
		
		x = body.getPosition().x * Config.PPM;
		y = (body.getPosition().y) * Config.PPM + 32;
				
		if(health > 0)
		{
			input.update();
		}
		
		play.setPosition(x, y);
		
		weapon.update(delta);
	}
	
	public void render(SpriteBatch batch)
	{
		super.render(batch);
		
		weapon.render(batch);
	}
	
	public void Die()
	{
		gs.core.setScreen(new TransitionScreen(gs.core, gs));
		setPosition(Float.parseFloat(gs.getLevel().getMap().getProperties().get("Revive").toString().split("-")[0]) / Config.PPM,
				Float.parseFloat(gs.getLevel().getMap().getProperties().get("Revive").toString().split("-")[1]) / Config.PPM);
		body.setLinearVelocity(0, -1);
		inContact.clear();
		health = gs.getGD().playerHealth;
		gs.getLevel().reset = true;
	}
	
	public boolean isGrounded()
	{
		boolean grounded = false;
		
		for(Tile t: inContact)
		{	
			if(t.getY() < y)
			{
				if(Math.abs(x - t.getX() * Config.PPM) < 22)
				{
					grounded = true;
					break;
				}
			}
		}
		
		return grounded;
	}
	
	@Override
	public void move(Vector2 moveVec){}
	@Override
	public void die(){}
//	@Override 
//	public void setPosition(float x, float y){}
	
	//Return Statements
	public PlayerInput getInput() {return input;}
	public Weapon getWeapon() {return weapon;}
}
