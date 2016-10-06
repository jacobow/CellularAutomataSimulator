// This entire file is part of my masterpiece.
// Noel Moon

package cellsociety_team11;

import java.io.File;

import javafx.scene.input.MouseEvent;

/**
 * This is the interface for the controller of the simulations. It carries methods that have to be implemented for a class that implements
 * this interface. This interface is implemented by the CellSocietyController class.
 * 
 * This interface is well-designed because it clearly lists out the methods that need to be implemented in order to have a functional
 * controller. For example, for any controller, there has to be a way to start and stop the simulation. In this case, there also has to be
 * ways to control the speed of the simulation and a way to take in data that is going to be used by the simulation.
 * 
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
