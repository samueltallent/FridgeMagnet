/*
 *Aaron Comen and Sam Tallent
 *Gallatin
 *2
 *Level
 * The Level class contains the data and methods used for each level
 */
import java.util.*;

/**
 * The Level class contains the data and methods used for each level
 */
public class Level
{
	private int levelNum;
	private HashSet<Magnetic> magnets;
	private HashSet<Obstacle> obstacles;
	
	/**
	 * Level constructs a new Level with an empty HashSet of Magnetics and of Obstacles
	 */
	public Level()
	{
		magnets = new HashSet<Magnetic>();
		obstacles = new HashSet<Obstacle>();
	}
	
	/**
	 * Adds a Magnetic to the HashSet<Magnetic>
	 * @param m the Magnetic to add to the HashSet
	 */
	public void createMagnet(Magnetic m)
	{
		magnets.add(m);
	}
	
	/**
	 * Adds an Obstacle to the HashSet<Obstacle>
	 * @param o the Obstacle to add to the HashSet
	 */
	public void createObstacle(Obstacle o)
	{
		obstacles.add(o);
	}
	
	/**
	 * Gets the HashSet<Magnetic>
	 * @return HashSet<Magnetic> the Magnetics in the level
	 */
	public HashSet<Magnetic> getMagnets()
	{
		return magnets;
	}
	
	/**
	 * Gets the HashSet<Obstacle>
	 * @return HashSet<Obstacle> the Obstacles in the level
	 */
	public HashSet<Obstacle> getObstacles()
	{
		return obstacles;
	}
	
	
}