/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *FridgeMagnet
 * FridgeMagnet creates all of the windows and contains information about the levels
 */
import java.util.*;


/**
 * FridgeMagnet creates all of the windows and contains information about the levels
 */
public class FridgeMagnet
{
	public static LevelSelector LevelSelector;
	public static InformationWindow InformationWindow;
	public static GeneralInformation GeneralInformation;
	public static TreeMap<Integer, Level> levels;
	private static LevelCreator LevelCreator;

    public static void main(String[] args)
    {
    	LevelCreator = new LevelCreator();
    	levels = LevelCreator.createLevels();
    	LevelSelector = new LevelSelector();
    	InformationWindow = new InformationWindow();
    	GeneralInformation = new GeneralInformation();
    }
}


/**
 * LevelCreator creates and adds each level to the TreeMap
 */
class LevelCreator
{
	private ArrayList<Magnetic> levelMagnets;
	private ArrayList<Obstacle> levelObstacles;
	private Level currentLevel;
	
	/**
	 * Constructs an ArrayList of Magnetics and an ArrayList of Obstacles that are used in each level
	 */
	public LevelCreator()
	{
		levelMagnets = new ArrayList<Magnetic>();
		levelObstacles = new ArrayList<Obstacle>();
	}
	
	/**
	 * createLevels() creates each level for the game
	 * @return TreeMap<Integer, Level> TreeMap of each level in the game
	 */
	public TreeMap<Integer, Level> createLevels()
	{	
		TreeMap<Integer, Level> levels = new TreeMap<Integer,Level>();
		/* 	Level One
		 * 	Contents:
		 *	- 1 Bar Magnet
		 *	- 1 Finish Magnet
		 */
		currentLevel = new Level();
		currentLevel.createMagnet(new BarMagnet(50,625,0));
		currentLevel.createMagnet(new BarMagnet(50,625,0));
		currentLevel.createMagnet(new FinishMagnet(50,300,0));
		currentLevel.createObstacle(new Wall(25,200, 100, 10, 0));
		levels.put(1, currentLevel);
		clearLevelData();
		
		/* 	Level Two
		 * 	Contents:
		 *	- 1 Cannon Magnet
		 *	- 1 Finish Magnet
		 */
		currentLevel=new Level();
		currentLevel.createMagnet(new CannonMagnet(50,625,0));
		currentLevel.createObstacle(new Wall(450,400, 100, 10, 0));
		currentLevel.createObstacle(new Wall(460,400, 100, 10, 90));
		currentLevel.createObstacle(new Wall(450,500, 100, 10, 0));
		currentLevel.createMagnet(new FinishMagnet(500,450,0));
		levels.put(2,currentLevel);
		clearLevelData();
			
		/* 	Level Three
		 * 	Contents:
		 *	- 1 Fan Magnet
		 *	- 1 Finish Magnet
		 */
		currentLevel=new Level();
		currentLevel.createMagnet(new FanMagnet(50,625,0));
		currentLevel.createMagnet(new BarMagnet(250,625,0));
		currentLevel.createMagnet(new FinishMagnet(25,400,0));
		levels.put(3,currentLevel);
		clearLevelData();
		
		/* 	Level Four
		 * 	Contents:
		 *	- 1 Beam Magnet
		 *	- 1 Finish Magnet
		 *	- 1 Wall
		 */
		currentLevel=new Level();
		currentLevel.createMagnet(new BeamMagnet(50,625,0));
		currentLevel.createMagnet(new FinishMagnet(500,200,0));
		currentLevel.createObstacle(new Wall(50,200,100,25,30));
		levels.put(4,currentLevel);
		clearLevelData();
		
		/* 	Level Five
		 * 	Contents:
		 *	- 1 Cannon Magnet
		 *	- 1 Beam Magnet
		 *  - 1 Finish Magnet
		 *	- 3 Horizontal Walls
		 *	- 1 Vertical Wall
		 */
		currentLevel = new Level();
		currentLevel.createMagnet(new BeamMagnet(100,625,0));
		currentLevel.createMagnet(new CannonMagnet(50,625,0));
		currentLevel.createMagnet(new FinishMagnet(50,400,0));
		currentLevel.createObstacle(new Wall(150,350,100,10,0));
		currentLevel.createObstacle(new Wall(100,350,100,10,0));
		currentLevel.createObstacle(new Wall(0,350,100,10,0));
		currentLevel.createObstacle(new Wall(250,350,100,10,90));
		levels.put(5, currentLevel);
		clearLevelData();
		
		/* 	Level Three
		 * 	Contents:
		 *	- 1 Cannon Magnet
		 *	- 1 Vertical and vertically moving wall
		 *  - 1 Finish Magnet
		 *	- 1 45 degree angle wall
		 *  - 1 -45 degree angle wall
		 */
		currentLevel = new Level();
		currentLevel.createMagnet(new CannonMagnet(50,650,0));
		currentLevel.createMagnet(new FinishMagnet(600,300,0));
		currentLevel.createObstacle(new Wall(550,350,100,25,45));
		currentLevel.createObstacle(new Wall(550,250,100,25,-45));

		levels.put(6, currentLevel);
		clearLevelData();

		return levels;
	}
	
	/**
	 * Resets the data of the current level
	 */
	private void clearLevelData()
	{
		levelMagnets.clear();
		levelObstacles.clear();
	}
	
}
