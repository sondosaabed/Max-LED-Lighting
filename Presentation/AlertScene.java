package Presentation;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/*
 * This class creates a scene of showing an alert message to the user
 */
public class AlertScene {
    //Feilds
	private Stage stage;
    private Label message;
	private Button cancel;
    private GridPane pane;
    
    public AlertScene(){
        initialize();
    }
    
    private void initialize(){
        setStage(new Stage());

		pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(100, 100, 100, 100));
		pane.setHgap(15);
		pane.setVgap(15);

		Image img = new Image("Images/icon.png");
		ImageView v = new ImageView(img);
		v.setFitWidth(70);
		v.setFitHeight(100);

		Button logo = new Button();
		logo.setPrefSize(70, 100);
		logo.setGraphic(v);
		logo.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
		GridPane.setHalignment(logo, HPos.CENTER);
		pane.add(logo, 0, 0);
		
		message=new Label();
		//message.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, FontPosture.REGULAR, 20));
		message.setTextFill(Color.ORANGERED);
		pane.add(message, 0, 1);
		
		//User button to exit
		cancel = new Button("Okay");
		//cancel.setFont(Font.font("Lucida Sans Unicode", FontWeight.BOLD, FontPosture.REGULAR, 16));
		cancel.setPrefSize(80, 20);
		GridPane.setHalignment(cancel,HPos.CENTER);
		cancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		pane.add(cancel,0,2); 

		Scene scene = new Scene(pane);
		stage=new Stage();
		stage.setScene(scene);
		stage.getIcons().add(new Image("Images/icon.png"));
    }
	/*
	 * Getters and Setters
	 */
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Label getMessage() {
        return message;
    }

    public void setMessage(Label message) {
        this.message = message;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }
}