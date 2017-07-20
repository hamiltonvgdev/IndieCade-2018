package Player;

import Game.Config;
import Screen.GameScreen;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class PlayerHitbox 
{
	
	Body head;
	Body torso;
	Body foot;
	
	WeldJointDef neck;
	WeldJointDef leg;
	
	BodyDef headdef;
	BodyDef torsodef;
	BodyDef footdef;
	
	FixtureDef Headdef;
	FixtureDef Torsodef;
	FixtureDef Footdef;
	
	public PlayerHitbox(GameScreen gs, Player player)
	{
		PolygonShape shape = new PolygonShape();
		
		headdef = new BodyDef();
		torsodef = new BodyDef();
		footdef = new BodyDef();
		
		neck = new WeldJointDef();
		leg = new WeldJointDef();
		
		headdef.type = BodyType.DynamicBody;
		headdef.position .set(player.getX(), player.getY() + Config.TORSO_HEIGHT
				+ Config.HEAD_HEIGHT);
		torsodef.type = BodyType.DynamicBody;
		torsodef.position .set(player.getX(), player.getY());
		footdef.type = BodyType.DynamicBody;
		footdef.position .set(player.getX(), player.getY() - Config.TORSO_HEIGHT
				-Config.FOOT_HEIGHT);
		
		head = gs.getWorld().createBody(headdef);
		torso = gs.getWorld().createBody(torsodef);
		foot = gs.getWorld().createBody(footdef);
		
		Headdef = new FixtureDef();
		Headdef.filter.categoryBits = Config.BIT_PLAYER;
		Torsodef = new FixtureDef();
		Torsodef.filter.categoryBits = Config.BIT_PLAYER;
		Footdef = new FixtureDef();
		Footdef.filter.categoryBits = Config.BIT_PLAYER;
		
		shape.setAsBox(Config.HEAD_WIDTH, Config.HEAD_HEIGHT);
		Headdef.shape = shape;
		head.createFixture(Headdef).setUserData(Config.PLAYER_Z + "-" + 0);
		shape.setAsBox(Config.TORSO_WIDTH, Config.TORSO_HEIGHT);	
		Torsodef.shape = shape;
		torso.createFixture(Torsodef).setUserData(Config.PLAYER_Z + "-" + 0);
		shape.setAsBox(Config.FOOT_WIDTH, Config.FOOT_HEIGHT);
		Footdef.shape = shape;
		foot.createFixture(Footdef).setUserData(Config.PLAYER_Z + "-" + 0);
		
		neck.initialize(head, torso, new Vector2(torso.getPosition().x, 
				torso.getPosition().y + Config.TORSO_HEIGHT / 2));
		leg.initialize(foot, torso, new Vector2(torso.getPosition().x, 
				torso.getPosition().y - Config.TORSO_HEIGHT / 2));
		
		gs.getWorld().createJoint(neck);
		gs.getWorld().createJoint(leg);
		
		shape.dispose();
	}
}
