import java.util.HashMap;
import java.util.Map;

/**
 * Result represents a single room assignment, with score
 * and individual assignments
 * 
 * @author Brett Boston
 */
public class Result {
	private Map<Person, Integer> assignments;
	private int score;
	
	/**
	 * Construct a new empty Result
	 * 
	 * @effects creates a new empty Result
	 */
	public Result() {
		assignments = new HashMap<Person, Integer>();
		score = 0;
	}
	
	/**
	 * Construct a new result copied from r
	 * 
	 * @param r Result to copy
	 * @effect creates a new Result copied from r
	 */
	public Result(Result r) {
		assignments = new HashMap<Person, Integer>(r.assignments);
		score = r.score;
	}
	
	/**
	 * Gets the score of this Result
	 * 
	 * @return the score of this Result
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the score of this Result
	 * 
	 * @param ns new score to set this.score to
	 * @modifes score
	 * @effects sets score to ns
	 */
	public void setScore(int ns) {
		score = ns;
	}
	
	/**
	 * Increments the score by delta
	 * 
	 * @param delta amount to increment the score by
	 * @modifies score
	 * @effects Increments the score by delta
	 */
	public void incrementScore(int delta) {
		score += delta;
	}
	
	/**
	 * Adds a room assignment to p
	 * 
	 * @param p person to assign to room
	 * @param room room to assign p to
	 * @modifies assignments
	 * @effects adds <p, room> to assignments
	 */
	public void addAssignment(Person p, int room) {
		assignments.put(p, room);
	}
	
	/**
	 * Gets the room p is assigned to
	 * 
	 * @param p person to query room assignment for
	 * @return the assignment of p
	 */
	public int getAssignment(Person p) {
		return assignments.get(p);
	}
}
