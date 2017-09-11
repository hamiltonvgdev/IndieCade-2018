package Player;

import java.util.ArrayList;

import Game.Config;
import Mob.Mob;
import Screen.GameScreen;
import Screen.TransitionScreen;
import Tile.Tile;
import Weapon.Weapon;

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
	
	//Testing
	Weapon weapon;
	
	
	public Player(GameScreen gs)
	{
		super(gs);
		
		input = new PlayerInput(this);

		play = gs.getRenderer().getPlayer("Test", "test/derp/derp.scml");
		draw = gs.getRenderer().getDrawer("Test", "test/derp/derp.scml");
		
		play.setScale(3);
		
		initHitbox();
		
		weapon = new Weapon("Starter Scythe", this);
		
		play.setAnimation(weapon.getIdleAnimation());
		play.addListener(new PlayerSpriterHandler(this));
		
		health = gs.getGD().playerHealth;
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
						getBone("Hitbox")).size.width * 0.1F /
						Config.PPM * play.getScale(), 
				play.getBoundingRectangle(play.
						getBone("Hitbox")).size.height * 0.1F / 
						Config.PPM * play.getScale());
		
		
		fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = Config.BIT_PLAYER;
		fdef.filter.maskBits = Config.BIT_TILE;
	}

	
	@Override
	public Player setID(int id)
	{
		this.id = Config.PLAYER_Z + "-" + id;
		return this;
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
		y = body.getPosition().y * Config.PPM;
				
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
	
	@Override
	public void move(Vector2 moveVec){}
	@Override
	public void die(){}
//	@Override 
//	public void setPosition(float x, float y){}
	
	//Return Statements
	public PlayerInput getInput() {return input;}
	public Weapon getWeapon() {return weapon;}
<<<<<<< HEAD
	public void reset(){
		x = 0;
		y = 0;
	}

	public void pause() {
		
	}
=======
>>>>>>> fac323bcfd86e88e157412d98264316a171274ee
}
