package control;

import Application.LongestCommonSubSequence;
import Presentation.PowerMT10LT20Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/*
 * This class create the controller for when the poewr sourcess are more than 10 battries but also less than 20
 * it allows the user to show the DP table and the solution but not the leds and simulation
 */
public class PowerMT10LT20Control {
	//feilds
	private PowerMT10LT20Scene PowerMT10LT20Scene;
	private Label powerl;
	private GridPane pane;
	private Label maxl;
	private Label lcss;
	private Button btCancel;
	private Button show;
	
	public PowerMT10LT20Control(Stage stage, LongestCommonSubSequence sol) {
		initialize(stage, sol);
	}

	private void initialize(Stage stage, LongestCommonSubSequence sol) {
		setPowerMT10LT20Scene(new PowerMT10LT20Scene(stage));
		setBtCancel(PowerMT10LT20Scene.getBtCancel());
		setLcss(PowerMT10LT20Scene.getLcss());
		setMaxl(PowerMT10LT20Scene.getMaxl());
		setPane(PowerMT10LT20Scene.getPane());
		setPowerl(PowerMT10LT20Scene.getPowerl());
		setShow(PowerMT10LT20Scene.getShow());

		handle_cancel(btCancel, stage);
		handle_show(getShow(), sol);

		getMaxl().setText("Maximum LEDs powered: " +sol.getC()[sol.getLeds().length-1][sol.getPowers().length-1]);
		getPowerl().setText("You have " + sol.getPower() + " Power Sources");	
    	
    	String text="";
    	for(int i=0;i<sol.getLcss().length;i++) {
    		text=text+" "+ sol.getLcss()[i]+ ", ";
    	}
    	getLcss().setText(text);
	}

	private void handle_show(Button table,  LongestCommonSubSequence sol){
		table.setOnAction(e->{
			new ShowTableControl(sol.getLeds(),sol.getPowers());
		});
	}
	private void handle_cancel(Button cancButton, Stage stage){
		cancButton.setOnAction(e->{
			stage.close();
		});
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

	public PowerMT10LT20Scene getPowerMT10LT20Scene() {
		return PowerMT10LT20Scene;
	}

	public void setPowerMT10LT20Scene(PowerMT10LT20Scene powerMT10LT20Scene) {
		PowerMT10LT20Scene = powerMT10LT20Scene;
	}

	public GridPane getPane() {
		return pane;
	}

	public void setPane(GridPane pane) {
		this.pane = pane;
	}
}