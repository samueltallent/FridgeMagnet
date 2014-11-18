/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *FridgeFrame
 *Creates the Frame on which the game will be played and begins the running of the game
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.geom.*;
/**
 *Creates the Frame on which the game will be drawn
 */
public class FridgeFrame extends JFrame implements ActionListener
{
	protected static DrawPanel drawer;
	protected static int height;
	protected static int width;
	private JMenuBar menuBar;
	private JButton start;
	private JButton reset;
	private JButton menu;
	private JTextField level;
	protected JTextField time;
	private BufferedImage trayIcon;
	protected long startTime; 
	/**
	 *Constructs a Fridge frame for a specified level
	 *@param levelNum the number of the level
	 */
	public FridgeFrame(int levelNum) 
	{
		menuBar = new JMenuBar();
		start = new JButton("Start");
		reset = new JButton("Reset");
		menu = new JButton("Return to Main Menu");
		level = new JTextField();
		time = new JTextField();
		level.setEditable(false);
		time.setEditable(false);
		level.setText("Level: " + levelNum);
		menuBar.add(level);
		menuBar.add(time);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(start);
		menuBar.add(reset);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		setSize(1000, 750);
		start.addActionListener(this);
		reset.addActionListener(this);
		menu.addActionListener(this);
		height = getHeight();
		width = getWidth();
		time.setText("Time:0");
		setLayout(new BorderLayout());	
		drawer = new DrawPanel(levelNum,this);

		add(drawer, BorderLayout.CENTER);
		//setResizable(false);
		setVisible(true);
		setTitle("Fridge Magnet");
		
		try
		{
			trayIcon = ImageIO.read(getClass().getResourceAsStream("TrayLogo2.png"));
		}
		catch(IOException e){}
		
		setIconImage(trayIcon);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 *listens for an  actionEvent and responds to the action
	 *@param e the action preformed
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Start"))
		{
			drawer.startThread();
			startTime=System.currentTimeMillis();
		}
		else if(e.getActionCommand().equals("Reset"))
		{
			drawer.stopThread();
			drawer.ball = new Ball(75,10,12);	
		}
		else
		{
			setVisible(false);
			FridgeMagnet.LevelSelector = new LevelSelector();
		}
	}
	
	
}
/**
 *Creates the panel on which the componets are drawn
 */
class DrawPanel extends JPanel implements MouseListener, MouseMotionListener
{
	//protected static BarMagnet bar;
	protected Ball ball;
	protected Wall wall;
	protected HashSet<Magnetic> magnets;
	protected HashSet<Obstacle> obstacles;
	private Thread repainter;
	protected boolean clicked;
	private int barX;
	private int barY;
	protected boolean editable;
	private Magnetic clickedMag;
	private BufferedImage backgroundImage;
	private boolean rotated;
	private Level currentLevel;
	private FridgeFrame fridge;
	protected boolean finished;

