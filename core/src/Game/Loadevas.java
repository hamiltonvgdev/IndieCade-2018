package Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;

public class Loadevas 
{
	public static GameData gd;
	
	public static void save(int id)
	{
		try{
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("save" + id + ".sav"));
			out.writeObject(gd);
			out.flush();
			out.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static void load(int id)
	{
		try{
			if(saveFileExists(id))
			{
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream("save" + id + ".sav"));
				gd = (GameData) in.readObject();
				in.close();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static void delete(int id)
	{
		try{
			if(saveFileExists(id))
			{
				File f = new File("save" + id + ".sav");
				f.delete();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static boolean saveFileExists(int id)
	{
		File f = new File("save" + id + ".sav");
		return f.exists();
	}
	
	public static void init()
	{
		gd = new GameData();
	}
}
