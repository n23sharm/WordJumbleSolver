import java.util.*;
import java.lang.*;
import java.io.*;

public class WordJumbleSolver {
	
	HashMap<String, ArrayList<String>> mWordMap = new HashMap<String, ArrayList<String>>();

	/*
	* Read a .txt file of dictionary words.
	* For each word, sort it alphabetically and store the sorted string into a hashmap as the key.
	* Append that word to value of that key. 
	*/
	public void initializeDictionary() throws FileNotFoundException, IOException {
		BufferedReader inFile = new BufferedReader(new FileReader("dictionary.txt"));
		String word;
		while ((word = inFile.readLine()) != null) {
			word = word.toLowerCase();
			
			String sorted = getSortedString(word);
			ArrayList<String> wordList;
			
			if (mWordMap.containsKey(sorted)) {
				wordList = mWordMap.get(sorted);
			} else {
				wordList = new ArrayList<String>();
			}
			wordList.add(word);
			mWordMap.put(sorted, wordList);
		}
		inFile.close();
	}

	/*
	* Get permuations of all possible lenghts of jumbled words.
	*/
	public ArrayList<String> getJumbledWords(String input) {
		ArrayList<String> wordList = new ArrayList<String>();
		String word = getSortedString(input.toLowerCase());
		
		for (int i = 2; i <= word.length(); ++i) {
			ArrayList<String> permutations = combinations(word, i);
			for (String permutation : permutations) {
				if (mWordMap.containsKey(permutation)) {
					wordList.addAll(mWordMap.get(permutation));
				}
			}
		}
		return wordList;
	}
	
	/*
	* Recursively get permutations of a certain length of string. 
	*/
	private ArrayList<String> combinations(String word, int sizeNeeded) {
		ArrayList<String> retValue = new ArrayList<String>();
		for (int i = 0;  i <= word.length() - sizeNeeded; ++i) {
			if (sizeNeeded == 1) {
				retValue.add(String.valueOf(word.charAt(i)));
			} else {
				char letter = word.charAt(i);
				ArrayList<String> recursive = combinations(word.substring(i+1), sizeNeeded-1);
				for (String w : recursive) {
					retValue.add(letter + w);
				}
			}
		}
		
		return retValue;
	}
	
	/*
	* Sort the list alphabetically.
	*/
	private String getSortedString(String word) {
		char[] letters = word.toCharArray();
		Arrays.sort(letters);
		String sorted = new String(letters);
		return sorted;
	}
}


