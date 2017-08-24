package Mob;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import Environment.Thing;
import Game.Config;
import Screen.GameScreen;

public class Mob extends Thing
{

	public Mob(GameScreen gs) 
	{
		super(gs);
	}
	
	public void initHitbox()
	{
		bdef = new BodyDef();
		bdef.type = BodyType.DynamicBody;
		bdef.position.set(x, y);
		body = gs.getWorld().createBody(bdef);

		shape = new PolygonShape();
		
		shape.setAsBox(
				play.getBoundingRectangle(play.
						getBone("Hitbox")).size.width * 0.4F / 
						Config.PPM * play.getScale(), 
				play.getBoundingRectangle(play.
						getBone("Hitbox")).size.height * 0.4F / 
						Config.PPM * play.getScale());
		
		
		fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = Config.BIT_ENEMY;
		fdef.filter.maskBits = Config.BIT_TILE;
	}
	
	@Override
	public Thing setID(int id)
	{
		this.id = Config.ENTITY_Z + "-" + id;
		body.createFixture(fdef).setUserData(this.id);
		shape.dispose();
		return this;
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		
		x = body.getPosition().x * Config.PPM;
		y = body.getPosition().y * Config.PPM;
		
		play.setPosition(x, y);
	}
}
