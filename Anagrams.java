
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	public void buildLetterTable() {
		    // Complete
	    letterTable = new HashMap<Character, Integer>();
		String alphabet  = "abcdefghijklmnopqrstuvwxyz";
	    for (int i=0; i<26; i++) {
	    	letterTable.put(alphabet.charAt(i), primes[i]);
	    }
	    System.out.println(letterTable);
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}

	public void addWord(String s) {
		    // Complete
		if (anagramTable.containsKey(myhashcode(s))) {
			ArrayList<String> values = new ArrayList<String>(anagramTable.get(myhashcode(s)));
			values.add(s);
			anagramTable.put(myhashcode(s), values);
		}
		else {
			ArrayList<String> values = new ArrayList<String>();
			values.add(s);
			anagramTable.put(myhashcode(s), values);
		}
	}
	
	public long myhashcode(String s) {
		    // Complete
	    long value = 1;
	    for (int i = 0; i<s.length(); i++) {
	    	value = value * letterTable.get(s.charAt(i));
	    }
	    return value;
	}
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    // Complete
	    ArrayList<Map.Entry<Long, ArrayList<String>>> anagrams = new ArrayList<>(anagramTable.entrySet());
	    ArrayList<Map.Entry<Long, ArrayList<String>>> listA = new ArrayList<>();
	    int currentMax = 0;
	    for (int i=1; i < anagrams.size(); i++) {
	    	if (anagrams.get(i).getValue().size() > currentMax) {
	    		listA.clear();
	    		listA.add(anagrams.get(i));
	    		currentMax = anagrams.get(i).getValue().size();
	    	}
	    	else if (anagrams.get(i).getValue().size() == currentMax) {
	    		listA.add(anagrams.get(i));
	    	}
	    }
	    return listA;
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
