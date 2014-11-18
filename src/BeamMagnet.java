/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *BeamMagnet
 *BeamMagnet is a Magnetic that pulls the ball to it if it is in a certain range
 */
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;

/**
 * BeamMagnet is a Magnetic that pulls the ball to it if it is in a certain range
 */
public class BeamMagnet extends Magnetic
{
	protected BufferedImage image;
	private int width;
	private int height;
	
	/**
	 * beamMagnet has an x position, y position, width, height, and angle
	 * @param x1 X Position
	 * @param y1 Y Position
	 * @param w Width
	 * @param h Height
	 * @param theta Angle
	 */
	public BeamMagnet(int x1, int y1, int theta)
	{

		super(x1, y1,theta);
		width = 50;
		height = 75;
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("UFO_Small.png"));
		}
		catch(IOException e){}	
		super.image=image;
	}
	
	/**
	 * Determines whether the ball has collided with the beamMagnet
	 * @return Whether the ball has collided with the beamMagnet or not
	 */
	public boolean collide(Ball b)
	{
		if(b.x + b.diameter >= x && b.x + b.diameter <= x + width)
		{
			return (b.y > y + height);
		}
		return false;
	}
	
	/**
	 * Changes the speed of the ball based on the beamMagnet's variables
	 */
	public void changeSpeed(Ball b)
	{
		double ySpeed = b.ySpeed;
		double xSpeed = b.xSpeed;
		
		if(!(ySpeed < 0))
		{
			ySpeed = -1;
		}
		if(Math.abs(xSpeed)<1)
		{
			System.out.println (xSpeed);
			ySpeed-=Math.abs(xSpeed/2);
		}
		else
			ySpeed -=1;
		if(ySpeed<-6)
		{
			ySpeed=-6;
		}
		xSpeed = .995 * xSpeed * Math.cos(angle);
		
		if(b.y <= y + height )
			ySpeed = 0;
		
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