/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *Magnetic
 *Magnetic is an abstract class that has the general variables and methods needed for the subclasses
 */
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;

/**
 * Magnetic is an abstract class that has the general variables and methods needed for the subclasses
 */
public abstract class Magnetic 
{
	public int x;
	public int y;
	public int width;
	public int height;
	public int angle;
	public int numAvailable;
	public Image image;
	
	/**
	 * Magnetic has an x position, y position, width, height, and angle
	 * @param x1 X Position
	 * @param y1 Y Position
	 * @param w Width
	 * @param h Height
	 * @param theta Angle
	 */
	public Magnetic(int x1, int y1, int theta)
	{
		x = x1;
		y = y1;
		angle = theta;
	}
	/**
	 * Determines whether the ball has collided with the Magnetic
	 * @return Whether the ball has collided with the Magnetic or not
	 */
	public abstract boolean collide(Ball b);
	
	/**
	 * Changes the speed of the ball based on the Magnetic's variables
	 */
	public abstract void changeSpeed(Ball b);
	
	/**
	 * Sets the Magnetic in motion
	 */
	public abstract boolean contains(int x1, int y1);
	
}
