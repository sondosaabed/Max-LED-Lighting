package Presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/*
 * This class shows a scene when the powers sources are more than 20 sources of power to the user 
 * it shows the the optimal solution only of maximum leds...
 */
public class PowerMT20Scene {
	//feilds
	private Label maxl;
	private GridPane pane;
	private Button btCancel;
	private Label lcss;
	
	public PowerMT20Scene(Stage stage) {
		initialize(stage);
	}
	
	private void initialize(Stage stage) {
		Color c4=Color.web("#353535");
		
		setPane(new GridPane());
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(120,150,130,150));
		pane.setHgap(15);
		pane.setVgap(15);
		BackgroundFill bGroundb1 = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
		Background bGround1 = new Background(bGroundb1);
		pane.setBackground(bGround1); 
		
		//Label to show the max number of powered LEDs
		maxl= new Label();
		maxl.setFont(Font.font(16));
    	pane.add(maxl,1,1);
		
    	//Label that shows the exact powered LEDs
    	lcss= new Label();
    	lcss.setFont(Font.font(14));
    	lcss.setTextFill(Color.GREEN);
    	pane.add(lcss,1,2);
    	
    	//User button to exit
		btCancel = new Button("Cancel");
    	btCancel.setTextFill(c4);
		btCancel.setPrefSize(100, 30);
		btCancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:lightyellow;");
		btCancel.setFont(Font.font(16));
    	pane.add(btCancel, 2, 1);  
    	
    	//Scene setting
    	Scene scene = new Scene(pane); 		
    	stage.setTitle("Power source supplied");
		stage.getIcons().add(new Image("images/icon.png"));
		stage.setScene(scene);  
    	stage.show(); 
	}
	/*
	 * Getters and Setters
	 */
	private void setPane(GridPane gridPane) {
		this.pane=gridPane;
	}
	
	public Label getMaxl() {
		return maxl;
	}

	public void setMaxl(Label maxl) {
		this.maxl = maxl;
	}

	public Button getBtCancel() {
		return btCancel;
	}

	public void setBtCancel(Button btCancel) {
		this.btCancel = btCancel;
	}

	public Label getLcss() {
		return lcss;
	}

	public void setLcss(Label lcss) {
		this.lcss = lcss;
	}

	public GridPane getPane() {
		return pane;
	}
}