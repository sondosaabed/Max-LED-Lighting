package control;

import Application.LongestCommonSubSequence;
import Presentation.PowerMT20Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/*
 * This class create the controller for when the poewr sourcess are more than 20 battries 
 * it only shows the solution the max number of leds that gives the optimal solution
 */
public class PowerMT20Control {
	//feilds
	private PowerMT20Scene PowerMT20Scene;
	private Label maxl;
	private GridPane pane;
	private Button btCancel;
	private Label lcss;
	
	public PowerMT20Control(Stage stage, LongestCommonSubSequence sol) {
		initialize(stage,sol);
	}
	
	private void initialize(Stage stage, LongestCommonSubSequence sol) {
		PowerMT20Scene=new PowerMT20Scene(stage);
		setMaxl(PowerMT20Scene.getMaxl());
		setPane(PowerMT20Scene.getPane());
		setBtCancel(PowerMT20Scene.getBtCancel());
		setLcss(PowerMT20Scene.getLcss());

		getMaxl().setText("Maximum LEDs powered: " +sol.getC()[sol.getLeds().length-1][sol.getPowers().length-1]);
    	String text="";

    	for(int i=0;i<sol.getLcss().length;i++) {
    		text=text+" "+ sol.getLcss()[i]+ ", ";
    	}
    	getLcss().setText(text);  
	}
	/*
	 * Getters and Setters
	 */
	public PowerMT20Scene getPowerMT20Scene() {
		return PowerMT20Scene;
	}

	public void setPowerMT20Scene(PowerMT20Scene powerMT20Scene) {
		PowerMT20Scene = powerMT20Scene;
	}

	public Label getMaxl() {
		return maxl;
	}

	public void setMaxl(Label maxl) {
		this.maxl = maxl;
	}

	public GridPane getPane() {
		return pane;
	}

	public void setPane(GridPane pane) {
		this.pane = pane;
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
}