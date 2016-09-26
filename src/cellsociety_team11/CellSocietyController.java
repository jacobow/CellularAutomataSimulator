package cellsociety_team11;

import java.io.File;
import cellsociety_team11.game_of_life.GameOfLifeGrid;
import cellsociety_team11.gui.MainBorderPane;
import cellsociety_team11.gui.MainWindow;
import cellsociety_team11.gui.SimulationType;
import cellsociety_team11.predator_prey.PredatorPreyGrid;
import cellsociety_team11.segregation.SegregationGrid;
import cellsociety_team11.spreading_of_fire.SpreadingOfFireGrid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import xml.factory.SimulationXMLFactory;
import xml.factory.XMLFactoryException;
import xml.model.SimulationXMLModel;
import xml.parser.XMLParser;

public class CellSocietyController implements MainController{

	private static final double INIT_FRAMES_PER_SECOND = 2;
    private static final double MILLISECOND_DELAY = 1000.0 / INIT_FRAMES_PER_SECOND;
	

	private MainWindow mainWindow;
	private Grid<?> grid;
	private Timeline timeline;
	private double simulationSpeed;
	private SimulationType simulationType;

	private boolean isPlaying;

	private SimulationXMLModel simulation;

	public CellSocietyController(String language){
		isPlaying = false;
		simulationSpeed = MainBorderPane.SPEED_SLIDER_START;
		this.mainWindow = new MainWindow(this, language);
		this.timeline = initSimulation();
	}
	
	@Override
	public void startSimulation(){
		isPlaying = true;
		timeline.play();
	}

	@Override
	public void nextStepSimulation() {
		grid.nextGrid();
		mainWindow.setGrid(grid, this.simulationType);
	}


	@Override
	public void updateSimulationSpeed(MouseEvent speedUpdated) {
		Slider speedSlider = (Slider) speedUpdated.getSource();
		this.simulationSpeed = speedSlider.getValue();
		this.timeline.stop();
		this.timeline.getKeyFrames().clear();
		this.timeline.getKeyFrames().add(getKeyFrame(MILLISECOND_DELAY / simulationSpeed));
		if (isPlaying){
			this.timeline.playFromStart();
		}
	}

	@Override
	public void stopSimulation() {
		isPlaying = false; 
		timeline.stop();
	}

	@Override
	public void uploadedXMLFileHandler(File xmlFile) {
		this.stopSimulation();
		readFileData(xmlFile.getAbsolutePath());
		setSimulationGrid(simulation.getSimulationName());
		this.timeline = initSimulation();
		this.mainWindow.setGrid(grid, this.simulationType);
	}
	
	/*
	 * Gets the Scene from the GUI
	 */
	public Scene getScene(){
		return mainWindow.getScene();
	}
	
	private void readFileData(String xmlFileLocation){
	    XMLParser parser = new XMLParser();
	    SimulationXMLFactory factory = new SimulationXMLFactory("Simulation");
	    File f = new File(xmlFileLocation);
	    if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
	        try {
	            simulation = factory.getSimulation(parser.getRootElement(f.getAbsolutePath()));
	        }
	        catch (XMLFactoryException e) {
	            System.err.println("Reading file " + f.getPath());
	            e.printStackTrace();
	        }
	    }
	}
	
	private void setSimulationGrid(String simulationTypeStr) {
	    if (simulationTypeStr.equals("Game of Life")) {
	        simulationType = SimulationType.GAMEOFLIFE;
	        grid = new GameOfLifeGrid(intToBool(simulation.getInitialLayout()));
	    }
	    if (simulationTypeStr.equals("Segregation")) {
	        simulationType = SimulationType.SEGREGATION;
	        grid = new SegregationGrid(simulation.getInitialLayout(), simulation.getProbability());
	    }
	    if (simulationTypeStr.equals("Spreading of Fire")) {
	        simulationType = SimulationType.SPREADING_OF_FIRE;
	        grid = new SpreadingOfFireGrid(simulation.getInitialLayout(), simulation.getProbability());
            }
	    if (simulationTypeStr.equals("Predator Prey")) {
	        simulationType = SimulationType.PREDATOR_PREY;
	        grid = new PredatorPreyGrid(simulation.getInitialLayout(), simulation.getPredatorLifeSpan(), 
	                                    simulation.getPreyBreedingSpan(), simulation.getPredatorBreedingSpan());
	    }
	}
	
	private Boolean[][] intToBool(Integer[][] array) {
	    int rows = array.length;
	    int columns = array[0].length;
	    Boolean[][] result = new Boolean[rows][columns];
            for (int i=0; i<rows; i++){
                for (int j=0; j<columns; j++){
                    result[i][j] = (array[i][j] == 1);
                }
            }
            return result;
	}

	
	private Timeline initSimulation(){
		Timeline simulationTimeline = new Timeline();
		simulationTimeline.setCycleCount(Timeline.INDEFINITE);
		simulationTimeline.getKeyFrames().add(getKeyFrame(MILLISECOND_DELAY / simulationSpeed));
		return simulationTimeline;
	}
	
	private KeyFrame getKeyFrame(double frameDuration){
		KeyFrame frame = new KeyFrame(Duration.millis(frameDuration),
                e -> this.nextStepSimulation());
		return frame;
	}

	
}
