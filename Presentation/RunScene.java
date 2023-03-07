package Presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * This class shows the first user interface to the user, it is basically greetings and letting the user to browse their file 
 */
public class RunScene {
	//feilds
	private GridPane pane;
	private Image img;
	private Button showBoards;
	private Button cancel;
	private Label powerl;
	
	public RunScene(Stage stage) {
		initilaize(stage);
	}

	private void initilaize(Stage stage) {
		img = new Image("Images/bg.jpg");
		BackgroundImage bImg = new BackgroundImage(img,
				BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);
		
		Color c4=Color.web("#353535");

		setPane(new GridPane());
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(120,150,130,150));
		pane.setHgap(15);
		pane.setVgap(15);
		pane.setBackground(bGround); 
		
		//A label to show the number of power sources
		powerl= new Label();
		powerl.setFont(Font.font(16));
    	pane.add(powerl,1,3);
    	
    	//Add a LED Image to the scene
    	ImageView v=new ImageView(new Image("Images/icon.png"));
    	v.setFitWidth(60);
    	v.setFitHeight(100);
    	pane.add(v,0,4);
    	
    	showBoards = new Button("show boards");
    	showBoards.setTextFill(c4);
    	showBoards.setPrefSize(150, 30);
    	showBoards.setFont(Font.font(16));	
    	showBoards.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
    	pane.add(showBoards, 1, 4);
		
		//User button to exit
		cancel = new Button("Cancel");
    	cancel.setTextFill(c4);
		cancel.setPrefSize(100, 30);
		cancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		cancel.setFont(Font.font(16));
    	pane.add(cancel, 2, 4);  
    	
    	//Scene setting
    	Scene scene = new Scene(pane); 		
    	stage.setTitle("Power source supplied");
		stage.getIcons().add(new Image("Images/icon.png"));
		stage.setScene(scene);  
    	stage.show(); 
	}
	/*
	 * Getters and Setters
	 */
	private void setPane(GridPane gridPane) {
		this.pane=gridPane;
	}

	public GridPane getPane() {
		return pane;
	}

	public Button getShowBoards() {
		return showBoards;
	}

	public void setShowBoards(Button showBoards) {
		this.showBoards = showBoards;
	}

	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}

	public Label getPowerl() {
		return powerl;
	}

	public void setPowerl(Label powerl) {
		this.powerl = powerl;
	}
}