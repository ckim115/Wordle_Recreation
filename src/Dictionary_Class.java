import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary_Class {
	ArrayList<String> words = new ArrayList<String>();
	String word;
	
	public Dictionary_Class() {
		try {
			Scanner in = new Scanner(new File("words.txt"));
			while(in.hasNext()) {
				String s = in.next();
				if(uniqueWord(s)) {
					words.add(s);
				}
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public ArrayList<String> getWords() {
		return words;
	}
	
	public boolean uniqueWord(String word) {
		boolean unique = true;
		for(int i = 0; i < word.length(); i ++) {
			char check = word.charAt(i);
			for(int j = i+1; j < word.length(); j++) {
				if(word.charAt(j) == check) {
					unique = false;
				}
			}
		}
		if(word.length() != 5) {
			unique = false;
		}
		return unique;
	}
}
