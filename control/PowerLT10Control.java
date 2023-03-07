package control;

import Application.LongestCommonSubSequence;
import Presentation.PowerLT10Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/*
 * This class create the controller for when the poewr sourcess are less than 10 battries 
 * it allows the user to connect and dissconet the leds to the battries also show the DP table
 */
public class PowerLT10Control {
	//Feilds
	private Label maxl;
	private Button show;
	private Button connect;
	private Button disconnect;
	private Button cancel;
	private PowerLT10Scene scene;

	public PowerLT10Control(Stage stage, LongestCommonSubSequence sol) {
		initialize(stage,  sol);
	}

	private void initialize(Stage stage, LongestCommonSubSequence sol) {
		setScene(new PowerLT10Scene(stage,sol.getLeds(), sol.getPowers()));
		setMaxl(getScene().getMaxl());
		setShow(getScene().getShow());
		setConnect(getScene().getConnect());
		setCancel(getScene().getCancel());
		setDisconnect(getScene().getDisconnect());
		
		handle_cancel(getCancel(),stage);
		handle_conect(getConnect(), stage,sol);
		handle_disconnect(getDisconnect(), stage,sol.getPowers());
		handle_show(getShow(), stage, sol.getLeds(),sol.getPowers());
	}

	private void handle_show(Button show2, Stage stage, int[] leds, int[] powers) {
		show2.setOnAction(e->{
			new ShowTableControl(powers, powers);
		});
	}

	private void handle_disconnect(Button disconnect2, Stage stage, int[] powers) {
		disconnect2.setOnAction(e->{
			//Responsive width and height
			int q=100;
			if(powers.length<=5 && powers.length >0) {q=100;}
			else if(powers.length == 6 ) {q=90;}
			else if(powers.length == 7) {q=80;}
			else if(powers.length == 8) {q=70;} 
			else if(powers.length == 9) {q=60;} 
			else if(powers.length == 10) {q=50;}
			
			//create Image for battery power source
			Image off = new Image("Images/off.png");
			//create Image for battery LED
			Image ledRED = new Image("Images/LED red.png");
		
		
			for(Button bat: getScene().getBatts()) {
				ImageView batt=new ImageView(off);
				batt.setFitWidth(q);
				batt.setFitHeight(q);
				bat.setGraphic(batt);
			}
			for(Button led: getScene().getLedds()) {
				ImageView batte=new ImageView(ledRED);
				batte.setFitWidth(q);
				batte.setFitHeight(q);
				led.setGraphic(batte);
			}
			for(Button lab: getScene().getLabs()) {
				lab.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-radius: 0;-fx-background-color: transparent;");
			}
		});
	}

	private void handle_conect(Button connect2, Stage stage, LongestCommonSubSequence sol) {
		connect2.setOnAction(e->{
			for(int i=0;i<sol.getLcss().length;i++) {
				for(Button bat: getScene().getBatts()) {
					if(bat.getId().equals(sol.getLcs().charAt(i)+"")) {
						bat.fire();
					}
				}
				
				for(Button led: getScene().getLedds()) {
					if(led.getId().equals(sol.getLcs().charAt(i)+"")) {
						led.fire();
					}
				}
				
				for(Button lab: getScene().getLabs()) {
					if(lab.getId().equals(sol.getLcs().charAt(i)+"")) {
						lab.fire();
					}
				}
			}
		});
	}

	private void handle_cancel(Button cancel, Stage stage){
		cancel.setOnAction(e->{
			stage.close();
		});
	}
	/*
	 * Getters and Setters
	 */
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

	public PowerLT10Scene getScene() {
		return scene;
	}

	public void setScene(PowerLT10Scene scene) {
		this.scene = scene;
	}

	

}
