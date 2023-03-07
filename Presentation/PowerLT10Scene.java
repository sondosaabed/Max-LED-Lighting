package Presentation;

import java.util.ArrayList;
import Application.LongestCommonSubSequence;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
 * This class shows a scene when the powers sources are less than 10 sources of power to the user 
 * it shows the battaroes and the leds in two columns so that in the controller they will be connected with optimal solution 
 */
public class PowerLT10Scene {
	//feilds
	private GridPane pane;
	private Label maxl;
	private Button show;
	private Button connect;
	private Button disconnect;
	private Button cancel;
	private ArrayList<Button> batts;
	private ArrayList<Button> ledds;
	private ArrayList<Button> labs;

	//(power<=10) 
	public PowerLT10Scene(Stage stage, int[] leds, int []powers) {
		initialize(stage, powers, powers);
	}
	
	private void initialize(Stage stage, int[] leds, int []powers) {
		//Creating array lists of batteries and LEDs and labels between them, to reach when clicking connect
		batts = new ArrayList<Button>();
		ledds = new ArrayList<Button>();
		labs = new ArrayList<Button>();
	
		//create Image for battery power source
		Image off = new Image("Images/off.png");
		Image on = new Image("Images/on.png");
    	
		//create Image for battery LED
		Image ledRED = new Image("Images/LED red.png");
		Image ledYEL = new Image("Images/LED yellow.png");		
		Color c4=Color.web("#353535");
	
		//New screen to show boards
		pane = new GridPane();
		pane.setPadding( new Insets( 5,30, 5,30 ));
        pane.setAlignment(Pos.CENTER); 
		pane.setHgap(15.5);
		pane.setVgap(15.5); 
    	
		LongestCommonSubSequence sol = new LongestCommonSubSequence(powers,leds);
		//Label to show the max number of powered LEDs
		maxl= new Label(("Maximum LEDs powered: " + sol.getC()[powers.length-1][leds.length-1]));
		maxl.setFont(Font.font(16));
    	pane.add(maxl,1,0);
    	
    	//User button to show table of dynamic solution
		show= new Button("table"); 
		show.setFont(Font.font(16)); 
		show.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		show.setTextFill(c4);
		show.setPrefSize(100, 30);
		GridPane.setValignment(show, VPos.CENTER);
		pane.add(show , 2,1);

		//User Button to connect LEDs
		connect = new Button("connect"); 
		connect.setFont(Font.font(16)); 
		connect.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		connect.setTextFill(c4);
		connect.setPrefSize(100, 30);
		GridPane.setHalignment(connect, HPos.CENTER);
		pane.add(connect , 1,1);
		
		//user button to disconnect LEDs
		disconnect = new Button("disconnect"); 
		disconnect.setFont(Font.font(16)); 
		disconnect.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		disconnect.setTextFill(c4);
		disconnect.setPrefSize(100, 30);
		pane.add(disconnect , 0,1);
		
		//User Button to exit stage
		cancel = new Button("exit");
		cancel.setTextFill(Color.WHITE);
		GridPane.setHalignment(cancel, HPos.RIGHT);
    	cancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:#F08080;");
    	cancel.setFont(Font.font(10));
		pane.add(cancel, 2,0);
		
		//Responsive width and height
		int x=100;
		if(powers.length<=5 && powers.length >0) {x=100;}
		else if(powers.length == 6 ) {x=90;}
		else if(powers.length == 7) {x=80;}
		else if(powers.length == 8) {x=70;} 
		else if(powers.length == 9) {x=60;} 
		else if(powers.length == 10) {x=50;}
				
		int y=2;//initial point on grid
		for(int i=0;i<powers.length;i++) {
			if(x==0)
				break;
			//Create image view for battery and led
			ImageView batt=new ImageView(off);
	    	batt.setFitWidth(x);
	    	batt.setFitHeight(x);
	    	
    		//Create battery 
    		Button bat = new Button();
    		GridPane.setHalignment(bat, HPos.CENTER);
    		bat.setPrefSize(x,x); 
    		bat.setGraphic(batt);
			bat.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
			bat.setId(i+1+"");
			bat.setOnAction(a->{ 
	    		//Responsive width and height
	    		int q=100;
	    		if(powers.length<=5 && powers.length >0) {q=100;}
	    		else if(powers.length == 6 ) {q=90;}
	    		else if(powers.length == 7) {q=80;}
	    		else if(powers.length == 8) {q=70;} 
	    		else if(powers.length == 9) {q=60;} 
	    		else if(powers.length == 10) {q=50;}
				ImageView batte=new ImageView(on);
    	    	batte.setFitWidth(q);
    	    	batte.setFitHeight(q);
				bat.setGraphic(batte);
			});  
			
    		//Add battery and LED to the pane and the lists
    		batts.add(i, bat);
    		pane.add(bat, 2, y);
    		
			y++;
		}
		
		y=2;//initial point on grid
		for(int i=0;i<powers.length;i++) {
			if(x==0)
				break;
    		//Create labels of led number
    		Button bat = new Button(leds[i]+"");
    		bat.setPrefSize(x,x); 
    		GridPane.setHalignment(bat, HPos.CENTER);
    		bat.setTextFill(c4);
    		bat.setFont(Font.font(18)); 
			bat.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
			bat.setId(leds[i]+"");
			labs.add(i,bat);
			bat.setOnAction(a->{
				bat.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 7;-fx-background-color:lightgrey;");
			}); 
    		//Add battery and LED to the pane and the lists
    		labs.add(i, bat);
    		pane.add(bat, 1, y);
			y++;
		}
	
		y=2;
		for(int i=0;i<leds.length;i++) {
			if(x==0)
				break;
	    	ImageView ledd=new ImageView(ledRED);
	    	ledd.setFitWidth(x);
	    	ledd.setFitHeight(x);
    		//Create LED
    		Button led = new Button();
    		GridPane.setHalignment(led, HPos.CENTER);
    		led.setPrefSize(x, x); 
    		led.setGraphic(ledd);
    		led.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
    		led.setId(leds[i]+"");
    		led.setOnAction(a->{
	    		//Responsive width and height
	    		int q=100;
	    		if(powers.length<=5 && powers.length >0) {q=100;}
	    		else if(powers.length == 6 ) {q=90;}
	    		else if(powers.length == 7) {q=80;}
	    		else if(powers.length == 8) {q=70;} 
	    		else if(powers.length == 9) {q=60;} 
	    		else if(powers.length == 10) {q=50;}
				ImageView batte=new ImageView(ledYEL);
    	    	batte.setFitWidth(q);
    	    	batte.setFitHeight(q);
				led.setGraphic(batte);
    		});	
    		ledds.add(i, led);
    		pane.add(led,0, y);
    		y++;
		}

		//Create scene
		Scene scene3 = new Scene(pane); 
		stage.setTitle("Maximum Number of powered LEDs");
		stage.getIcons().add(new Image("Images/icon.png"));
		stage.setScene(scene3);  
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

	public Label getMaxl() {
		return maxl;
	}

	public void setMaxl(Label maxl) {
		this.maxl = maxl;
	}

	public Button getShow() {
		return show;
	}

	public void setShow(Button show) {
		this.show = show;
	}

	public Button getConnect() {
		return connect;
	}

	public void setConnect(Button connect) {
		this.connect = connect;
	}

	public Button getDisconnect() {
		return disconnect;
	}

	public void setDisconnect(Button disconnect) {
		this.disconnect = disconnect;
	}

	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}

	public ArrayList<Button> getBatts() {
		return batts;
	}

	public void setBatts(ArrayList<Button> batts) {
		this.batts = batts;
	}

	public ArrayList<Button> getLedds() {
		return ledds;
	}

	public void setLedds(ArrayList<Button> ledds) {
		this.ledds = ledds;
	}

	public ArrayList<Button> getLabs() {
		return labs;
	}

	public void setLabs(ArrayList<Button> labs) {
		this.labs = labs;
	}
}