	/**
	 *Constructs a drawpanel with a given FridgeFrame and level
	 *@param levelNum the number of the level
	 *@param f the FridgeFrame Drawn upon
	 */
	public DrawPanel(int levelNum,FridgeFrame f)
	{
		fridge=f;
		currentLevel = FridgeMagnet.levels.get(levelNum);
		magnets = currentLevel.getMagnets();
		obstacles=currentLevel.getObstacles();
		finished=false;
		try
		{
			backgroundImage = ImageIO.read(getClass().getResourceAsStream("bground.png"));			
		}
		catch(IOException e){}
		ball = new Ball(75,10,12);
		addMouseListener(this);
		addMouseMotionListener(this);
		editable=true;
		setPreferredSize(new Dimension(FridgeFrame.width, FridgeFrame.height));
		repainter = new Thread(new Runnable()
		{
		    @Override
		    public void run()
		    {
		        while (!finished)
		        {
		        	repaint();
		        	
				    try 
			        {
			            Thread.sleep(25);
			        } catch (InterruptedException ignored) {ignored.printStackTrace();}
		        }
				FridgeMagnet.LevelSelector=new LevelSelector();
				fridge.setVisible(false);
				fridge.dispose();
		    }
		});
		repainter.start();


	}
	/**
	 *sets editable to false thread so the user cannot edit the drawPanel
	 */
	public void startThread()
	{
		editable=false;
	}
	/**
	 *Makes it so the  the user can edit the DrawPanel
	 */
	public void stopThread()
	{
		editable = true;
	}
	/**
	 *Returns all the magnets in the level
	 *@return an HashSet of magnetics
	 */
	public HashSet<Magnetic> getMagnets()
	{
		return magnets;
	}
	/**
	 *Returns all the obstacles in the level
	 *@return an HashSet of Obstacles
	 */
	public HashSet<Obstacle> getObstacles()
	{
		return obstacles;
	}
	/**
	 *Paints the componets on the panel
	 *@param g the Graphics to draw on
	 */
	public void paintComponent(Graphics g)
	{	
		
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, FridgeFrame.width, FridgeFrame.height-150, null);
		g.setColor(Color.black);
		g.drawLine(0,600,1000,600);
		for(Magnetic m: magnets)
		{
			FinishMagnet f=new FinishMagnet(0,0,0);
			Graphics2D g2d=(Graphics2D)g;
			AffineTransform at=new AffineTransform();
			at.rotate(Math.toRadians(m.angle),m.x,m.y);
			g2d.setTransform(at);
			g2d.drawImage(m.image, m.x, m.y, this);
			at.rotate(Math.toRadians(-m.angle),m.x,m.y);
			g2d.setTransform(at);
		}
		for(Obstacle m:obstacles)
		{
			Graphics2D g2d=(Graphics2D)g;
			AffineTransform at=new AffineTransform();
			at.rotate(Math.toRadians(m.angle),m.x,m.y);
			g2d.setTransform(at);
			g2d.drawImage(m.image, m.x, m.y, this);
			at.rotate(Math.toRadians(-m.angle),m.x,m.y);
			g2d.setTransform(at);
		}
		if(!editable)
		{			
			finished=ball.drawBall(g);
			long endtime =System.currentTimeMillis();
			long seconds=(endtime-fridge.startTime)/1000;
			fridge.time.setText("Time:"+seconds);
		}
		else
		{
			g.drawImage(ball.image,75,10,24,24,null);
		}
	}
	/**
	 *An empty MouseListener Class
	 *@param event the mouse Event
	 */
	public void mouseReleased(MouseEvent event)
	{
		
	}
	/**
	 *An empty MouseListener Class
	 *@param event the mouse Event
	 */
	public void mouseClicked(MouseEvent event) 
	{
		
	} 
	/**
	 *Empty method for MouseListener
	 *@param event the mouse click event
	 */	
	public void mouseEntered(MouseEvent event) {
		
	}
	/**
	 *Empty method for MouseListener
	 *@param event the mouse click event
	 */
	public void mouseExited(MouseEvent event) {}
	/**
	 *gets the x and y of the mouse click and determine if the object should rotate or move
	 *@param event the mouse click event
	 */
	public void mousePressed(MouseEvent event)
   	{
   		if(event.getButton()==event.BUTTON1)
   		{   		
   			for(Magnetic bar:magnets)
   			{
	   			if(bar.contains(event.getX(),event.getY()))
	   			{
	   				clicked=true;
	   				rotated=false;
	   				clickedMag=bar;
	   			}
   			}
   		}
   		if(event.getButton()==event.BUTTON3)
   		{
   			for(Magnetic bar:magnets)
   			{
	   			if(bar.contains(event.getX(),event.getY()))
	   			{
	   				BarMagnet b=new BarMagnet(0,0,0);
	   				if(bar.getClass().equals(b.getClass()))
	   				{
	   					rotated=true;
	   					clickedMag=bar;
	   				}
	   				
	   				clicked=false;
	   				
	   			}
   			}
   		}
   		FinishMagnet f=new FinishMagnet(0,0,0);
   		if(clickedMag!=null)
   		{
   			if(f.getClass().equals(clickedMag.getClass()))
	   		{
	   			clicked=false;
	   			rotated=false;
	   		}
   		}
   		
   		
   	}
   	 /**
   	 *Listens for when the mouse is moved with no buttons 
   	 *and makes sure the magnets don't move
   	 *@param e the MouseEvent
   	 */    
   public void mouseMoved(MouseEvent e) {
       clicked=false;
       rotated=false;
    }
	/**
	 *Listens for the mouse to be Dragged and moves
	 *or rotates magnets appropriately 
	 *@param e the MouseEvent that is listened for
	 */
    public void mouseDragged(MouseEvent e)
    {
		if(clicked&&editable)
		{	      	
			barX=e.getX();
			barY=e.getY();   
			clickedMag.x=barX;
			clickedMag.y=barY;	
	    }
    	if(rotated&&editable)
    	{
    		clickedMag.angle=(int)Math.toDegrees(Math.atan((e.getY()-clickedMag.y)/((double)e.getX()-clickedMag.x)));	
    	} 
     
    }
}