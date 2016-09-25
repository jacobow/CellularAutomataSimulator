package cellsociety_team11;

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

	private static final double INIT_FRAMES_PER_SECOND = 4;
	private static final double MILLISECOND_DELAY = 1000.0 / INIT_FRAMES_PER_SECOND;
	
	
	private static final String XML_FILE_LOCATION = "data/CA_xml/PredatorPrey.xml";
	private static final String XML_SUFFIX = ".xml";
	
	private MainWindow mainWindow;
	private Grid<?> grid;
	private Timeline timeline;
	private double simulationSpeed;
	private SimulationType simulationType;
	private SimulationXMLModel simulation;

	public CellSocietyController(String language){
		readFileData();
		simulationSpeed = MainBorderPane.SPEED_SLIDER_START;
		this.mainWindow = new MainWindow(this, language);
		setSimulationGrid(simulation.getSimulationName());
		//simulationType = SimulationType.SPREADING_OF_FIRE;
		//testSetGrid();
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

	@Override
	public void startSimulation(){
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY * simulationSpeed),
                e -> this.nextStepSimulation());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();
	}

	@Override
	public void nextStepSimulation() {
		grid.nextGrid();
		//printGrid(grid);
		mainWindow.setGrid(grid, this.simulationType);
	}



	private void printGrid(Grid<?> grid){
		for (int i = 0; i < grid.getWidth(); i++){
			for (int j = 0; j< grid.getHeight(); j++){
				Cell<?> currCell = grid.getCell(new Coordinates(i, j));
				System.out.print(currCell.getValue().toString() + " ");
			}
			System.out.println("");
		}
	}

	@Override
	public void updateSimulationSpeed(MouseEvent speedUpdated) {
		// TODO Auto-generated method stub
		Slider speedSlider = (Slider) speedUpdated.getSource();
		this.simulationSpeed = speedSlider.getValue();
		timeline.setDelay(Duration.millis(MILLISECOND_DELAY * simulationSpeed));
	}

	@Override
	public void stopSimulation() {
		// TODO Auto-generated method stub
		timeline.stop();
	}
}