package Player;

import java.util.ArrayList;

import Environment.Thing;
import Game.Config;
import Screen.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blueacorn.spriter.LibGdxDrawer;
import com.brashmonkey.spriter.Animation;
import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Mainline.Key;
import com.brashmonkey.spriter.Play;
import com.brashmonkey.spriter.Play.PlayerListener;

public class Health extends Thing{
	private int Healthnumber;
	private Texture texture;
	private Sprite healthSprite;
	
	private int scale = 30;
	
	private final int Threshold = 5;
	protected LibGdxDrawer draw;
	protected Play play;
	protected ArrayList<Play> HeartPlays;
	protected ArrayList<LibGdxDrawer> HeartDraws;
	
	public Health(GameScreen gs) {
		super(gs);
		Healthnumber = 6;
		// TODO Auto-generated constructor stub
		texture = new Texture(Gdx.files.local("Utils/HeartBeat/heart beat 2 PNG.png"));
		healthSprite = new Sprite(texture);
		
		HeartDraws = new ArrayList<LibGdxDrawer>();
		HeartPlays = new ArrayList<Play>();
		
		for(int i = 0; i < Threshold; i ++)
		{
			draw = gs.getRenderer().getDrawer("Heart Warning" + (i + 1),
					"Utils/HeartBeat/HeartBeat.scml");
			play = gs.getRenderer().getPlayer("Heart Warning" + (i + 1),
					"Utils/HeartBeat/HeartBeat.scml");
			play.setScale(scale / Config.PPM / 4);
			HeartDraws.add(draw);
			HeartPlays.add(play);
		}
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		super.update(delta);
		
		Healthnumber = (int) gs.getPlayer().getHealth();
		
		if(Healthnumber <= Threshold)
		{
			for(int i = 0; i < Threshold; i ++)
			{
				HeartPlays.get(i).setPosition((i + 0.5F) * scale * 0.8F, scale / 2);
				HeartPlays.get(i).update();
			}
		}
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
		if(Healthnumber > Threshold)
		{
			for (int i = 0; i< scale*Healthnumber;i = i+scale){
				batch.draw(healthSprite,i * 0.80F ,0,scale,scale);
			}
		}else
		{
			for(int i = 0; i < Healthnumber; i ++)
			{
				HeartDraws.get(i).update(batch.getProjectionMatrix());
				HeartDraws.get(i).draw(HeartPlays.get(i));
			}
		}
	}
}
