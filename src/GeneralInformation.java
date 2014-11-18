/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *GeneralInformation
 *Class for a JFrame that contains information about each of the magnets
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
 * Class for a JFrame that contains information about each of the magnets
 */
public class GeneralInformation extends JFrame implements ActionListener
{
	protected static final int FRAME_WIDTH = 280;
	protected static final int FRAME_HEIGHT = 325;
	private NorthPanel northPanel;
	private JPanel centerPanel;
	private JTextArea text;
	private JTextPane output;
	private JComboBox<String> magnets;
	private BufferedImage trayIcon;

	
	/**
	 * Creates a new InformationWindow
	 */
	public GeneralInformation()
	{
		this.setTitle("Information");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLayout(new BorderLayout());
		
		northPanel = new NorthPanel();
		northPanel.setPreferredSize(new Dimension(InformationWindow.FRAME_WIDTH/2, InformationWindow.FRAME_HEIGHT));
		centerPanel = new JPanel();	
		String[] magTypes = {"Bar Magnet", "Beam Magnet", "Cannon Magnet", "Fan Magnet"};
		magnets = new JComboBox<String>(magTypes);
		magnets.addActionListener(this);
		
		text = new JTextArea(10,20);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setBackground(new Color(238,238,238));
		text.setText("The goal of the game is to move the ball from its starting position to the finish.  Each level will have varying obstacles that will challenge you to use the magnets provided to reach the end.  The magnets can be moved by dragging and dropping with the left mouse and rotated by using the right mouse.");
		centerPanel.add(text);

		
		try
		{
			trayIcon = ImageIO.read(getClass().getResourceAsStream("TrayLogo2.png"));
		}
		catch(IOException e){}
		
		setIconImage(trayIcon);
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.SOUTH);
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
	 * actionPerformed repaints the centerPanel if an ActionEvent fires
	 * @param e the ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
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
 * NorthPanel is the panel that contains the logo
 */
class NorthPanel extends JPanel
{
	ArrayList<BufferedImage> images;
	private int width = InformationWindow.FRAME_WIDTH/2;
	private int height = InformationWindow.FRAME_HEIGHT;	
	private BufferedImage logo;

	/**
	 * Constructs a new NorthPanel
	 */
	public NorthPanel()
	{
		setPreferredSize(new Dimension(width, height));
		try
		{
			logo = ImageIO.read(getClass().getResourceAsStream("MainLogoSmall.png"));
		}
		catch(IOException e){} 
	}
		
	/**
	 * paintComponent controls the drawing of the component
	 * @param g the graphics used to paint with
	 */
	public void paintComponent(Graphics g)
	{
		g.drawImage(logo,0,0,logo.getWidth(),logo.getHeight(), null);
	}
	
}