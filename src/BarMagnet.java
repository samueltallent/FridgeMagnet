/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *BarMagnet
 *BarMagnet is a Magnetic that makes the ball bounce based on where it hits
 */
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import java.awt.geom.*;

/**
 * BarMagnet is a Magnetic that makes the ball bounce based on where it hits
 */
public class BarMagnet extends Magnetic
{
	protected BufferedImage image;
	private int width;
	private int height;
	
	/**
	 * BarMagnet has an x position, y position, width, height, and angle
	 * @param x1 X Position
	 * @param y1 Y Position
	 * @param theta Angle
	 */
	public BarMagnet(int x1, int y1, int theta)
	{
		
		super(x1, y1,theta);
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("BarMagnet.png"));
		}
		catch(IOException e){}	
		super.image=image;
		width=100;
		height=25;
	}
	
	/**
	 * Determines whether the ball has collided with the barMagnet
	 * @return Whether the ball has collided with the barMagnet or not
	 */
	public boolean collide(Ball b)
	{
				
		return contains((int)(b.x+b.radius+b.radius*Math.sin(Math.toRadians(angle))),(int)(b.y+b.radius+b.radius*Math.cos(Math.toRadians(angle))));
	}
	
	/**
	 * Changes the speed of the ball based on the barMagnet's variables
	 */
	public void changeSpeed(Ball b)
	{
		double ySpeed = b.ySpeed;
		double xSpeed = b.xSpeed;
		ySpeed = Math.sin(Math.toRadians(angle));
		if(angle==0||angle==180)
		{
			ySpeed-=.1;
		}
		xSpeed = xSpeed+(Math.sin(Math.toRadians(angle)));
		b.setSpeed(xSpeed, ySpeed);
		
	}
	
	/**
	 * Determines if an x coordinate and y coordinate are in the bounds of the wall
	 */
	public boolean contains(int x1, int y1)
	{
		if(angle>=-5&&angle<=5)
		{
			if(x<=x1&&x+width>=x1)
				return (y<=y1&&y+height>=y1);
			return false;
		}
		else if(angle<0&&angle>-85)
		{
			if(x<=x1+10&&x+(width*Math.cos(Math.toRadians(angle)))+10>=x1)
			{
				return(y1>=y-5+(x1-x)*Math.tan(Math.toRadians(angle))&&y1<=(y+25)+(x1-x)*Math.tan(Math.toRadians(angle)));
			}
			return false;
		}
		else if(angle<=-85&&angle>=-90||angle<270&&angle>=265)
		{
			if(x-10<=x1&&x+height+10>=x1)
			{
				return(y>=y1&&y-width<=y1);
			}
			return false;
		}
		else if(angle>180&&angle<270)
		{
			if(x>=x1&&x+(width*Math.cos(Math.toRadians(angle)))<=x1)
			{	
				return(y1>=y-20+(x1-x)*Math.tan(Math.toRadians(angle))&&y1<=15+(y+15)+(x1-x)*Math.tan(Math.toRadians(angle)));
			}
			return false;
			
		}
		else if(angle==180)
		{
			if(x>=x1&&x-width<=x1)
			{
				return (y-10<=y1&&y+height-10>=y1);
			}	
			return false;
		}
		else if(angle<-180&&angle>-270)
		{
			if(x>=x1-10&&x+(width*Math.cos(Math.toRadians(angle)))-10<=x1)
			{	
				return(y1<=y+20+(x1-x)*Math.tan(Math.toRadians(angle))&&y1>=(y-20)+(x1-x)*Math.tan(Math.toRadians(angle)));
			}
			return false;
			
		}
		else if(angle>=85)
		{
			if(x<=x1+10&&x+height>=x1-10)
			{
				return(y<=y1&&y+width>=y1);
			}
			return false;
		}
		else if(angle<85&&angle>5)
		{
			if(x<=x1+10&&x+(width*Math.cos(Math.toRadians(angle)))+10>=x1)
			{	
				return(y1+(25*Math.sin(Math.toRadians(angle)))>=(y)+(x1-((double)x))*Math.tan(Math.toRadians(angle))&&y1-(25*Math.sin(Math.toRadians(angle)))<=(y)+(x1-((double)x))*Math.tan(Math.toRadians(angle)));
			}
			return false;
			
		}
		return false;

	}
}