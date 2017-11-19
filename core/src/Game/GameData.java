package Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import Screen.GameScreen;

public class GameData implements Serializable
{
	private static final long serialVersionUID = 1;
	
	public String name;
	public String currentWeapon;
	public String currentMap;
	
	//Player stats;
	public float playerHealth;
	public ArrayList<Boolean> WeaponUnlock;
	
	//Boss Data
	public ArrayList<double[]> binput;
	public ArrayList<Double> boutput;
	
	Random gen;
	
	
	public GameData()
	{
		gen = new Random();
		playerHealth = 10;
		WeaponUnlock = new ArrayList<Boolean>();
		binput = new ArrayList<double[]>();
		boutput = new ArrayList<Double>();
		
		for(int i = 0; i < 1; i ++)
		{
			double[] derp = new double[9];
			derp[0] = gen.nextInt();
			derp[1] = gen.nextInt();
			derp[2] = gen.nextInt();
			derp[3] = gen.nextInt();
			derp[4] = gen.nextInt();
			derp[5] = gen.nextInt();
			derp[6] = gen.nextInt();
			derp[7] = gen.nextInt();
			derp[8] = gen.nextInt();
			binput.add(derp);
			boutput.add(derp[1] + 
					derp[2] + 
					derp[3] + 
					derp[4] + 
					derp[5] + 
					derp[6] + 
					derp[7] + 
					derp[8] + 
					derp[0]);
		}
	}
	
	public void init()
	{
		name = "";
		currentWeapon = "";
		currentMap = "test";
	}
	
	public void write(GameScreen gs)
	{
		playerHealth = gs.getPlayer().getHealth();
		WeaponUnlock = gs.core.wl.Unlocked;
		currentMap = gs.getLevel().getId();
	}
	
	public boolean ready()
	{
//		if(name.equals(""))
//		{
//			return false;
//		}else
//		{
//			return true;
//		}
		return true;
	}
}
