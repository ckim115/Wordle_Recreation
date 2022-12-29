import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game_Viewer extends Application {
	Stage stage;
	Grid_Viewer grid = new Grid_Viewer();
	GridPane keyboard;
	Wordle_Model wordle = new Wordle_Model();
	MenuItem newGame;
	MenuItem howToPlay;
	Button goBack;
	Image blankImage;
	Image greenImage;
	Image yellowImage;
	Image grayImage;
	GridPane MSpane;
	Scene scene;
	TextField input;
	Button enter;
	String word;
	ArrayList<Integer> green;
	ArrayList<Integer> yellow;
	ArrayList<Integer> gray;
	String guess;
	
	@Override
	public void start(Stage stage) throws Exception {
		word = wordle.getGameWord();
		
		this.stage = stage;
		VBox root = new VBox();
		blankImage = new Image(getClass().getClassLoader().getResource("resource/EmptyBlock.jpg").toString());
		
		MSpane = new GridPane();
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 5; j++) {
				ImageView b = new ImageView(blankImage);
				MSpane.add(b, j, i);
			}
		}
		
		//keyboard stuff
		keyboard = new GridPane();
		keyboard.setPrefWidth(210);
		keyboard.setPrefHeight(120);
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 7; j++) {
				if(!(i == 3 && j == 6)) {
					char c = (char) (65 + i + j);
					Image letter = new Image(getClass().getClassLoader().getResource("resource/" + c + "Gray.jpg").toString());
					ImageView b = new ImageView(letter);
					b.setFitHeight(30);
					b.setFitWidth(30);
					MSpane.add(b, i, wordle.getTurns());
				}
			}
		}
		
		MenuBar menubar = new MenuBar();
		Menu menu = new Menu("Menu");
		newGame = new MenuItem("New Game");
		howToPlay = new MenuItem("How To Play");
		menu.getItems().addAll(newGame, howToPlay);
		menubar.getMenus().add(menu);
		newGame.setOnAction(new MenuHandler());
		howToPlay.setOnAction(new MenuHandler());
				
		HBox inputRow = new HBox();
		input = new TextField();
		input.setPromptText("Enter word");
		inputRow.getChildren().addAll(input);
		
		enter = new Button("Enter");
		enter.setOnAction(new InputHandler());
		
		root.getChildren().addAll(menubar, MSpane, input, enter, keyboard);
		root.setAlignment(Pos.CENTER);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	private class MenuHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			MenuItem m = (MenuItem)event.getSource();
			if(m == newGame) {
				wordle.setTurns(0);
				word = wordle.getGameWord();
				for(int i = 0; i < 6; i++) {
					for(int j = 0; j < 5; j++) {
						ImageView b = new ImageView(blankImage);
						MSpane.add(b, j, i);
					}
				}
		    } else if (m == howToPlay) {
				OpenInstructions root = new OpenInstructions();
				try {
					root.start(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		}
	}
	
	private class InputHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			Button src = (Button)event.getSource();
			if (src == enter) {
				guess = input.getText().toUpperCase();
				input.clear();
				if(grid.isFiveLetter(guess) && wordle.realWord(guess)) {
					grid.addWord(guess);
					green = wordle.isGreen(guess);
					yellow = wordle.isYellow(guess);
					
					for(int i = 0; i < green.size(); i++) {
						greenImage = new Image(getClass().getClassLoader().getResource("resource/" + guess.charAt(green.get(i)) + "Green.jpg").toString());
						ImageView b = new ImageView(greenImage);
						MSpane.add(b, green.get(i), wordle.getTurns());
					}
					
					for(int i = 0; i < yellow.size(); i++) {
						yellowImage = new Image(getClass().getClassLoader().getResource("resource/" + guess.charAt(yellow.get(i)) + "Yellow.jpg").toString());
						ImageView b = new ImageView(yellowImage);
						MSpane.add(b, yellow.get(i), wordle.getTurns());
					}
					
					for(int i = 0; i < 5; i++) {
						boolean gray = true;
						for(int j = 0; j < yellow.size(); j++) {
							if(i == yellow.get(j)) {
								gray = false;
							}
						}
						for(int j = 0; j < green.size(); j++) {
							if(i == green.get(j)) {
								gray = false;
							}
						}

						if(gray) {
							grayImage = new Image(getClass().getClassLoader().getResource("resource/" + guess.charAt(i) + "Gray.jpg").toString());
							ImageView b = new ImageView(grayImage);
							MSpane.add(b, i, wordle.getTurns());
						}
					}
					
					wordle.addTurn();
					
					if(wordle.isGameWon(guess)) {
						BorderPane root = new BorderPane();
						root.setCenter(new Label("You Won! You took " + wordle.getTurns() + " turns."));
						goBack = new Button("Go Back");
						goBack.setOnAction(new InputHandler());
						root.setBottom(goBack);
						stage.setScene(new Scene(root));
					} else if (wordle.isGameLost(guess)) {
						BorderPane root = new BorderPane();
						root.setCenter(new Label("You Lost. The real word was " + word + "."));
						goBack = new Button("Go Back");
						goBack.setOnAction(new InputHandler());
						root.setBottom(goBack);
						stage.setScene(new Scene(root));
					}
				}
			} else if (src == goBack) {
				stage.setScene(scene);
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
