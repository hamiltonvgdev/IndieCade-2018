package FinalBoss;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import Game.Core;
import Screen.GameScreen;

public class BossBody extends Thread
{
	Core core;
	GameScreen gs;
	
	boolean running;
	NeuralNetwork n;
	
	public  BossBody(Core core)
	{
		this.core = core;
	}
	
	public void setGS(GameScreen gs)
	{
		this.gs = gs;
		n = new NeuralNetwork(gs.getGD());
	}
	
	public void run()
	{
		running = true;
		if(n != null)
		{
			n.update();
		}
	}
	
	public void stahp()
	{
		running = false;
	}
}
