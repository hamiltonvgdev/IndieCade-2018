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
			boolean herp = true;
			
			n.Update();
			
			double[] derp = new double[9];
			
			derp[0] = 1;
			derp[1] = 1;
			derp[2] = 1;
			derp[3] = 1;
			derp[4] = 1;
			derp[5] = 1;
			derp[6] = 1;
			derp[7] = 1;
			derp[8] = 1;
			
			System.out.println(n.calculate(derp));
		}
	}
	
	public void stahp()
	{
		running = false;
	}
}
