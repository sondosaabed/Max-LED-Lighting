package control;

import Application.LongestCommonSubSequence;
import Presentation.RunScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/*
 * This class create the controller for the scene that shows how many leds the user have and let them show the boards of leds and bataries
 */
public class RunControl {
	//feilds
	private RunScene RunScene;
	private GridPane pane;
	private Button showBoards;
	private Label powerl;
	private Button btCancel;
	
	public RunControl(Stage stage, LongestCommonSubSequence sol) {
		initialize(stage,sol);
	}
	
	private void initialize(Stage stage, LongestCommonSubSequence sol) {
		this.RunScene = new RunScene(stage);
		this.setPane(RunScene.getPane());
		setPowerl(RunScene.getPowerl());
		
		setBtCancel(RunScene.getCancel());
		setShowBoards(RunScene.getShowBoards());

		handle_cancel(btCancel, stage);
		handle_boards(showBoards, stage,sol);
   	}

	private void handle_boards(Button show, Stage stage, LongestCommonSubSequence sol){
		show.setOnAction(e->{
			if(sol.getPower()<=10) {
				new PowerLT10Control(stage,sol);
			}else if(sol.getPower()>10 && sol.getPower() <=20) {
				new PowerMT10LT20Control(stage,sol);
			}else {
				new PowerMT20Control(stage,sol);
			}
		});
	}

	private void handle_cancel(Button cancel, Stage stage) {
		cancel.setOnAction(e->{
			stage.close();
		});
	}
	/*
	 * Getters and Setters
	 */
	private void setPane(GridPane pane2) {
		this.pane=pane2;
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

	public Label getPowerl() {
		return powerl;
	}

	public void setPowerl(Label powerl) {
		this.powerl = powerl;
	}

	public Button getBtCancel() {
		return btCancel;
	}

	public void setBtCancel(Button btCancel) {
		this.btCancel = btCancel;
	}
}