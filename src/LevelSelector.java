/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *LevelSelector
 * LevelSelector opens new levels depending on which is clicked and also can open the information windows
 */
import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

/**
 * LevelSelector opens new levels depending on which is clicked and also can open the information windows
 */
public class LevelSelector extends JFrame implements ActionListener
{
	private JButton nextButton;
	private JButton prevButton;
	private JPanel bottomPanel;
	private LevelComponent middleComponent;
	private Queue<Level> levels;
	private ArrayList<ImageIcon> buttonImgs;
	public static final int SELECTOR_WIDTH = 1000;
	public static final int SELECTOR_HEIGHT = 750;
	private JMenuBar bar;
	private JMenu menu;
	private JMenuItem magnets;
	private JMenuItem general;
	private BufferedImage trayIcon;
	
	/**
	 * Creates a new LevelSelector
	 */
	public LevelSelector()
	{
		setLayout(new BorderLayout());
		setSize(SELECTOR_WIDTH, SELECTOR_HEIGHT);
		levels = new LinkedList<Level>();
		
		middleComponent = new LevelComponent();
		bottomPanel  = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight()*1/8.0)));
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));

		prevButton = new JButton("Previous");
		nextButton = new JButton("Next");
		
		add(middleComponent);
		
		bottomPanel.add(prevButton);
		bottomPanel.add(nextButton);
		
		magnets = new JMenuItem("Magnet Information");
		general = new JMenuItem("General Information");
		menu = new JMenu("Help");
		bar = new JMenuBar();
		magnets.addActionListener(this);
		general.addActionListener(this);
		bar.add(menu);
		menu.add(general);
		menu.add(magnets);
		setJMenuBar(bar);
		
		try
		{
			trayIcon = ImageIO.read(getClass().getResourceAsStream("TrayLogo2.png"));
		}
		catch(IOException e){}
		
		this.setIconImage(trayIcon);
		setVisible(true);
		setTitle("Fridge Magnet");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	/**
	 * actionPerformed determines whether to open the Magnet information or the General information if the JMenuItems are pressed
	 * @param e the ActionEvent that determines which to open
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Magnet Information"))
			FridgeMagnet.InformationWindow.setVisible(true);
		if(e.getActionCommand().equals("General Information"))
			FridgeMagnet.GeneralInformation.setVisible(true);
	}
	
	
}

/**
 * LevelComponent controls the drawing of each level
 */
class LevelComponent extends JComponent implements MouseListener, MouseMotionListener
{
	int numLevels;
	private ArrayList<Point> levelPoints;
	private Point outlinePoint;
	private Color outlineColor;
	private boolean finished;
	private ArrayList<BufferedImage> imageArray;
	private int levelToOpen;
	protected static FridgeFrame currentFridgeFrame;
	private BufferedImage logo;

	/**
	 * Creates a new LevelComponent
	 */
	public LevelComponent()
	{
		repaint();
		levelPoints = new ArrayList<Point>();
		imageArray = new ArrayList<BufferedImage>();
		outlinePoint = null;	
		outlineColor = Color.yellow;
		finished = false;	
		addMouseListener(this);
		addMouseMotionListener(this);
		try
		{
			for(int i = 1; i <= 6; i++)
			{
				imageArray.add(ImageIO.read(getClass().getResourceAsStream("Level"+i+".png")));
			}
			logo = ImageIO.read(getClass().getResourceAsStream("MainLogo.png"));
		}
		catch(IOException e){System.out.println (e.getMessage());	}
		
	}
	
	/**
	 * paintComponent controls the drawing of the component
	 * @param g the graphics used to paint with
	 */
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.GRAY);
		Color boxColor = g.getColor();
		Font font = new Font(g.getFont().getName(), Font.PLAIN, 24);
		g.setFont(font);
		int count = 1;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{

				g.setColor(boxColor);
				int boxX = j *(LevelSelector.SELECTOR_WIDTH/4) + 192;
				int boxY = i * (LevelSelector.SELECTOR_HEIGHT/3) + 169;
				if(count<7)
					g.drawImage(imageArray.get(count-1), boxX, boxY, 100, 100,null);
				g.setColor(Color.red);
				if(count<7)
				g.drawString(String.valueOf(count++), boxX, boxY+18);
				
				if(outlinePoint != null)
				{	int outlineX = (int)(outlinePoint.getX());
					int outlineY = (int)(outlinePoint.getY());
					g.setColor(outlineColor);
					g.drawRect(outlineX,outlineY, 101, 101);	
					g.drawRect(outlineX-1,outlineY-1, 103, 103);	
				}
				
				if(levelPoints.size() < 6)
				{	
					levelPoints.add(new Point(boxX, boxY));
				}
				
			}
		}
	}
	
	/**
	 *Empty method for MouseListener
	 *@param event the mouse click event
	 */	
	public void mouseReleased(MouseEvent event) {}
	
	/**
	 *Opens new level if mouse is clicked
	 *@param event the mouse click event
	 */
	public void mouseClicked(MouseEvent event) 
	{
		if(!finished)
		{
			for(int i = 0; i < levelPoints.size(); i++)
			{
				Point p = levelPoints.get(i);
				int levelX = (int)(p.getX());
				int levelY = (int)(p.getY());
				int x = event.getX();
				int y = event.getY();
				if(x > levelX && x < levelX + 100 && y > levelY && y < levelY + 100)
				{
					outlinePoint = new Point(levelX-1, levelY-1);
					outlineColor = Color.green;
					repaint();
					levelToOpen = i+1;
					finished = true;
				}
			}
		}
		if(finished)
		{
			currentFridgeFrame = new FridgeFrame(levelToOpen);
			FridgeMagnet.LevelSelector.setVisible(false);	
		}
		
		
	} 
	/**
	 *Empty method for MouseListener
	 *@param event the mouse click event
	 */	
	public void mouseEntered(MouseEvent event)
	{
	}
	
	/**
	 *Empty method for MouseListener
	 *@param event the mouse click event
	 */
	 
	public void mouseExited(MouseEvent event) {}
	/**
	 *gets the x and y of the mouse click then class the repaint method
	 *@param event the mouse click event
	 */
	 
	public void mousePressed(MouseEvent event)
   	{
   		
   	}
   	
   	/**
	 *Highlights based on the mouse's position
	 *@param event the mouse click event
	 */	
   public void mouseMoved(MouseEvent event)
   {
   		if(!finished)
   		{
   			for(int i = 0; i < levelPoints.size(); i++)
			{
				Point p = levelPoints.get(i);
				int levelX = (int)(p.getX());
				int levelY = (int)(p.getY());
				int x = event.getX();
				int y = event.getY();
				if(x > levelX && x < levelX + 100 && y > levelY && y < levelY + 100)
				{
					outlinePoint = new Point(levelX-1, levelY-1);
					repaint();
				}
			}
   		}	
   }

	/**
	 *Empty method for MouseListener
	 *@param event the mouse click event
	 */	
    public void mouseDragged(MouseEvent e) {}
}