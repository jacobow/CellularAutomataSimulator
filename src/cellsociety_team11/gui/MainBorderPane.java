package cellsociety_team11.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import cellsociety_team11.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Instantiates all the User Interface controls and points event handlers at the MainController
 */
public class MainBorderPane extends BorderPane{
	private ToolBar toolBar;
	private ResourceBundle resourceBundle;
	private MainController mainController;
	private static final double SPEED_SLIDER_MIN = 0.25;
	private static final double SPEED_SLIDER_MAX = 1.25;
	public static final double SPEED_SLIDER_START = 0.5;
	private static final double SPEED_SLIDER_TICK_NUMBER = 4.0;
	private static final double SPEED_SLIDER_MAJOR_TICK_UNIT = (SPEED_SLIDER_MAX-SPEED_SLIDER_MIN)/(SPEED_SLIDER_TICK_NUMBER-1.0);
	
	public MainBorderPane(MainController simulationController, ResourceBundle resourceBundle){
		this.resourceBundle = resourceBundle;
		this.mainController = simulationController;
		initMainBorderPane();
	}
	
	private void initMainBorderPane(){
		toolBar = createToolBar();	
		this.setTop(toolBar);
	}
	
	private ToolBar createToolBar(){
		ToolBar toolBar = new ToolBar();
		toolBar.getItems().add(createButton("StartButton", event -> mainController.startSimulation()));
		toolBar.getItems().add(createButton("StepButton", event -> mainController.nextStepSimulation()));
		toolBar.getItems().add(createButton("StopButton", event -> mainController.stopSimulation()));
		toolBar.getItems().add(createButton("UploadXMLButton", event -> this.updateXMLHandler()));
		toolBar.getItems().add(createSliderAndLabelHBox("SpeedSliderLabel", event -> mainController.updateSimulationSpeed(event))); 
		return toolBar;
	}
	
	private void updateXMLHandler(){
		ArrayList<String> extensionList = new ArrayList<>();
		extensionList.add(MainController.XML_SUFFIX);
		File xmlFile = (new XMLFileChooser("XMLFileChooserTitle", extensionList, this.resourceBundle)).getXMLFileChooser().showOpenDialog(this.getScene().getWindow());
		if (xmlFile != null){
			mainController.uploadedXMLFileHandler(xmlFile);
		}
	}
	
	
	private HBox createSliderAndLabelHBox(String labelProperty, EventHandler<? super MouseEvent> handler){
		HBox sliderAndLabel = new HBox();
		sliderAndLabel.getChildren().add(createLabel(labelProperty));
		sliderAndLabel.getChildren().add(createSlider(handler));
		return sliderAndLabel;
	}
	
	private Slider createSlider(EventHandler<? super MouseEvent> handler){
		Slider slider = new Slider(SPEED_SLIDER_MIN, SPEED_SLIDER_MAX, SPEED_SLIDER_START);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(SPEED_SLIDER_MAJOR_TICK_UNIT);
		slider.setMinorTickCount((int) SPEED_SLIDER_TICK_NUMBER);
		slider.setOnMouseDragged(handler);
		return slider;
	}
	
	private Label createLabel(String labelProperty){
		Label sliderLabel = new Label(resourceBundle.getString(labelProperty));
		return sliderLabel;
	}
	
	private Button createButton(String buttonProperty, EventHandler<ActionEvent> handler){
		Button button = new Button(this.resourceBundle.getString(buttonProperty));
		button.setOnAction(handler);
		
		return button;
	}	
	
}
