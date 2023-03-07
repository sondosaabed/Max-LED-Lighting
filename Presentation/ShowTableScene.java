package Presentation;

import Application.LongestCommonSubSequence;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/*
 * This class shows a scene of the dynamic programming table for the user 
 */
public class ShowTableScene {
	//feilds
	private GridPane root;
	private Button btCancel;
	private Stage stage;

	public ShowTableScene( int[] leds, int []powers) {
		initialize(powers, powers);
	}
	
	private void initialize(int[] leds, int []powers) {
		LongestCommonSubSequence sol = new LongestCommonSubSequence(leds, powers);

		int length = powers.length;
		int width =leds.length;
	     
	    root = new GridPane();  
	    root.setAlignment(Pos.CENTER); 
	     
	     for(int y = 1; y < length+1; y++){
	       	  Label num = new Label();
	       	  num.setPrefHeight(50);
	       	  num.setPrefWidth(50);
	       	  num.setAlignment(Pos.CENTER);
	       	  num.setStyle("-fx-background-radius: 3, 3;-fx-background-color:lightyellow;-fx-border-color:lightgrey;");
	       	  num.setFont(Font.font(14));
	       	  num.setText( leds[y-1]+"");

	       	  //Iterate the Index using the loops
	         GridPane.setRowIndex(num,y);
	         GridPane.setColumnIndex(num,0);    
	         root.getChildren().add(num);
	     }
	     
	     for(int y = 1; y < width+1; y++){ 
	    	 Label num = new Label();
	       	  num.setPrefHeight(50);
	       	  num.setPrefWidth(50);
	       	  num.setAlignment(Pos.CENTER);
	       	  num.setStyle("-fx-background-color:lightyellow;-fx-border-color:lightgrey;");
	       	  num.setFont(Font.font(14));
	       	  
	       	  num.setText( powers[y-1]+"");
   
	         //Iterate the Index using the loops
	         GridPane.setRowIndex(num,0);
	         GridPane.setColumnIndex(num,y);    
	         root.getChildren().add(num);
	     }
	     
	     for(int y = 1; y < length+1; y++){
	         for(int x = 1; x < width+1; x++){
	             // Create a new Label in each Iteration
	             Label num = new Label();
	             num.setPrefHeight(50);
	             num.setPrefWidth(50);
	             num.setAlignment(Pos.CENTER);
	             num.setStyle("-fx-background-color:white;-fx-border-color:lightgrey;");
	             num.setFont(Font.font(14));
				
	             num.setText(sol.getC()[y-1][x-1]+""+sol.getB()[y-1][x-1]);

	             //Iterate the Index using the loops
	             GridPane.setRowIndex(num,y);
	             GridPane.setColumnIndex(num,x);    
	             root.getChildren().add(num);
	         }
	     }
		 btCancel = new Button("Ok");
		 btCancel.setFont(Font.font(14));
		 root.add(btCancel, 0, 0); 
		 btCancel.setTextFill(Color.WHITE);
		 btCancel.setStyle("-fx-background-radius: 7, 7;-fx-background-color:grey;");
		 
		 Scene scene = new Scene(root, 800, 800);    
		 stage = new Stage();
		 stage.setTitle("Dynamic Table");
		 stage.getIcons().add(new Image("Images/icon.png"));
		 stage.setScene(scene);
		 stage.show();
	}
	/*
	 * Getters and Setters
	 */
	public GridPane getRoot() {
		return root;
	}

	public void setRoot(GridPane root) {
		this.root = root;
	}

	public Button getBtCancel() {
		return btCancel;
	}

	public void setBtCancel(Button btCancel) {
		this.btCancel = btCancel;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
