package cellsociety_team11;

import javafx.scene.input.MouseEvent;

public interface SimulationController {
	
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
}
