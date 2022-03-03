import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/** @author SS
 * In this project The user inputs a file of number of power sources and the sort of the LEDs, then the program calculates the maximum powered LEDs 
 * by finding the optimal solution using dynamic programming, the longest common sub-array, and displays the dynamic table.
 **/

public class App extends Application{
	public static FileChooser fileChooser;
	public static File file;//The input file
	private static int power;//Number of power sources
	static int[] powers; //Array of the batteries
	static int[] leds;//Array of the LEDs as read from file
	private static char[][] b;//Array of DP table for arrows
	private static int[][] c;//Array of DP table for lengths
	private static String lcs ="";//The maximum powered LEDs 
	private static String lcss[];//The exact LEDs powered
	
	@Override
	public void start(Stage stage) throws Exception {
		//Creating grid pane
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(120,150,130,150));
		pane.setHgap(10.5);
		pane.setVgap(10.5); 
		
		//A color that is repeatedly used in the project
		Color c4=Color.web("#353535");
		
		//Creating Background Image
		Image img = new Image("bg.jpg");
		BackgroundImage bImg = new BackgroundImage(img,
				BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
		Background bGround = new Background(bImg);
		pane.setBackground(bGround); 
		
		//Label Greeting User
		Label hello = new Label("Hello user, Select your LEDs and Power file...");
		hello.setFont(Font.font(16));  
		hello.setTextFill(c4);
		pane.add(hello, 0,5);
		
		//This label will be updated by process of file importing
		Label process = new Label("Process");
		process.setFont(Font.font(12));  
		process.setTextFill(Color.RED );
		pane.add(process , 0, 6);
		
		//User button to run the program
		Button run = new Button("Run");
		run.setFont(Font.font(14)); 
		run.setTextFill(c4);
		run.setPrefSize(100, 30);
		run.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		
		//User Button to browse file
		Button add = new Button("Browse"); 
		add.setFont(Font.font(14)); 
		add.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		add.setTextFill(c4);
		add.setPrefSize(100, 30);
		pane.add(add , 1,6);
		add.setOnAction(d-> { 
			Stage stage3 = new Stage();
    		fileChooser = new FileChooser();
    		file=fileChooser.showOpenDialog(stage3); 
			if(file==(null)) {
				process.setText("You haven't chose a file yet!");
				System.out.println("null pointer");
			} else {
				if(readFile(file)==-1) {
					process.setText("Number of leds and power sources doesn't match");
				}else {
					pane.add(run, 1, 6);
					hello.setText("Reday to run");
				}
			}
    	});
		
		//User button when clicked it runs the program
		run.setOnAction(e -> {
			Stage stage2 = new Stage();
			GridPane pane2 = new GridPane();
			pane2.setPadding( new Insets( 110,130, 110,130 )); 
		    pane2.setAlignment(Pos.CENTER); 
			pane2.setHgap(10);
			pane2.setVgap(10); 
			pane2.setBackground(bGround); 
			
			//The interface changes depends on the number of power
	    	if(power<=10) {
	    		//A label to show the number of power sources
				Label powerl= new Label(("You have " + power + " Power Sources"));
				powerl.setFont(Font.font(16));
		    	pane2.add(powerl,1,3);
		    	
		    	//Add a LED Image to the scene
		    	Image img1 = new Image("icon.png");
		    	ImageView v=new ImageView(img1);
		    	v.setFitWidth(60);
		    	v.setFitHeight(100);
		    	pane2.add(v,0,4);
		    	
		    	//User button to calculate the maximum number of powered LEDs
		    	Button show = new Button("show boards");
		    	show.setTextFill(c4);
		    	show.setPrefSize(150, 30);
		    	show.setFont(Font.font(16));	
		    	show.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		    	pane2.add(show, 1, 4);
		    	show.setOnAction(o-> {
	    			//New screen to show boards
		    		Stage stage3 = new Stage();
	    			GridPane pane3 = new GridPane();
	    			pane3.setPadding( new Insets( 5,30, 5,30 ));
	                pane3.setAlignment(Pos.CENTER); 
	        		pane3.setHgap(15.5);
	        		pane3.setVgap(15.5); 
	        		
	        		//Label to show the max number of powered LEDs
	        		Label maxl= new Label(("Maximum LEDs powered: " +longestCommonSubArrayLenghth(powers,leds)  ));
	    			maxl.setFont(Font.font(16));
	    	    	pane3.add(maxl,1,0);
	    	    	
	    	    	//User button to show table of dynamic solution
	        		Button table= new Button("table"); 
	        		table.setFont(Font.font(16)); 
	        		table.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
	        		table.setTextFill(c4);
	        		table.setPrefSize(100, 30);
	        		GridPane.setValignment(table, VPos.CENTER);
	        		pane3.add(table , 2,1);
				    table.setOnAction(w-> {
				    	 int length = powers.length;
				         int width =leds.length;
				         
				         GridPane root = new GridPane();  
				         root.setAlignment(Pos.CENTER); 
				         for(int y = 1; y < length+1; y++){
					       	  Label num = new Label();
					       	  num.setPrefHeight(50);
					       	  num.setPrefWidth(50);
					       	  num.setAlignment(Pos.CENTER);
					       	  num.setStyle("-fx-background-radius: 3, 3;-fx-background-color:lightyellow;-fx-border-color:lightgrey;");
					       	  num.setText( leds[y-1]+"");
					       	  num.setFont(Font.font(14));
					             
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
					       	  num.setText( powers[y-1]+"");
					       	  num.setFont(Font.font(14));
				             
				             //Iterate the Index using the loops
				             GridPane.setRowIndex(num,0);
				             GridPane.setColumnIndex(num,y);    
				             root.getChildren().add(num);
				         }
			         
				         for(int y = 1; y < length+1; y++){
				             for(int x = 1; x < width+1; x++){
				                 // Create a new num in each Iteration
				                 Label num = new Label();
				                 num.setPrefHeight(50);
				                 num.setPrefWidth(50);
				                 num.setAlignment(Pos.CENTER);
				                 num.setStyle("-fx-background-color:white;-fx-border-color:lightgrey;");
				                 num.setText(c[y-1][x-1]+""+b[y-1][x-1]);
				                 num.setFont(Font.font(14));
				                 //Iterate the Index using the loops
				                 GridPane.setRowIndex(num,y);
				                 GridPane.setColumnIndex(num,x);    
				                 root.getChildren().add(num);
				             }
				         }
				         Stage stage4 = new Stage();
				         Button btCancel = new Button("Ok");
				         btCancel.setFont(Font.font(14));
				         root.add(btCancel, 0, 0); 
				         btCancel.setTextFill(Color.WHITE);
				         btCancel.setStyle("-fx-background-radius: 7, 7;-fx-background-color:grey;");
				   	  	 btCancel.setOnAction(d-> {
				   	  		 stage4.close();
				   	  	 });
				         Scene scene = new Scene(root, 600, 600);    
				         stage4.setTitle("Dynamic Table");
				         stage4.getIcons().add(new Image("icon.png"));
				         stage4.setScene(scene);
				         stage4.show();
				    });
			    
				    //Creating array lists of batteries and LEDs and labels between them, to reach when clicking connect
				    List<Button> batts = new ArrayList<Button>();
				    List<Button> ledds = new ArrayList<Button>();
				    List<Button> labs = new ArrayList<Button>();
				    
	        		//create Image for battery power source
	        		Image off = new Image("off.png");
	        		Image on = new Image("on.png");
	    	    	
		    		//create Image for battery LED
		    		Image ledRED = new Image("LED red.png");
		    		Image ledYEL = new Image("LED yellow.png");
	    	    	
		    		//Responsive width and height
		    		int x=100;
		    		if(power<=5 && power >0) {x=100;}
		    		else if(power == 6 ) {x=90;}
		    		else if(power == 7) {x=80;}
		    		else if(power == 8) {x=70;} 
		    		else if(power == 9) {x=60;} 
		    		else if(power == 10) {x=50;}
		    		
	        		//User Button to connect LEDs
	        		Button connect = new Button("connect"); 
	        		connect.setFont(Font.font(16)); 
	        		connect.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
	        		connect.setTextFill(c4);
	        		connect.setPrefSize(100, 30);
	        		GridPane.setHalignment(connect, HPos.CENTER);
	        		pane3.add(connect , 1,1);
	        		connect.setOnAction(r->{
	        			print(b,leds,power-1,power-1);
	        			for(int i=0;i<lcs.length();i++) {
	        				for(Button bat: batts) {
	            				if(bat.getId().equals(lcs.charAt(i)+"")) {
	            					bat.fire();
	            				}
	            			}
	            			
	            			for(Button led: ledds) {
	            				if(led.getId().equals(lcs.charAt(i)+"")) {
	            					led.fire();
	            				}
	            			}
	            			
	            			for(Button lab: labs) {
	            				if(lab.getId().equals(lcs.charAt(i)+"")) {
	            					lab.fire();
	            				}
	            			}
	        			}
	        		});
	        		//user button to disconnect LEDs
	        		Button disconnect = new Button("disconnect"); 
	        		disconnect.setFont(Font.font(16)); 
	        		disconnect.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
	        		disconnect.setTextFill(c4);
	        		disconnect.setPrefSize(100, 30);
	        		pane3.add(disconnect , 0,1);
	        		disconnect.setOnAction(y->{
			    		//Responsive width and height
			    		int q=100;
			    		if(power<=5 && power >0) {q=100;}
			    		else if(power == 6 ) {q=90;}
			    		else if(power == 7) {q=80;}
			    		else if(power == 8) {q=70;} 
			    		else if(power == 9) {q=60;} 
			    		else if(power == 10) {q=50;}
        				for(Button bat: batts) {
        					ImageView batt=new ImageView(off);
        					batt.setFitWidth(q);
    		    	    	batt.setFitHeight(q);
    		    	    	bat.setGraphic(batt);
            			}
            			for(Button led: ledds) {
            				ImageView batte=new ImageView(ledRED);
            				batte.setFitWidth(q);
    		    	    	batte.setFitHeight(q);
    		    	    	led.setGraphic(batte);
            			}
            			for(Button lab: labs) {
    						lab.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
        				}
	        		});
        		
	        		//User Button to exit stage
	        		Button cancel = new Button("exit");
	        		cancel.setTextFill(Color.WHITE);
	        		GridPane.setHalignment(cancel, HPos.RIGHT);
	    	    	cancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:#F08080;");
	    	    	cancel.setFont(Font.font(10));
	        		pane3.add(cancel, 2,0);
	        		cancel.setOnAction(y-> {
	        			stage3.close();
						stage2.close();
						stage.close();
	        		});

		    		int y=2;//initial point on grid
		    		for(int i=0;i<power;i++) {
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
				    		if(power<=5 && power >0) {q=100;}
				    		else if(power == 6 ) {q=90;}
				    		else if(power == 7) {q=80;}
				    		else if(power == 8) {q=70;} 
				    		else if(power == 9) {q=60;} 
				    		else if(power == 10) {q=50;}
							ImageView batte=new ImageView(on);
			    	    	batte.setFitWidth(q);
			    	    	batte.setFitHeight(q);
							bat.setGraphic(batte);
						});  
						
			    		//Add battery and LED to the pane and the lists
			    		batts.add(i, bat);
			    		pane3.add(bat, 2, y);
			    		
						y++;
		    		}
		    		
		    		y=2;//initial point on grid
		    		for(int i=0;i<power;i++) {
		    			if(x==0)
		    				break;
			    		//Create labels of led number
		        		Button bat = new Button(App.leds[i]+"");
		        		bat.setPrefSize(x,x); 
		        		GridPane.setHalignment(bat, HPos.CENTER);
		        		bat.setTextFill(c4);
		        		bat.setFont(Font.font(18)); 
						bat.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
						bat.setId(App.leds[i]+"");
						labs.add(i,bat);
						bat.setOnAction(a->{
							bat.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 7;-fx-background-color:lightgrey;");
						}); 
			    		//Add battery and LED to the pane and the lists
			    		labs.add(i, bat);
			    		pane3.add(bat, 1, y);
						y++;
		    		}
	    		
		    		y=2;
		    		for(int i=0;i<App.leds.length;i++) {
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
			    		led.setId(App.leds[i]+"");
			    		led.setOnAction(a->{
				    		//Responsive width and height
				    		int q=100;
				    		if(power<=5 && power >0) {q=100;}
				    		else if(power == 6 ) {q=90;}
				    		else if(power == 7) {q=80;}
				    		else if(power == 8) {q=70;} 
				    		else if(power == 9) {q=60;} 
				    		else if(power == 10) {q=50;}
							ImageView batte=new ImageView(ledYEL);
			    	    	batte.setFitWidth(q);
			    	    	batte.setFitHeight(q);
							led.setGraphic(batte);
			    		});	
			    		ledds.add(i, led);
			    		pane3.add(led,0, y);
			    		y++;
		    		}
	    	    	//Create scene
		    		Scene scene3 = new Scene(pane3); 
	        		stage3.setTitle("Maximum Number of powered LEDs");
	        		stage3.getIcons().add(new Image("icon.png"));
	        		stage3.setScene(scene3);  
	        		stage3.show();
		    	});
				//User button to exit
	    		Button btCancel = new Button("Cancel");
		    	btCancel.setTextFill(c4);
				btCancel.setPrefSize(100, 30);
				btCancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
				btCancel.setFont(Font.font(16));
		    	pane2.add(btCancel, 2, 4);  
		    	btCancel.setOnAction(d-> stage2.close());
	    	}else if(power>10 && power <=20) {
	    		//A label to show the number of power sources
				Label powerl= new Label(("You have " + power + " Power Sources"));
				powerl.setFont(Font.font(16));
		    	pane2.add(powerl,1,3);
		    	
		    	//Add a LED Image to the scene
		    	Image img1 = new Image("icon.png");
		    	ImageView v=new ImageView(img1);
		    	v.setFitWidth(60);
		    	v.setFitHeight(100);
		    	pane2.add(v,0,4);
		    	
	    		//Label to show the max number of powered LEDs
        		Label maxl= new Label(("Maximum LEDs powered: " +longestCommonSubArrayLenghth(powers,leds)  ));
    			maxl.setFont(Font.font(16));
    	    	pane2.add(maxl,1,4);
    	    	print(b,leds,power-1,power-1);
    	    	String text="";
    	    	lcss= lcs.split(" ");
    	    	for(int i=0;i<lcss.length;i++) {
    	    		text=text+" "+ lcss[i]+ ", ";
    	    	}
    	    	Label lcss= new Label(text);
    	    	lcss.setFont(Font.font(14));
    	    	lcss.setTextFill(Color.GREEN);
    	    	pane2.add(lcss,2,4);
	    		Button show = new Button("show table");
		    	show.setTextFill(c4);
		    	show.setPrefSize(150, 30);
		    	show.setFont(Font.font(16));	
		    	show.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
		    	pane2.add(show, 1, 5);
		    	show.setOnAction(w-> {
		    		print(b,leds,power-1,power-1);
		    		int length = powers.length;
		    		int width =leds.length;
			         
			        GridPane root = new GridPane();  
			        root.setAlignment(Pos.CENTER); 
			         
			         for(int y = 1; y < length+1; y++){
				       	  Label num = new Label();
				       	  num.setPrefHeight(50);
				       	  num.setPrefWidth(50);
				       	  num.setAlignment(Pos.CENTER);
				       	  num.setStyle("-fx-background-radius: 3, 3;-fx-background-color:lightyellow;-fx-border-color:lightgrey;");
				       	  num.setText( leds[y-1]+"");
				       	  num.setFont(Font.font(14));
				             
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
				       	  num.setText( powers[y-1]+"");
				       	  num.setFont(Font.font(14));
				             
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
			                 num.setText(c[y-1][x-1]+""+b[y-1][x-1]);
			                 num.setFont(Font.font(14));
			                 //Iterate the Index using the loops
			                 GridPane.setRowIndex(num,y);
			                 GridPane.setColumnIndex(num,x);    
			                 root.getChildren().add(num);
			             }
			         }
			         Stage stage4 = new Stage();
			         Button btCancel = new Button("Ok");
			         btCancel.setFont(Font.font(14));
			         root.add(btCancel, 0, 0); 
			         btCancel.setTextFill(Color.WHITE);
			         btCancel.setStyle("-fx-background-radius: 7, 7;-fx-background-color:grey;");
			   	  	 btCancel.setOnAction(d-> {
			   	  		 stage4.close();
			   	  	 });
		         Scene scene = new Scene(root, 800, 800);    
		         stage4.setTitle("Dynamic Table");
		         stage4.getIcons().add(new Image("icon.png"));
		         stage4.setScene(scene);
		         stage4.show();
			    });
				//User button to exit
	    		Button btCancel = new Button("Cancel");
		    	btCancel.setTextFill(c4);
				btCancel.setPrefSize(100, 30);
				btCancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:white;");
				btCancel.setFont(Font.font(16));
		    	pane2.add(btCancel, 2, 5);  
		    	btCancel.setOnAction(d-> stage2.close());
	    	}else {
	    		//A label to show the number of power sources
				Label powerl= new Label(("You have " + power + " Power Sources"));
				powerl.setFont(Font.font(16));
		    	pane2.add(powerl,1,0);
		    	
		    	//Add a LED Image to the scene
		    	Image img1 = new Image("icon.png");
		    	ImageView v=new ImageView(img1);
		    	v.setFitWidth(60);
		    	v.setFitHeight(100);
		    	pane2.add(v,0,1);
		    	
	    		//Label to show the max number of powered LEDs
        		Label maxl= new Label(("Maximum LEDs powered: " +longestCommonSubArrayLenghth(powers,leds)  ));
    			maxl.setFont(Font.font(16));
    	    	pane2.add(maxl,1,1);
    	    	pane2.setBackground(null);
				
    	    	//Label that shows the exact powered LEDs
    	    	print(b,leds,power-1,power-1);
		    	String text="";
    	    	lcss= lcs.split(" ");
    	    	for(int i=0;i<lcss.length;i++) {
    	    		text=text+" "+ lcss[i]+ ", ";
    	    	}
    	    	Label lcss= new Label(text);
    	    	lcss.setFont(Font.font(14));
    	    	lcss.setTextFill(Color.GREEN);
    	    	pane2.add(lcss,1,2);
    	    	
    	    	//User button to exit
	    		Button btCancel = new Button("Cancel");
		    	btCancel.setTextFill(c4);
				btCancel.setPrefSize(100, 30);
				btCancel.setStyle("-fx-background-radius: 18, 7;-fx-background-color:lightyellow;");
				btCancel.setFont(Font.font(16));
		    	pane2.add(btCancel, 2, 1);  
		    	btCancel.setOnAction(d-> stage2.close());
	    	}
	    	//Scene setting
	    	Scene scene = new Scene(pane2); 		
	    	stage2.setTitle("Power source supplied");
			stage2.getIcons().add(new Image("icon.png"));
			stage2.setScene(scene);  
	    	stage2.show(); 
		}); 
		
		//User button to exit
		Button cancel = new Button("Cancel");
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
		stage.getIcons().add(new Image("icon.png"));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static int longestCommonSubArrayLenghth(int[]y,int[]x) {
		//This method returns the length of the longest common sub array
		int n = y.length;
		int m = x.length;
	
		c = new int[n][m];
		b = new char[n+1][m+1];
		
		for(int i=0;i<n;i++)
			c[0][i]=0;
		
		for(int i=0;i<m;i++)
			c[i][0]=0;
		
		for(int i=1; i<m; i++)
			for(int j=1;j<n;j++)
				if(x[i-1]==y[j-1]) {
					c[i][j]=c[i-1][j-1]+1;
					b[i][j]='\\';
				}else if(c[i][j-1]>c[i-1][j]) {
					c[i][j]=c[i][j-1];
					b[i][j]='<';
				}else {
					c[i][j]=c[i-1][j];
					b[i][j]='^';
				}
		return c[n-1][m-1];
	}
	
	public static int readFile(File input) {
		//This method reads the input file into 2 arrays, LEDs and power sources
		int x=0;
		try {
			 FileReader fileR = new FileReader(input);
		     BufferedReader buffer = new BufferedReader(fileR);
		     
		     //read the 1st line to get the number of power sources
		     power= Integer.parseInt(buffer.readLine());
		     
		     powers = new int[power];
		     leds= new int[power];
		     String s[]= buffer.readLine().split(" ");
		     
		     if(s.length!=power) {
		    	 System.out.println("No match of power source and leds");
		    	 x= -1;
		     }else {
		    	//create an array of powers and read the array of LEDs from file
			     for(int i=0; i<power;i++) {
			    	 powers[i]= i+1;
			    	 leds[i]= Integer.parseInt(s[i]);
			    	 System.out.println(powers[i] +" " + leds[i]);
			     }
		     }
			buffer.close();
		}catch(NumberFormatException t) {
			System.out.println(t);
		}catch (FileNotFoundException e1) {
			System.out.println(e1);
		}catch (IOException e1) {
			System.out.println(e1);
		} catch (InputMismatchException e) {
			System.out.println(e);
		}
		return x;
	}

	public static void print(char [][]b, int [] leds, int i, int j) {
		//In this method the longest common sub- array is created
		if(i!=0 && j!=0) {
			if(b[i][j]=='\\') {
				print(b,leds,i-1,j-1);
				lcs=lcs+leds[i-1]+" ";
				System.out.println(leds[i-1]);
			}else if(b[i][j]=='<') {
				print(b,leds,i,j-1);
			}else if(b[i][j]=='^'){
				print(b,leds,i-1,j);
			}
		}
	}
}
