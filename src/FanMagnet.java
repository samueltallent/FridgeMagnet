/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *FanMagnet
 *fanMagnet is a Magnetic that blows the ball in the direction the fan is facing
 */
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;

/**
 * fanMagnet is a Magnetic that blows the ball in the direction the fan is facing
 */
public class FanMagnet extends Magnetic
{
	private Icon image;
	private int width;
	private int height;
	
	/**
	 * fanMagnet has an x position, y position, width, height, and angle
	 * @param x1 X Position
	 * @param y1 Y Position
	 * @param w Width
	 * @param h Height
	 * @param theta Angle
	 */
	public FanMagnet(int x1, int y1,int theta)
	{
		super(x1, y1,theta);
		width = 64;
		height = 108;
		image = new ImageIcon(getClass().getResource("fan.gif"));
		super.image=((ImageIcon)image).getImage();	
	}
	
	/**
	 * Determines whether the ball has collided with the fanMagnet
	 * @return Whether the ball has collided with the fanMagnet or not
	 */
	public boolean collide(Ball b)
	{
		if(b.x + b.diameter >= x + width && b.x + b.diameter <= x + width + 100)
		{
			return (b.y + b.diameter >= y && b.y + b.diameter <= y + height);
		}
		return false;
	}

	/**
	 * Changes the speed of the ball based on the fanMagnet's variables
	 */
	public void changeSpeed(Ball b)
	{
		double ySpeed = b.ySpeed;
		double xSpeed = b.xSpeed;
		
		//ySpeed = ySpeed * Math.sin(angle);
		xSpeed = xSpeed * Math.cos(angle)+.2;
		
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