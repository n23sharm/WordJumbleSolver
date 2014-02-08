import java.util.*;
import java.io.*;

public class Tester {
	
	public static void main(String[] args) {
		WordJumbleSolver solver = new WordJumbleSolver();
		try {
			solver.initializeDictionary();
		} catch (IOException e) {
			System.out.println("IOException occured: " + e.getMessage());
		} 	
		
		ArrayList<String> words = solver.getJumbledWords(args.length > 0 ? args[0] : "");
		System.out.println("Solution: " + words);
	}
}