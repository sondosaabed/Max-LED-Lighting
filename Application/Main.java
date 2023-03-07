package Application;

import control.MainControl;
import javafx.application.Application;
import javafx.stage.Stage;

/** @author SS
 * In this project The user inputs a file of number of power sources and the sort of the LEDs, then the program calculates the maximum powered LEDs 
 * by finding the optimal solution using dynamic programming, the longest common sub-array, and displays the dynamic table.
 **/

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
		
	@Override
	public void start(Stage stage) {
		MainControl mainCtrl = new MainControl(stage);
		mainCtrl.showBrowseScene();
	}
}