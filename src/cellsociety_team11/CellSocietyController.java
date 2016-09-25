package cellsociety_team11;

import java.io.File;
import java.util.Arrays;
import cellsociety_team11.game_of_life.GameOfLifeCell;
import cellsociety_team11.game_of_life.GameOfLifeGrid;
import cellsociety_team11.game_of_life.GameOfLifeRules;
import cellsociety_team11.gui.MainBorderPane;
import cellsociety_team11.gui.MainWindow;
import cellsociety_team11.gui.SimulationType;
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

public class CellSocietyController implements SimulationController{
	public static final Boolean[][] TEST_INIT_GRID= new Boolean[][]{
		{false, false, false, true, true},
		{false, false, true, true, false},
		{false, true, true, false, false},
		{false, true, false, false, true},
		{false, true, false, false, true}
		};
	
	private static final double INIT_FRAMES_PER_SECOND = 4;
	private static final double MILLISECOND_DELAY = 1000.0 / INIT_FRAMES_PER_SECOND;
	private static final String XML_FILE_LOCATION = "data/CA_xml/GameOfLife2.xml";
	private static final String XML_SUFFIX = ".xml";
	
	private MainWindow mainWindow;
	private GameOfLifeGrid grid;
	private Timeline timeline;
	private double simulationSpeed;
	private SimulationXMLModel model;

	public CellSocietyController(String language){
		simulationSpeed = MainBorderPane.SPEED_SLIDER_START;
		this.mainWindow = new MainWindow(this, language);
		getFileData();
		System.out.println(model.getInitialLayout()[0][0]);
		grid = new GameOfLifeGrid(intToBool(model.getInitialLayout()));
		//grid = new GameOfLifeGrid(TEST_INIT_GRID);
		//testSetGrid();
		this.mainWindow.setGrid(grid, SimulationType.GAMEOFLIFE);
		
	}
	
	private void getFileData(){
	    XMLParser parser = new XMLParser();
	    SimulationXMLFactory factory = new SimulationXMLFactory("Simulation");
	    File f = new File(XML_FILE_LOCATION);
	    if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
	        try {
	            model = factory.getSimulation(parser.getRootElement(f.getAbsolutePath()));
	        }
	        catch (XMLFactoryException e) {
	            System.err.println("Reading file " + f.getPath());
	            e.printStackTrace();
	        }
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
		System.out.println("Updated Grid");
		printGrid(grid);
		mainWindow.setGrid(grid, SimulationType.GAMEOFLIFE);
	}
	
	
	
	private void printGrid(Grid grid){
		for (int i = 0; i < grid.getWidth(); i++){
			for (int j = 0; j< grid.getHeight(); j++){
				Cell currCell = grid.getCell(new Coordinates(i, j));
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
