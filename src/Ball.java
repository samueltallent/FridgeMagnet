/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *Obstacle
 *Obstacle is an abstract class that has the general variables and methods needed for the subclasses
 */
import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;
/**
 *Represents a Ball and ditects collison
 */
public class Ball //implements Runnable
{
	protected double ySpeed;
	protected double xSpeed;
	protected double x;
	protected double y;
	protected int radius;
	protected int diameter;
	int count = 0;
	protected BufferedImage image;
	/**
	 *creates a ball of a specified size
	 *@param x1 the x position
	 *@param y1 the y position
	 *@param r the radius
	 */
	
	public Ball(double x1, double y1, int r)
	{
		x = x1;
		y = y1;
		radius = r;
		diameter = 2 * r;
		ySpeed = 0;
		xSpeed = .1;
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("Ball.png"));
		}
		catch(IOException e){}	
	}
	/**
	 *Draws the ball on a specifed graphic 
	 *@param g the graphics to draw on
	 */	
	public boolean drawBall(Graphics g)
	{
		Color backgroundColor = new Color(238,238,238);
		for(Magnetic m : FridgeFrame.drawer.getMagnets())
		{
			if(m.collide(this))
			{
				FinishMagnet f=new FinishMagnet(0,0,0);
				if(m.getClass().equals(f.getClass()))
					return true;
				m.changeSpeed(this);
			}
		}
		for(Obstacle o : FridgeFrame.drawer.getObstacles())
		{
			if(o.collide(this))
				o.changeSpeed(this);
		}
		
		g.setColor(Color.BLUE);
		ySpeed+=.1;
		if(y + 2 * radius > FridgeFrame.height * (4/5.0))
		{
			ySpeed = -1 * (ySpeed * (3/4.0));
			y--;
		}
		if(x + diameter >= FridgeFrame.width)
		{
			xSpeed = -1 * (xSpeed * (3/4.0));
			x-=2;
		}
		if(x <= 0)
		{
			xSpeed = -1 * (xSpeed * (3/4.0));
			x+=2;
		}
		x += xSpeed;
		y += ySpeed;
		g.drawImage(image, (int)x, (int)y, diameter, diameter, null);
		g.setColor(backgroundColor);
		return false;
	}
	/**
	 *Sets the balls speed
	 *@param xs the x speed
	 *@param ys the y speed
	 */
	public void setSpeed(double xs, double ys)
	{
		xSpeed = xs;
		ySpeed = ys;
	}
}