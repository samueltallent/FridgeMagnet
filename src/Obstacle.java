/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *Obstacle
 *Obstacle is an abstract class that has the general variables and methods needed for the subclasses
 */
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;

/**
 * Obstacle is an abstract class that has the general variables and methods needed for the subclasses
 */
public abstract class Obstacle
{
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int centerX;
	protected int centerY;
	protected int maxX;
	protected int minX;
	protected int maxY;
	protected int minY;
	protected boolean xPos;
	protected boolean yPos;
	protected double angle;
	protected BufferedImage image;
	
	/**
	 * Obstacle has an x position, y position, width, height, and angle
	 * @param x1 X Position
	 * @param y1 Y Position
	 * @param w Width
	 * @param h Height
	 * @param theta Angle
	 */
	public Obstacle(int x1, int y1, int w, int h, double theta)
	{
		x = x1;
		y = y1;
		centerX = x;
		centerY = y;
		width = w;
		height = h;
		angle = theta;
	}
	
	/**
	 * Determines whether the ball has collided with the Obstacle
	 * @param b the ball to check
	 * @return Whether the ball has collided with the Obstacle or not
	 */
	public abstract boolean collide(Ball b);
	
	/**
	 * Changes the speed of the ball based on the Obstacle's variables
	 * @param b the ball to check
	 */
	public abstract void changeSpeed(Ball b);
	
	/**
	 * Sets the Obstacle in motion
	 * @param verticalDisplacement the distance the Obstacle will move vertically
	 * @param horizontalDisplacement the distance the Obstacle will move horizontally
	 * @param speedInMillis the speed that the obstacle will move at
	 */
	public abstract void setMotion(int verticalDisplacement, int horizontalDisplacement, double speedInMillis);
	
	
}