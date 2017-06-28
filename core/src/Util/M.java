package Util;

public class M 
{
	//Trignometric Formulas
	public static float sin(float degree)
	{
		return (float) Math.sin(Math.toRadians(degree));
	}
	public static float cos(float degree)
	{
		return (float) Math.cos(Math.toRadians(degree));
		}
	public static float tan(float degree)
	{
		return (float) Math.tan(Math.toRadians(degree));
	}
	
	//Spatial Calculations
	
	public static float distance(float x1, float y1, float x2, float y2)
	{
		return (y2 - y1)/(x2 - x1);
	}
	
	public static float angle(float x1, float y1, float x2, float y2)
	{
		return (float) Math.toDegrees(Math.atan((x2 - x1) / (y2 - y1)));
	}
}
