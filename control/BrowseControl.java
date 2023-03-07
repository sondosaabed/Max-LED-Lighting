package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

import Application.LongestCommonSubSequence;
import Presentation.BrowseScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BrowseControl {
	//feilds
	private FileChooser fileChooser;
	private File file;
	private BrowseScene BrowseScene;
	private GridPane pane;
	private Label process;
	private Button run;
	private Button cancel;
	private Label hello;
	private LongestCommonSubSequence sol;

	public BrowseControl(Stage window) {
		initialize(window);
	}

	private void initialize(Stage stage) {
		BrowseScene = new BrowseScene(stage);
		process = BrowseScene.getProcess();
		pane = BrowseScene.getPane();
		run = BrowseScene.getRun();
		setCancel(BrowseScene.getCancel());
		this.fileChooser = new FileChooser();
		hello=BrowseScene.getHello();
		
		handle_browse(stage,sol);
		handle_cancel(cancel, stage);
	}

	private void handle_run(Button run, Stage stage, LongestCommonSubSequence sol){
		run.setOnAction(e->{
			RunControl scene = new RunControl(stage,sol);
			scene.getPowerl().setText("Power Sources: "+sol.getPower());
		});
	}

	private void handle_browse(Stage stage, LongestCommonSubSequence sol2) {
		BrowseScene.getBrowse().setOnAction(d-> { 
			Stage stage3 = new Stage();
			fileChooser = new FileChooser();
			file=fileChooser.showOpenDialog(stage3); 
			
			if(file==(null)) {
				AlertCtrl alert = new AlertCtrl("You haven't chose a file yet!", "No file");
				alert.show();
			} else {
				if(readFile(file)==-1) {
					AlertCtrl alert = new AlertCtrl("Number of leds and power sources doesn't match", "Number mismatch");
					alert.show();
				}else {
					pane.add(run, 1, 6);
					handle_run(run, stage, sol);
					hello.setText("Reday to run");
				}
			}
		});	
	}

	private void handle_cancel(Button cancel, Stage stage){
		cancel.setOnAction(e->{
			stage.close();
		});
	}

	public int readFile(File input) {
		//This method reads the input file into 2 arrays, LEDs and power sources
		int x=0;
		try {
			 FileReader fileR = new FileReader(input);
		     BufferedReader buffer = new BufferedReader(fileR);
		     
		     //read the 1st line to get the number of power sources
		    int power= Integer.parseInt(buffer.readLine());
			sol= new LongestCommonSubSequence(new int[power],new int[power]);
		    
			String s[]= buffer.readLine().split(" ");
		     
		     if(s.length!=power) {
		    	 x= -1;
		     }else {
		    	//create an array of powers and read the array of LEDs from file
			     for(int i=0; i<power;i++) {
			    	sol.getPowers()[i] = i+1;
					sol.getLeds()[i]= Integer.parseInt(s[i]);
			     }
		     }
			buffer.close();
		}catch(NumberFormatException t) {
			AlertCtrl alert = new AlertCtrl(t.getMessage(), "Number Format Exception");
			alert.show();
		}catch (IOException e1) {
			AlertCtrl alert = new AlertCtrl(e1.getMessage(), "IO Exception");
			alert.show();
		} catch (InputMismatchException e) {
			AlertCtrl alert = new AlertCtrl(e.getMessage(), "Input Mismatch Exception");
			alert.show();
		}
		return x;
	}
	
	public Button getCancel() {
		return cancel;
	}

	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}
	
	public Button getRun() {
		return run;
	}

	public void setRun(Button run) {
		this.run = run;
	}
	
	public FileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(FileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public BrowseScene getBrowseScene() {
		return BrowseScene;
	}

	public void setBrowseScene(BrowseScene browseScene) {
		BrowseScene = browseScene;
	}

	public GridPane getPane() {
		return pane;
	}

	public void setPane(GridPane pane) {
		this.pane = pane;
	}

	public Label getProcess() {
		return process;
	}

	public void setProcess(Label process) {
		this.process = process;
	}

	public Label getHello() {
		return hello;
	}

	public void setHello(Label hello) {
		this.hello = hello;
	}

	public LongestCommonSubSequence getSol() {
		return sol;
	}

	public void setSol(LongestCommonSubSequence sol) {
		this.sol = sol;
	}

	
}
