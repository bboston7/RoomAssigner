import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A Person represents a single person.  It maintains a list of
 * preferences to be used with a Borda count client.
 * <p>
 * 
 * No duplicates may exist in the list of preferences, or the
 * behavior of the algorithm is undefined
 * 
 * @author Brett Boston
 */
public class Person {
	private final String name;
	private List<Integer> preferences;
	
	/**
	 * Construct a new Person named name
	 * 
	 * @param name The person's name
	 * @effects Constructs a new Person named name
	 */
	public Person(String name) {
		this.name = name;
		preferences = new LinkedList<Integer>();
	}
	
	/**
	 * Returns the name of this Person
	 * 
	 * @return the name of this Person
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Adds a choice to the person's list of preferences
	 * at the back
	 * 
	 * @param room Room number to be added
	 * @modifies this
	 * @effects Adds room to the person's list of preferences
	 * 			at the back.
	 */
	public void addChoice(int room) {
		preferences.add(room);
	}
	
	/**
	 * Gets an iterator over the rooms in order of most
	 * favorable to least favorable
	 * 
	 * @return an iterator over the rooms
	 */
	public Iterator<Integer> iterator() {
		return preferences.iterator();
	}
}
