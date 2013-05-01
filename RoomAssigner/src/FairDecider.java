import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * FairDecider runs the Borda Count algorithm on a list of people
 * to determine room assignments.
 * 
 * @author Brett Boston
 */
public class FairDecider {
	private List<Person> people;
	private Stack<Result> results;
	private Set<Integer> rooms;
	
	/**
	 * Creates a new FairDecider
	 * 
	 * @param people List of people who will be occupying the house
	 * @effects creates a new FairDecider
	 */
	public FairDecider(List<Person> people) {
		this.people = people;	// Representation Exposure!
		results = new Stack<Result>();
		Result max = new Result();
		max.incrementScore(Integer.MAX_VALUE);
		results.push(max);
		rooms = new HashSet<Integer>();
		buildRooms();
	}
	
	/**
	 * Builds the set of rooms
	 */
	private void buildRooms() {
		for (int i = 1; i <= people.size(); i++) {
			rooms.add(i);
		}
	}
	
	/**
	 * Runs the Borda Count algorithm and returns a stack
	 * containing all lowest results with equal score
	 * 
	 * @return a Stack containing all the lowest results
	 * 		   with equal score
	 */
	public Stack<Result> decide() {
		decide(0, 0, new Result());
		return results; // Representation exposure!
	}
	
	/**
	 * Private helper method for deciding room assignments
	 * 
	 * @param index index of people to examine
	 * @param score running total of the score at calling point
	 * 		  of the recursive algorithm
	 * @param r current result being built
	 */
	private void decide(int index, int score, Result r) {
		// Base Case: have data for every person
		if (index == people.size()) {
			Result top = results.peek();
			
			// If the result matches the score in the stack
			// then just add the result to the stack
			if (top.getScore() == r.getScore()) {
				results.push(new Result(r));
				
			// If the result is smaller than the score in
			// the stack, clear the stack and push r
			} else if (top.getScore() > r.getScore()) {
				results.clear();
				results.push(new Result(r));
			} // Else do nothing, r.score is too high
		}
		
		// Recursive Case
		else {
			Person p = people.get(index);
			Iterator<Integer> choices = p.iterator();
			int thisScore = 1; // Score for person p
			
			// Iterate through p's choices
			while (choices.hasNext()) {
				int room = choices.next();
				
				// If the room hasn't been picked, recurse
				if (rooms.contains(room)) {
					// Remove the room from the set to "reserve" it
					rooms.remove(room);
					
					// Set score and assignment for r
					r.setScore(score + thisScore);
					r.addAssignment(p, room);
					
					// Recursively decide on the next person
					decide(index + 1, score + thisScore, r);
					
					// Add the room back to the set of rooms
					rooms.add(room);
				}
				thisScore++;
			}
		}
	}
}
