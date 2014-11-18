/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *Wall
 * Wall is an Obstacle that makes the ball bounce based on where it hits
 */
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;

/**
 * Wall is an Obstacle that makes the ball bounce based on where it hits
 */
public class Wall extends Obstacle
{
	private BufferedImage image;
	
	/**
	 * Wall has an x position, y position, width, height, and angle
	 * @param x1 X Position
	 * @param y1 Y Position
	 * @param w Width
	 * @param h Height
	 * @param theta Angle
	 */
	public Wall(int x1, int y1, int w, int h, int theta)
	{
		super(x1, y1,w,h, theta);
		xPos = true;
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("Wall.png"));
		}
		catch(IOException e){}	
		super.image=image;
	}
	
	/**
	 * Determines whether the ball has collided with the Wall
	 * @return Whether the ball has collided with the Wall or not
	 */
	public boolean collide(Ball b)
	{

		return contains((int)(b.x+b.radius+b.radius*Math.sin(Math.toRadians(angle))),(int)(b.y+b.radius+b.radius*Math.cos(Math.toRadians(angle))));
	}
	
	/**
	 * Changes the speed of the ball based on the Wall's variables
	 */
	public void changeSpeed(Ball b)
	{
		double ySpeed = b.ySpeed;
		double xSpeed = b.xSpeed;
		if(angle==0)
		{
			ySpeed=-ySpeed;
			if(ySpeed<0)
				b.y-=3;
			else
				b.y+=3;
			ySpeed *= (3/4.0);
		
		}
		else
			ySpeed = Math.sin(Math.toRadians(angle));
		if(angle==90)
		{
			if(xSpeed<0)
				b.x+=3;
			else
				b.x-=3;
			xSpeed = -xSpeed;
		}
		if(angle!=0)
		{
			xSpeed = (xSpeed+(Math.sin(Math.toRadians(angle))));
		}
		
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
				if(y<=y1&&y+height>=y1)
				{
					return true;
				}
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
	
	/**
	 * Sets the Wall in motion
	 * @param verticalDisplacement the distance the Wall will move vertically
	 * @param horizontalDisplacement the distance the Wall will move horizontally
	 * @param speedInMillis the speed that the Wall will move at
	 * (Not implemented in Wall)
	 */
	public void setMotion(int verticalDisplacement, int horizontalDisplacement, double speed)
	{
//		maxX = centerX + horizontalDisplacement;
//		minX = centerX - horizontalDisplacement;
//		maxY = centerY + verticalDisplacement;
//		minY = centerY - verticalDisplacement;
//		
//		if(x == maxX)
//			xPos = false;
//		if(x == minX)
//			xPos = true;
//		if(xPos)
//		{
//			x++;
//		}
//		else if(!xPos)
//		{
//			x--;
//		}
	}
}