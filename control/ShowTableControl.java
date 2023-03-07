package control;

import Presentation.ShowTableScene;
import javafx.scene.control.Button;
/*
 * This class creates the controller of the Dynamic table scene to be shown to the user
 */
public class ShowTableControl {
	//feilds
	private Button cancel;
	private ShowTableScene scene;

	public ShowTableControl( int [] leds, int[] powers ) {
		initialize( leds, powers);
	}

	private void initialize(int[] leds, int[] powers) {
		setScene(new ShowTableScene( leds, powers));
		setCancel(getScene().getBtCancel());
		handle_cancel(getCancel());
	}

	private void handle_cancel(Button cancel2) {
		cancel2.setOnAction(e->{
			getScene().getStage().close();
		});
	}
	/*
	 * Getters and Setters
	 */
	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}

	public ShowTableScene getScene() {
		return scene;
	}

	public void setScene(ShowTableScene scene) {
		this.scene = scene;
	}
}