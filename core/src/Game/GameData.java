package Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import Player.Player;
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
	
	
	public GameData()
	{
		playerHealth = 10;
		WeaponUnlock = new ArrayList<Boolean>();
		binput = new ArrayList<double[]>();
		boutput = new ArrayList<Double>();
		
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
		
		binput.add(derp);
		boutput.add((double) 10);
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
