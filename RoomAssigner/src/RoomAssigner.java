import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Fairly assigns rooms based on the Borda Count algorithm
 * 
 * @author Brett Boston
 */
public class RoomAssigner {
	
	// List of names of roommates
	public static final String[] names = 
		{"Adrian", "Anthony", "Brett", "Darrin", "Findlay", "Jamael", "Jason", "Ryan"};
	
	/**
	 * Main method runs the program
	 */
	public static void main(String[] args) {
		List<Person> people = buildPeople();
		getChoices(people);
		FairDecider f = new FairDecider(people);
		Stack<Result> results = f.decide();
		printResults(results, people);
	}
	
	/**
	 * Builds the list of people to live in the house
	 * 
	 * @return A List of people to live in the house
	 */
	public static List<Person> buildPeople() {
		List<Person> people = new ArrayList<Person>();
		for (String name : names) {
			people.add(new Person(name));
		}
		return people;
	}
	
	/**
	 * Gets a list of choices from the console for each individual
	 * 
	 * @param people List of people to get choices from
	 * @modifies people
	 * @effects adds choices to each person in people
	 */
	public static void getChoices(List<Person> people) {
		Scanner s = new Scanner(System.in);
		for (Person p : people) {
			System.out.println(p.getName());
			for (int i = 1; i <= names.length; i++) {
				System.out.print("Choice " + i + ": ");
				p.addChoice(s.nextInt());
			}
			System.out.println();
		}
		s.close();
	}
	
	/**
	 * Prints the results of the Borda Count algorithm
	 * 
	 * @param rs Stack of results to be printed
	 * @param people List of people whom the algorithm was run on
	 */
	public static void printResults(Stack<Result> rs, List<Person> people) {
		System.out.println("Found " + rs.size() + " results of equal score worth " + rs.peek().getScore() + " points.");
		while (!rs.isEmpty()) {
			System.out.println();
			Result r = rs.pop();
			for(Person p : people) {
				System.out.println(p.getName() + " : " + r.getAssignment(p));
			}
		}
	}
	
//	public static void simpleAssign(List<Person> people) {
//		for (Person p : people) {
//			for (int i = 1; i <= names.length; i++) {
//				p.addChoice(i);
//			}
//		}
//	}
}
