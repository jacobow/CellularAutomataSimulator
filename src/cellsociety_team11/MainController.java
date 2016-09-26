package cellsociety_team11;

import java.io.File;

import javafx.scene.input.MouseEvent;

/**
 * @author Cleveland Quin Thompson V (ct168)
 *
 */
public interface MainController {
	public static final String XML_SUFFIX = ".xml";
	public static final String DATA_DIRECTORY = "./data";
	/*
	 * Starts the Simulation at the current Simulation Speed
	 */
	public void startSimulation();
	
	/*
	 * Progresses the Simulation one frame
	 */
	public void nextStepSimulation();
	
	/*
	 * Updates the Simulation Speed based on the Speed Slider
	 */
	public void updateSimulationSpeed(MouseEvent speedUpdated);
	
	/*
	 * Stops the Simulation
	 */
	public void stopSimulation();
	
	/*
	 * Handles the upload of a new XML file and reinitializes the Simulation
	 */
	public void uploadedXMLFileHandler(File xmlFile);
}
