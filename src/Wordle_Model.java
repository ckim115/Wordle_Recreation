import java.util.ArrayList;
import java.util.Random;


public class Wordle_Model {
	Dictionary_Class dic = new Dictionary_Class();
	String word;
	ArrayList<String> fiveLetterWords;
	ArrayList<Integer> green;
	ArrayList<Integer> yellow;
	int turns;
	
	public Wordle_Model() {
		fiveLetterWords = dic.getWords();
	}
	
	public ArrayList<String> getListOfWords(){
		return fiveLetterWords;
	}
	
	public String getGameWord() {
		Random rand = new Random();
		word = fiveLetterWords.get(rand.nextInt(fiveLetterWords.size()));
		while(word.length() < 5) {
			word = fiveLetterWords.get(rand.nextInt(fiveLetterWords.size()));
		}
		
		return word;
	}
	
	//return index # @ which the letters match
	public ArrayList<Integer> isGreen(String word) {
		ArrayList<Integer> green = new ArrayList<Integer>();
		for(int i = 0; i < this.word.length(); i++) {
			if(this.word.charAt(i) == word.charAt(i)) {
				green.add(i);
			}
		}
		
		return green;
	}
	
	//return index # @ which letters are in the word but wrong place
	public ArrayList<Integer> isYellow(String word) {
		ArrayList<Integer> yellow = new ArrayList<Integer>();
		for(int i = 0; i < this.word.length(); i++) {
			for(int j = 0; j < this.word.length(); j++) {
				if(word.charAt(i) == this.word.charAt(j) && word.charAt(i) != this.word.charAt(i)) {
					yellow.add(i);
					break;
				}
			}
		}
		
		return yellow;
	}
	
	//return index # @ which the letters match
	public void setGreen(ArrayList<Integer> list) {
		green = list;
	}
		
		//return index # @ which letters are in the word but wrong place
	public void setYellow(ArrayList<Integer> list) {
		yellow = list;
	}
	
	public void setTurns(int t) {
		turns = t;
	}
	
	public int getTurns() {
		return turns;
	}
	
	public void addTurn() {
		turns++;
	}
	
	public boolean realWord(String guess) {
		boolean real = false;
		for(int i = 0; i < fiveLetterWords.size(); i++) {
			if(guess.equals(fiveLetterWords.get(i))) {
				real = true;
			}
		}
		return real;
	}
	
	public boolean isGameLost(String guess) {
		if(!word.equals(guess) && turns == 6) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isGameWon(String guess) {
		if(word.equals(guess)) {
			return true;
		} else {
			return false;
		}
	}
}
