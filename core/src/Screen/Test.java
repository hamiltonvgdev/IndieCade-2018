package Screen;

import Game.Config;
import Game.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blueacorn.spriter.LibGdxDrawer;
import com.blueacorn.spriter.LibGdxLoader;
import com.brashmonkey.spriter.Data;
import com.brashmonkey.spriter.Drawer;
import com.brashmonkey.spriter.Player;
import com.brashmonkey.spriter.SCMLReader;

public class Test extends ModScreen
{
	Player player;
	ShapeRenderer renderer;
	//SpriteBatch batch;
	Drawer<Sprite> drawer;
	LibGdxLoader loader;
	OrthographicCamera cam;
	FPSLogger fpslog;

	public Test(Core core) {
		super(core);

		cam = new OrthographicCamera();
		cam.zoom = 1f;
		renderer = new ShapeRenderer();
		//batch = new SpriteBatch();
		FileHandle handle = Gdx.files.internal("test/derp.scml");
		Data data = new SCMLReader(handle.read()).getData();

		loader = new LibGdxLoader(data);
		loader.load(handle.file());
		drawer = new LibGdxDrawer(loader, core.batch, renderer);

		player = new Player(data.getEntity(0));
		player.setScale(2);
		fpslog = new FPSLogger();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		cam.setToOrtho(false, Config.GAME_WIDTH, Config.GAME_HEIGHT);
		cam.position.set(0, 0, 0f);
		cam.update();
		renderer.setProjectionMatrix(cam.combined);
		//batch.setProjectionMatrix(cam.combined);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		player.update();
		fpslog.log();

		drawer.draw(player);
	}

	@Override
	public void dispose() {
		super.dispose();

		renderer.dispose();
		loader.dispose();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}