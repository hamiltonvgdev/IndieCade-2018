package com.brashmonkey.spriter;

import Game.Config;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Represents a 2D rectangle with left, top, right and bottom bounds.
 * A rectangle is responsible for calculating its own size and checking if a point is inside it or if it is intersecting with another rectangle.
 * @author Trixt0r
 *
 */
public class Rectangle {
	
	/**
	 * Belongs to the bounds of this rectangle.
	 */
	public float left, top, right, bottom;
	PolygonShape shape;
	/**
	 * The size of this rectangle.
	 */
	public final Dimension size;

	//SpriteBatch for libgdx rendering
	public SpriteBatch batch;
	
	/**
	 * Creates a rectangle with the given bounds.
	 * @param left left bounding
	 * @param top top bounding
	 * @param right right bounding
	 * @param bottom bottom bounding
	 */
	
	
	public Rectangle(float left, float top, float right, float bottom){
		this.set(left, top, right, bottom);
		this.size = new Dimension(0, 0);
		this.calculateSize();
		batch = new SpriteBatch();
	}
	
	/**
	 * Creates a rectangle with the bounds of the given rectangle.
	 * @param rect rectangle containing the bounds.
	 */
	public Rectangle(Rectangle rect){
		this(rect.left, rect.top, rect.right, rect.bottom);
	}
	
	/**
	 * Returns whether the given point (x,y) is inside this rectangle.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return <code>true</code> if (x,y) is inside
	 */
	public boolean isInside(float x, float y){
		return x >= this.left && x <= this.right && y <= this.top && y >= this.bottom; 
	}
	
	/**
	 * Returns whether the given point is inside this rectangle.
	 * @param point the point
	 * @return <code>true</code> if the point is inside
	 */
	public boolean isInside(Point point){
		return isInside(point.x, point.y);
	}
	
	/**
	 * Calculates the size of this rectangle.
	 */
	public void calculateSize(){
		this.size.set(right-left, top-bottom);
		orient();
	}
	
	/**
	 * Sets the bounds of this rectangle to the bounds of the given rectangle.
	 * @param rect rectangle containing the bounds.
	 */
	public void set(Rectangle rect){
		if(rect == null) return;
		this.bottom = rect.bottom;
		this.left = rect.left;
		this.right = rect.right;
		this.top = rect.top;
		this.calculateSize();
	}
	
	/**
	 * Sets the bounds of this rectangle to the given bounds.
	 * @param left left bounding
	 * @param top top bounding
	 * @param right right bounding
	 * @param bottom bottom bounding
	 */
	public void set(float left, float top, float right, float bottom){
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	/**
	 * Returns whether the given two rectangles are intersecting.
	 * @param rect1 the first rectangle
	 * @param rect2 the second rectangle
	 * @return <code>true</code> if the rectangles are intersecting
	 */
	public static boolean areIntersecting(Rectangle rect1, Rectangle rect2){
		return rect1.isInside(rect2.left, rect2.top) || rect1.isInside(rect2.right, rect2.top)
				|| rect1.isInside(rect2.left, rect2.bottom) || rect1.isInside(rect2.right, rect2.bottom);
	}
	
	/**
	 * Creates a bigger rectangle of the given two and saves it in the target.
	 * @param rect1 the first rectangle
	 * @param rect2 the second rectangle
	 * @param target the target to save the new bounds.
	 */
	public static void setBiggerRectangle(Rectangle rect1, Rectangle rect2, Rectangle target){
		target.left = Math.min(rect1.left, rect2.left);
		target.bottom = Math.min(rect1.bottom, rect2.bottom);
		target.right = Math.max(rect1.right, rect2.right);
		target.top = Math.max(rect1.top, rect2.top);
	}
	
	public void orient()
	{
//		left = x;
//		right = x + size.width;
//		top = y;
//		bottom = y - size.height / 2;
	}
	
	//Renders ligdx rectangle
	public void render(SpriteBatch batch)
	{
		this.batch.setProjectionMatrix(batch.getProjectionMatrix());
		
		this.batch.begin();
		
		this.batch.draw(new TextureRegion(new Texture("Basic Square.png")), 
				left, bottom, size.width, size.height);
		
		this.batch.end();
	}
	
	//Converts to Libgdx Shape
	public PolygonShape toShape()
	{
		shape.setAsBox((right - left) / Config.PPM, (top - bottom) / Config.PPM);
		return shape;
	}
}
