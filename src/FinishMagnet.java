/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *FinishMagnet
 *FinishMagnet is a Magnetic that is the destination for the level
 */
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;

/**
 * FinishMagnet is a Magnetic that is the destination for the level
 */
public class FinishMagnet extends Magnetic
{
	protected BufferedImage image;
	private int width;
	private int height;
	
	/**
	 * FinishMagnet has an x position, y position, width, height, and angle
	 * @param x1 X Position
	 * @param y1 Y Position
	 * @param w Width
	 * @param h Height
	 * @param theta Angle
	 */
	public FinishMagnet(int x1, int y1, int theta)
	{
		super(x1, y1,theta);
		width = 58;
		height = 53;
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("FINISH_SMALL.png"));
		}
		catch(IOException e){}
		super.image=image;	
	}
	
	/**
	 * Determines whether the ball has collided with the FinishMagnet
	 * @return Whether the ball has collided with the FinishMagnet or not
	 */
	public boolean collide(Ball b)
	{
		if(b.x + b.diameter >= x && b.x + b.diameter <= x + width)
		{
			return (b.y + b.diameter >= y && b.y + b.diameter <= y + height);
		}
		return false;
	}
	
	/**
	 * Changes the speed of the ball based on the FinishMagnet's variables
	 */
	public void changeSpeed(Ball b)
	{
		double ySpeed = b.ySpeed;
		double xSpeed = b.xSpeed;
		
		ySpeed = ySpeed * Math.sin(angle) -.1;
		xSpeed = xSpeed * Math.cos(angle);
		
		b.setSpeed(xSpeed, ySpeed);
		
	}
	
	/**
	 * Determines if an x coordinate and y coordinate are in the bounds of the wall
	 */
	public boolean contains(int x1, int y1)
	{
		if(x<=x1&&x+width>=x1)
		{
			return (y<=y1&&y+width>=y1);
		}
		return false;
	}
}