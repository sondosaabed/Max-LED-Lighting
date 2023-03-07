package control;

import Presentation.AlertScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/*
 * This class is a controller for an alert that is shown to the user for a specific warning
 */
public class AlertCtrl{
    // Feilds
    private AlertScene scene;
    private Label message;
	private Button cancel;
    
    public AlertCtrl(String warning, String title){
        initialize(warning, title);
	}
    private void initialize(String warning, String title){
        setScene(new AlertScene());
        setMessage(getScene().getMessage());
		setCancel(getScene().getCancel());
		
		getScene().getStage().setTitle(title);
		getMessage().setText(warning);
		
		handle_cancel(cancel);
    }

	private void handle_cancel(Button cancel2) {
		//Method that handles cancel
		cancel2.setOnAction(e->{
			getScene().getStage().close();
		});
	}
    /*
     * Getters and Setters
     */
    public void show() {
		getScene().getStage().show();
	}

    public AlertScene getScene() {
        return scene;
    }

    public void setScene(AlertScene scene) {
        this.scene = scene;
    }

    public Label getMessage() {
        return message;
    }

    public void setMessage(Label message) {
        this.message = message;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }
}