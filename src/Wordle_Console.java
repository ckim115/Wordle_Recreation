import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Wordle_Console {
	
	public static void main(String args) {
		
		System.out.println("Welcome to wordle");
		
	} 
	
	
	
	public static boolean uniqueWord(String word) {
		for(int i = 0; i < word.length(); i ++) {
			char check = word.charAt(i);
			for(int j = i+1; j < word.length(); j++) {
				if(word.charAt(j) == check) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}