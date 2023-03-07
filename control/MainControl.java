package control;

import javafx.stage.Stage;
/*
 * This class creates the main controller that runs the app
 * shows the first scene
 */
public class MainControl {
	//feilds
	private Stage stage;
	private BrowseControl BrowseControl;
	
	public MainControl(Stage stage) {
		this.stage= stage;
	}
	
	public void showBrowseScene() {
		setBrowseControl(new BrowseControl(stage));
	}
	/*
	 * Getters and Setters
	 */
	public void setBrowseControl(BrowseControl browseControl) {
		this.BrowseControl=browseControl;
	}
	
	public BrowseControl getBrowseControl() {
		return BrowseControl;
	}
}