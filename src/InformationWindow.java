/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *InformationWindow
 *Class for a JFrame that contains information about each of the algorithms
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import javax.swing.text.*;

/**
 * Class for a JFrame that contains information about each of the algorithms
 */
public class InformationWindow extends JFrame implements ActionListener
{
	protected static final int FRAME_WIDTH = 350;
	protected static final int FRAME_HEIGHT = 150;
	private LeftPanel leftPanel;
	private JPanel centerPanel;
	private JTextArea text;
	private JTextPane output;
	private JComboBox<String> magnets;
	private BufferedImage trayIcon;

	
	/**
	 * Creates a new InformationWindow
	 */
	public InformationWindow()
	{
		this.setTitle("Information");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLayout(new BorderLayout());
		
		leftPanel = new LeftPanel();
		leftPanel.setPreferredSize(new Dimension(InformationWindow.FRAME_WIDTH/2, InformationWindow.FRAME_HEIGHT));
		centerPanel = new JPanel();
		//centerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Information")); 
		
		String[] magTypes = {"Bar Magnet", "Beam Magnet", "Cannon Magnet", "Fan Magnet"};
		magnets = new JComboBox<String>(magTypes);
		magnets.addActionListener(this);
		
		text = new JTextArea(5,15);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setBackground(new Color(238,238,238));
		centerPanel.add(text);

		try
		{
			trayIcon = ImageIO.read(getClass().getResourceAsStream("TrayLogo2.png"));
		}
		catch(IOException e){}
		
		setIconImage(trayIcon);
		
		//centerPanel.add(text);
		leftPanel.add(magnets);
		add(leftPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		setResizable(false);
		setVisible(false);
	}
	
	/**
	 * Gets the selected magnet in the JComboBox
	 * @return the string of the selected item
	 */
	public String getSelected()
	{
		return (String)(magnets.getSelectedItem());
	}
	
	/**
	 * actionPerformed repaints the LeftPanel and centerPanel if an ActionEvent fires
	 * @param e the ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
		leftPanel.repaint();
		centerPanel.repaint();
	}
	
	/**
	 * Sets the text of the JTextArea
	 * @param s the text to set
	 */
	public void setText(String s)
	{
		text.setText(s);
	}
	
}


/**
 * LeftPanel is the panel that contains the images that are drawn 
 */
class LeftPanel extends JPanel
{
	ArrayList<BufferedImage> images;
	private int width = InformationWindow.FRAME_WIDTH;
	private int height = InformationWindow.FRAME_HEIGHT;	
	private BufferedImage barImage;
	private BufferedImage beamImage;
	private BufferedImage cannonImage;
	private BufferedImage fanImage;
	
	/**
	 * Constructs a new LectPanel
	 */
	public LeftPanel()
	{
		setPreferredSize(new Dimension(width, height));
		try
		{
			barImage = ImageIO.read(getClass().getResourceAsStream("BarMagnet.png"));
			beamImage = ImageIO.read(getClass().getResourceAsStream("UFO_Small.png"));
			cannonImage = ImageIO.read(getClass().getResourceAsStream("AcceleratorMagnet.png"));
			fanImage = ImageIO.read(getClass().getResourceAsStream("fan.gif"));
		}
		catch(IOException e){}
		
	}
		
	/**
	 * paintComponent controls the drawing of the component
	 * @param g the graphics used to paint with
	 */
	public void paintComponent(Graphics g)
	{
		switch(FridgeMagnet.InformationWindow.getSelected())
		{
			case "Bar Magnet":
				g.drawImage(barImage,37, height/2, barImage.getWidth(), barImage.getHeight(), null);
					FridgeMagnet.InformationWindow.setText("                Bar Magnet\nThis is a simple magnet that acts as a simple wall or floor for the ball. It does not change the speed of the ball at all.");
					break;
			case "Beam Magnet":
				g.drawImage(beamImage,60, height/4, beamImage.getWidth(), beamImage.getHeight(), null);
					FridgeMagnet.InformationWindow.setText("             Beam Magnet\nThis magnet pulls the ball towards it while also slowing down its vertical and horizontal speeds.");
					break;
			case "Cannon Magnet":
				g.drawImage(cannonImage,37, height/2, cannonImage.getWidth(), cannonImage.getHeight(), null);
					FridgeMagnet.InformationWindow.setText("           Cannon Magnet\nThis magnet accelerates the ball in the direction that its arrows are pointing while it is in contact with the ball.");
					break;
			case "Fan Magnet":
				g.drawImage(fanImage,63, height/4, fanImage.getWidth()*3/4, fanImage.getHeight()*3/4, null);
					FridgeMagnet.InformationWindow.setText("           Fan Magnet\nThis magnet blows the ball if it is in a small area around the fan. It only affects the horizontal speed.");
					break;
			
		}
	}
	
}