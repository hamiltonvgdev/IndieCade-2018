package Player;

import Game.Config;
import Mob.Mob;
import Screen.GameScreen;
import Weapon.Weapon;

import com.badlogic.gdx.graphics.Texture;
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
	}
	
	@Override
	public void initHitbox()
	{
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
		
		input.update();
		
		play.setPosition(x, y);
		
		weapon.update(delta);
	}
	
	public void render(SpriteBatch batch)
	{
		super.render(batch);
		
		weapon.render(batch);
	}
	
	public void move()
	{
		
	}
	
	@Override
	public void move(Vector2 movveVec){}
//	@Override 
//	public void setPosition(float x, float y){}
	
	//Return Statements
	public PlayerInput getInput() {return input;}
	public Weapon getWeapon() {return weapon;}
}
