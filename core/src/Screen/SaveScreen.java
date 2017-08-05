package Screen;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;

import Util.MenuButton;
import Game.Config;
import Game.Core;
import Game.Loadevas;

public class SaveScreen extends ModScreen
{
	boolean save1;
	boolean save2;
	boolean save3;
	
	MenuButton Save1;
	MenuButton Save2;
	MenuButton Save3;
	
	MenuButton DelSave1;
	MenuButton DelSave2;
	MenuButton DelSave3;
	
	Sound music;
	long musicId;
	
	public SaveScreen(Core core)
	{
		super(core);
		
		Save1 = new MenuButton(Config.GAME_WIDTH / 6, 
				Config.GAME_HEIGHT / 5 * 2, 1.5F);
		Save2 = new MenuButton(Config.GAME_WIDTH / 6 * 3, 
				Config.GAME_HEIGHT / 5 * 2, 1.5F);
		Save3 = new MenuButton(Config.GAME_WIDTH / 6 * 5, 
				Config.GAME_HEIGHT / 5 * 2, 1.5F);
	}

	public ModScreen setPreviousMusic(Sound titleMusic, long musicId) 
	{
		music = titleMusic;
		this.musicId = musicId;
		return this;
	}
	
	@Override
	public void show() 
	{
		super.show();
		
		save1 = Loadevas.saveFileExists(1);
		save2 = Loadevas.saveFileExists(2);
		save3 = Loadevas.saveFileExists(3);
		
		if(save1)
		{
			Save1.setPhrase("Load Save 1");
			DelSave1 = new MenuButton(Config.GAME_WIDTH / 6, 
					Config.GAME_HEIGHT / 5, 1.25F).
					setPhrase("Delete Save 1");
		}else
		{
			Save1.setPhrase("Create New Save 1");
		}
		
		if(save2)
		{
			Save2.setPhrase("Load Save 2");
			DelSave2 = new MenuButton(Config.GAME_WIDTH / 6 * 3, 
					Config.GAME_HEIGHT / 5, 1.25F).
					setPhrase("Delete Save 2");
		}else
		{
			Save2.setPhrase("Create New Load Save 2");
		}
		
		if(save3)
		{
			Save3.setPhrase("Load Save 3");
			DelSave3 = new MenuButton(Config.GAME_WIDTH / 6 * 5, 
					Config.GAME_HEIGHT / 5, 1.25F).
					setPhrase("Delete Save 3");
		}else
		{
			Save3.setPhrase("Create New Load Save 3");
		}
	}
	
	@Override
	public void update(float delta) 
	{
		Save1.update(delta);
		Save2.update(delta);
		Save3.update(delta);
		
		if(save1)
		{
			DelSave1.update(delta);
			
			if(DelSave1.confirmed)
			{
				save1 = false;
				Save1.setPhrase("Create New Save 1");
				Loadevas.delete(1);
			}
		}
		
		if(save2)
		{
			DelSave2.update(delta);
			
			if(DelSave2.confirmed)
			{
				save2 = false;
				Save2.setPhrase("Create New Save 2");
				Loadevas.delete(2);
			}
		}
		
		if(save3)
		{
			DelSave3.update(delta);
			
			if(DelSave3.confirmed)
			{
				save3 = false;
				Save3.setPhrase("Create New Save 3");
				Loadevas.delete(3);
			}
		}
		
		if(Save1.confirmed)
		{
			if(save1)
			{
				Loadevas.load(1);
			}else
			{
				Loadevas.gd.init();
				Loadevas.gd.name = JOptionPane.
						showInputDialog("Please Enter Your Character's Name");
				Loadevas.save(1);
			}

			core.setScreen(new TransitionScreen(core,
					new GameScreen(core, Loadevas.gd, Loadevas.gd.currentMap)).
					setFadeOutMusic(music, musicId));
			//core.setScreen(new GameScreen(core, Loadevas.gd, Loadevas.gd.currentMap));
		}
		
		if(Save2.confirmed)
		{
			if(save2)
			{
				Loadevas.load(2);
			}else
			{
				Loadevas.gd.init();
				Loadevas.gd.name = JOptionPane.
						showInputDialog("Please Enter Your Character's Name");
				Loadevas.save(2);
			}
			
			core.setScreen(new TransitionScreen(core,
					new GameScreen(core, Loadevas.gd, Loadevas.gd.currentMap)).
					setFadeOutMusic(music, musicId));
			//core.setScreen(new GameScreen(core, Loadevas.gd, Loadevas.gd.currentMap));
		}
		
		if(Save3.confirmed)
		{
			if(save3)
			{
				Loadevas.load(3);
			}else
			{
				Loadevas.gd.init();
				Loadevas.gd.name = JOptionPane.
						showInputDialog("Please Enter Your Character's Name");
				Loadevas.save(3);
			}

			core.setScreen(new TransitionScreen(core,
					new GameScreen(core, Loadevas.gd, Loadevas.gd.currentMap)).
					setFadeOutMusic(music, musicId));
			//core.setScreen(new GameScreen(core, Loadevas.gd, Loadevas.gd.currentMap));
		}
		
	}
	
	@Override
	public void render(float delta) 
	{
		super.render(delta);
		Save1.render(core.batch);
		Save2.render(core.batch);
		Save3.render(core.batch);
		
		if(save1)
		{
			DelSave1.render(core.batch);
		}
		
		if(save2)
		{
			DelSave2.render(core.batch);
		}
		
		if(save3)
		{
			DelSave3.render(core.batch);
		}
	}

	@Override
	public void dispose() 
	{
		super.dispose();
		
		Save1.dispose();
		Save2.dispose();
		Save3.dispose();
		
		if(save1)
		{
			DelSave1.dispose();
		}
		
		if(save2)
		{
			DelSave2.dispose();
		}
		
		if(save3)
		{
			DelSave3.dispose();
		}
	}

}
