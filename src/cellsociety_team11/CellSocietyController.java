package cellsociety_team11;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

import cellsociety_team11.gui.MainBorderPane;
import cellsociety_team11.gui.MainWindow;
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
	private static final String GRID_RESOURCES = "GridClasses";
	

	private MainWindow mainWindow;
	private Grid<?> grid;
	private Timeline timeline;
	private double simulationSpeed;
	private boolean isPlaying;
	private SimulationXMLModel simulation;

	public CellSocietyController(String language){
		this.isPlaying = false;
		this.simulationSpeed = MainBorderPane.SPEED_SLIDER_START;
		this.mainWindow = new MainWindow(this, language);
		this.timeline = initSimulation();
	}
	
	@Override
	public void startSimulation(){
		if (this.grid!=null){
			this.isPlaying = true;
			this.timeline.play();
		}
	}

	@Override
	public void nextStepSimulation() {
		if (this.grid!=null){
			this.grid.nextGrid();
			this.mainWindow.setGrid(this.grid, this.simulation.getSimulationName());
		}
	}


	@Override
	public void updateSimulationSpeed(MouseEvent speedUpdated) {
		Slider speedSlider = (Slider) speedUpdated.getSource();
		this.simulationSpeed = speedSlider.getValue();
		this.timeline.stop();
		this.timeline.getKeyFrames().clear();
		this.timeline.getKeyFrames().add(getKeyFrame(MILLISECOND_DELAY / this.simulationSpeed));
		if (this.isPlaying){
			this.timeline.playFromStart();
		}
	}

	@Override
	public void stopSimulation() {
		this.isPlaying = false; 
		this.timeline.stop();
	}

	@Override
	public void uploadedXMLFileHandler(File xmlFile) {
		this.stopSimulation();
		readFileData(xmlFile.getAbsolutePath());
		this.grid = setSimulationGrid();
		this.timeline = initSimulation();
		this.mainWindow.setGrid(this.grid, this.simulation.getSimulationName());
	}
	
	/*
	 * Gets the Scene from the GUI
	 */
	public Scene getScene(){
		return this.mainWindow.getScene();
	}
	
	private void readFileData(String xmlFileLocation){
	    XMLParser parser = new XMLParser();
	    SimulationXMLFactory factory = new SimulationXMLFactory("Simulation");
	    File f = new File(xmlFileLocation);
	    if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
	        try {
	        	this.simulation = factory.getSimulation(parser.getRootElement(f.getAbsolutePath()));
	        }
	        catch (XMLFactoryException e) {
	            System.err.println("Reading file " + f.getPath());
	            e.printStackTrace();
	        }
	    }
	    else{
	    	System.err.println("Not xml file. " + f.getAbsolutePath());
	    }
	}
	
	private Grid<?> setSimulationGrid() {
		try{
			return getGridInstance(this.simulation, this.simulation.getInitialLayout(), this.simulation.getSimulationName(), ResourceBundle.getBundle(MainWindow.DEFAULT_RESOURCE_PACKAGE + GRID_RESOURCES));
		}
		catch(SimulationInstantiationException e){
			e.printStackTrace();
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> Grid<?> getGridInstance(SimulationXMLModel simulation, T[][] valueGrid, String simulationName, ResourceBundle resourceBundle) throws SimulationInstantiationException{
		try{
			Class<?> simulationClass = Class.forName(resourceBundle.getString(simulationName));
			Constructor<?> myConstructor = simulationClass.getConstructors()[0];
			return (Grid<T>) myConstructor.newInstance(new Object[] {valueGrid, simulation});
		}
		catch(Exception e ){
			System.out.println("Can't Instaniate Grid");
			throw new SimulationInstantiationException(e.getMessage(), e);
		}
	}
	
	private Timeline initSimulation(){
		Timeline simulationTimeline = new Timeline();
		simulationTimeline.setCycleCount(Timeline.INDEFINITE);
		simulationTimeline.getKeyFrames().add(getKeyFrame(MILLISECOND_DELAY / this.simulationSpeed));
		return simulationTimeline;
	}
	
	private KeyFrame getKeyFrame(double frameDuration){
		KeyFrame frame = new KeyFrame(Duration.millis(frameDuration),
                e -> this.nextStepSimulation());
		return frame;
	}

	
}
