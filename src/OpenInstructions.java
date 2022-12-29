import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OpenInstructions extends Application 
{
	VBox overallRoot;
	Button goBack;
	Stage s;

	public static void main(String[] args) 
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		s = stage;
		overallRoot = new VBox();
		VBox topInstructions = new VBox();
		
		//labels in the topInstructions
		Label guessLabel = new Label("Guess the *WORDLE* in six tries. \n");
		Label validGuessLabel = new Label("Each guess must be a five-letter word that exists in the \ngame's database.\nHit the enter button to submit. \n");
		Label colorChangeLabel = new Label("After each guess, the color of the tiles will change to show \nhow close your guess was to the word. \n \n");
		guessLabel.setFont(Font.font("Sans-Serif", 20));
		validGuessLabel.setFont(Font.font("Sans-Serif", 20));
		colorChangeLabel.setFont(Font.font("Sans-Serif", 20));
		guessLabel.setAlignment(Pos.CENTER);
		validGuessLabel.setAlignment(Pos.CENTER);
		colorChangeLabel.setAlignment(Pos.CENTER);
		
		topInstructions.getChildren().addAll(guessLabel, validGuessLabel, colorChangeLabel);

		goBack = new Button("Go Back");
		goBack.setOnAction(new InputHandler());
		
		//image stuff
		FileInputStream inputStream = new FileInputStream("wordleProject.png");
		Image wordleExamples = new Image(inputStream);
		ImageView imageView = new ImageView(wordleExamples);
		imageView.setX(100);
		imageView.setY(100);
		
		
		
		Group imageRoot = new Group(imageView);
		overallRoot.getChildren().addAll(topInstructions, imageRoot);
		Scene scene = new Scene(overallRoot, 600, 600);

		overallRoot.getChildren().addAll(topInstructions, imageRoot, goBack);
		Scene scene1 = new Scene(overallRoot, 550, 600);
		stage.setTitle("Wordle Instructions");
		stage.setScene(scene1);
		stage.setResizable(true);
		stage.sizeToScene();
		stage.show();
	}
	
	private class InputHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			Button src = (Button)event.getSource();
			if (src == goBack) {
				Game_Viewer game = new Game_Viewer();
				try {
					game.start(s);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
