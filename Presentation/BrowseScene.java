package Presentation;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
 * This class create the controller for when the browsing file scene
 * it allows the user to browse input file and then if there is an error the program show an alert
 */
public class BrowseScene {
	//feilds
	private GridPane pane;
	private Image img;
	private Label hello;
	private Label process;
	private Button run;
	private Button browse;
	private Button cancel;
	
	public BrowseScene(Stage stage) {
		initialize(stage);
	}

	private void initialize(Stage stage) {
		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(120,150,130,150));
		pane.setHgap(10.5);
		pane.setVgap(10.5); 
		
		//A color that is repeatedly used in the project
		Color c4=Color.web("#353535");
		
		//Creating Background Image
		img = new Image("images/bg.jpg");
		BackgroundImage bImg = new BackgroundImage(img,
				BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);
		pane.setBackground(bGround); 
		
		//Label Greeting User
		hello = new Label("Hello user, Select your LEDs and Power file...");
		hello.setFont(Font.font(16));  
		hello.setTextFill(c4);
		pane.add(hello, 0,5);
		
		//This label will be updated by process of file importing
		process = new Label("Process");
		process.setFont(Font.font(12));  
		process.setTextFill(Color.RED );
		pane.add(process , 0, 6);
		
		//User button to run the program
		run = new Button("Run");
		run.setFont(Font.font(14)); 
		run.setTextFill(c4);
		run.setPrefSize(100, 30);
		run.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		
		//User Button to browse file
		browse = new Button("Browse"); 
		browse.setFont(Font.font(14)); 
		browse.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		browse.setTextFill(c4);
		browse.setPrefSize(100, 30);
		pane.add(browse , 1,6);
		
		//User button to exit
		cancel = new Button("Cancel");
		cancel.setFont(Font.font(14)); 
		cancel.setTextFill(c4);
		cancel.setPrefSize(100, 30);
		cancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		pane.add(cancel,2,6); 
		cancel.setOnAction(e -> Platform.exit()); 
		
		//Create Scene
		Scene scene  = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Max LED Lighting");
		stage.getIcons().add(new Image("images/icon.png"));
		stage.show();
	}
	/*
	 * Getters and Setters
	 */
	public GridPane getPane() {
		return pane;
	}

	public void setPane(GridPane pane) {
		this.pane = pane;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Label getHello() {
		return hello;
	}

	public void setHello(Label hello) {
		this.hello = hello;
	}

	public Label getProcess() {
		return process;
	}

	public void setProcess(Label process) {
		this.process = process;
	}

	public Button getRun() {
		return run;
	}

	public void setRun(Button run) {
		this.run = run;
	}

	public Button getBrowse() {
		return browse;
	}

	public void setBrowse(Button browse) {
		this.browse = browse;
	}

	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}
}
