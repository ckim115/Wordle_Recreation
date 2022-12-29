import java.util.ArrayList;

public class Grid_Viewer {
	//for the word
	String[][] wordGrid;
	//for the colors
	String[][] colorGrid;
	int curRow = 0;
	int curCol = 0;
	
	public Grid_Viewer() 
	{
		wordGrid = new String[6][5];
		colorGrid = new String[6][5];
	}
	
	public void addWord(String w) {
		for(int i = 0; i < 5; i++) {
			wordGrid[curRow][i] = "" + w.charAt(i);
		}
	}
	
	public boolean isFiveLetter(String w) {
		if(w.length() != 5) {
			return false;
		} else {
			return true;
		}
	}
	
	public void correctLetters(ArrayList<Integer> arr) {
		for(int i = 0; i < arr.size(); i++) {
			colorGrid[curRow][arr.get(i)] = "green"; //green
		}
		curRow++;
	}
	
	public void existingLetters(ArrayList<Integer> arr) {
		for(int i = 0; i < arr.size(); i++) {
			colorGrid[curRow][arr.get(i)] = "yellow"; //yellow
		}
		curRow++;
	}
	
	public void wrongLetters(ArrayList<Integer> arr) {
		for(int i = 0; i < arr.size(); i++) {
			colorGrid[curRow][arr.get(i)] = "wrong"; //grey
		}
		curRow++;
	}
	
	public int getRow() {
		return curRow;
	}
	
	public int getCol() {
		return curCol;
	}
}
