import java.util.Iterator;
import java.util.Scanner;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	
	private static void formQueue(RandomizedQueue<String> q, int limit) {
		if (limit <= 0 || q == null || q.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		
		String s;

		try(Scanner scanner = new Scanner(System.in)){
			
			do {
				s = scanner.nextLine();
				if (!s.isBlank()) q.enqueue(s);
			}
			while(q.size() < limit || !s.isBlank());
		}
	}
	
	private static void printExactAmountOfElements(RandomizedQueue<String> q, int amount) {
		if (amount <= 0 || q == null || q.isEmpty())
		{
			throw new IllegalArgumentException();
		}
		
        Iterator<String> iterator = q.iterator();
		
		for(int i = 0; i < amount; i++)
		{
			System.out.println(iterator.next());
		}
	}
	
	private static int readIntFromConsole() {	
		try(Scanner s = new Scanner(System.in)) {
			String input = s.nextLine();
			
			return Integer.parseInt(input);
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = readIntFromConsole();
		
		formQueue(q, k);
		
		printExactAmountOfElements(q, k);
	}
}
