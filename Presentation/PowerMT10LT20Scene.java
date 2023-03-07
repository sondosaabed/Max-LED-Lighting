package Presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * This class shows a scene when the powers sources are more than 10 sources of power and less than 20 to the user 
 * it shows the optimal solution and sllows the user to see the dynamic programming table
 */
public class PowerMT10LT20Scene {
	//feilds
	private Label powerl;
	private GridPane pane;
	private Label maxl;
	private Label lcss;
	private Button btCancel;
	private Button show;
	
	public PowerMT10LT20Scene(Stage stage) {
		initialize(stage);
	}
	
	private void initialize(Stage stage) {
		Color c4=Color.web("#353535");
		setPane(new GridPane());
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(120,150,130,150));
		pane.setHgap(15);
		pane.setVgap(15);
		
		//A label to show the number of power sources
		powerl= new Label();
		powerl.setFont(Font.font(16));
    	pane.add(powerl,1,3);
    	
    	//Add a LED Image to the scene
    	Image img1 = new Image("Images/icon.png");
    	ImageView v=new ImageView(img1);
    	v.setFitWidth(60);
    	v.setFitHeight(100);
    	pane.add(v,0,4);
    	
		//Label to show the max number of powered LEDs
		maxl= new Label();
		maxl.setFont(Font.font(16));
    	pane.add(maxl,1,4);
    	
    	lcss= new Label();
    	lcss.setFont(Font.font(14));
    	lcss.setTextFill(Color.GREEN);
    	pane.add(lcss,2,4);
    	
		show = new Button("show table");
    	show.setTextFill(c4);
    	show.setPrefSize(150, 30);
    	show.setFont(Font.font(16));	
    	show.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
    	pane.add(show, 1, 5);
    	
		//User button to exit
		btCancel = new Button("Cancel");
    	btCancel.setTextFill(c4);
		btCancel.setPrefSize(100, 30);
		btCancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		btCancel.setFont(Font.font(16));
    	pane.add(btCancel, 2, 5);  
    	
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
	public Label getPowerl() {
		return powerl;
	}

	public void setPowerl(Label powerl) {
		this.powerl = powerl;
	}

	public GridPane getPane() {
		return pane;
	}

	public void setPane(GridPane pane) {
		this.pane = pane;
	}

	public Label getMaxl() {
		return maxl;
	}

	public void setMaxl(Label maxl) {
		this.maxl = maxl;
	}

	public Label getLcss() {
		return lcss;
	}

	public void setLcss(Label lcss) {
		this.lcss = lcss;
	}

	public Button getBtCancel() {
		return btCancel;
	}

	public void setBtCancel(Button btCancel) {
		this.btCancel = btCancel;
	}
	
	public Button getShow() {
		return show;
	}

	public void setShow(Button show) {
		this.show = show;
	}
}
