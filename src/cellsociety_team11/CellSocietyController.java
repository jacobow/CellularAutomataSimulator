package cellsociety_team11;

import java.sql.Time;
import java.io.File;
import java.util.Arrays;
import cellsociety_team11.game_of_life.GameOfLifeCell;
import cellsociety_team11.game_of_life.GameOfLifeGrid;
import cellsociety_team11.game_of_life.GameOfLifeRules;
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
	public static final Boolean[][] BOOL_INIT_GRID= new Boolean[][]{
		{false, false, false, true, true},
		{false, false, true, true, false},
		{false, true, true, false, false},
		{false, true, false, false, true},
		{false, true, false, false, true}
		};

	public static final Integer[][] INT_INIT_GRID= new Integer[][]{
		{0, 0, 0, 0},
		{0, 2, 1, 2},
		{0, 0, 1, 0},
		{1, 1, 0, 1},
		{0, 0, 0, 0}
		};

	private static final double INIT_FRAMES_PER_SECOND = 2;
    private static final double MILLISECOND_DELAY = 1000.0 / INIT_FRAMES_PER_SECOND;
    
	private static final String XML_FILE_LOCATION = "data/CA_xml/SpreadingOfFire.xml";
	private static final String XML_SUFFIX = ".xml";

	private MainWindow mainWindow;
	private Grid<?> grid;
	private Timeline timeline;
	private double simulationSpeed;
	private SimulationType simulationType;

	private boolean isPlaying;

	private SimulationXMLModel simulation;

	public CellSocietyController(String language){
		isPlaying = false;
		readFileData();
		simulationSpeed = MainBorderPane.SPEED_SLIDER_START;
		this.mainWindow = new MainWindow(this, language);
		setSimulationGrid(simulation.getSimulationName());
		this.timeline = initSimulation();
		this.mainWindow.setGrid(grid, this.simulationType);
	}
	
	private void readFileData(){
	    XMLParser parser = new XMLParser();
	    SimulationXMLFactory factory = new SimulationXMLFactory("Simulation");
	    File f = new File(XML_FILE_LOCATION);
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

	public Scene getScene(){
		return mainWindow.getScene();
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

	@Override
	public void startSimulation(){
		isPlaying = true;
		timeline.play();
	}

	@Override
	public void nextStepSimulation() {
		grid.nextGrid();
		//printGrid(grid);
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
		// TODO Auto-generated method stub
		isPlaying = false; 
		timeline.stop();
	}
}
