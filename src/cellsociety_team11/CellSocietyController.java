// This entire file is part of my masterpiece.
// Noel Moon

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

/**
 * This class extends the MainController interface and handles the stepping from one state to the next. It also reads in the data from the
 * XML File so that it can be used by the rest of the program. It manages the simulation speed and handles updating the simulation speed
 * if the user changes the speed using the speed slider. 
 * 
 * This class is well-designed because it provides very functional and readable code that implements the methods that were required by the
 * interface that this class implements. There is also encapsulation that hides the implementation of the how exactly the XML file is read from,
 * which can be seen in the private readFileData() method. This encapsulation is necessary because the code for reading in an XML file is 
 * difficult to read and it is not necessary to be seen to use the uploadedXMLFileHandler() method. Another encapsulated method is the 
 * getSimulationGrid() method, which is properly encapsulated because someone using this class doesn't have to know how the code of the
 * getSimulationGrid() method is written as long as they know that this method gets the simulation grid, as its name states.
 * 
 * @author Cleveland Quin Thompson V (ct168)
 * @author Noel Moon (nm142)
 */
public class CellSocietyController implements MainController{

	private static final double INIT_FRAMES_PER_SECOND = 2;
	private static final double MILLISECOND_DELAY = 1000.0 / INIT_FRAMES_PER_SECOND;
	private static final String GRID_RESOURCES = "GridClasses";

	private MainWindow mainWindow;
	private Grid<?> leftGrid, rightGrid;
	private Timeline timeline;
	private double simulationSpeed;
	private boolean isPlaying;

	public CellSocietyController(String language){
		this.isPlaying = false;
		this.simulationSpeed = MainBorderPane.SPEED_SLIDER_START;
		this.mainWindow = new MainWindow(this, language);
		this.timeline = initSimulation();
	}
	
	/*
         * Starts the Simulation at the current Simulation Speed
         */
	@Override
	public void startSimulation(){
		if (this.leftGrid!=null){
			this.isPlaying = true;
			this.timeline.play();
		}
	}

	/*
         * Progresses the Simulation one frame
         */
	@Override
	public void nextStepSimulation() {
		if (this.leftGrid!=null){
			this.leftGrid.nextGrid();	
		}
		if (this.rightGrid!=null){
			this.rightGrid.nextGrid();
		}
		this.mainWindow.updateGrids();
	}

	/*
         * Updates the Simulation Speed based on the Speed Slider
         */
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

	/*
         * Stops the Simulation
         */
	@Override
	public void stopSimulation() {
		this.isPlaying = false; 
		this.timeline.stop();
	}

	/*
         * Handles the upload of a new XML file and reinitializes the Simulation
         */
	@Override
	public void uploadedXMLFileHandler(File xmlFile) {
	    try {
	        this.stopSimulation();
	        SimulationXMLModel currentSimulation = readFileData(xmlFile.getAbsolutePath());
	        Grid<?> grid = getSimulationGrid(currentSimulation);
	        this.timeline = initSimulation();
	        Boolean chosenGrid = this.mainWindow.setGrid(grid, currentSimulation.getSimulationName(), currentSimulation.getShape());
	        if (chosenGrid==null){
	            return;
	        }
	        if (chosenGrid){
	            this.leftGrid = grid;
	        }
	        else{
	            this.rightGrid = grid;
	        }
	    }
	    catch (XMLFactoryException e) {
	        e.printStackTrace();
	    }
	}
	
	/*
	 * Gets the Scene from the GUI
	 */
	public Scene getScene(){
		return this.mainWindow.getScene();
	}
	
	private SimulationXMLModel readFileData(String xmlFileLocation){
	    XMLParser parser = new XMLParser();
	    SimulationXMLFactory factory = new SimulationXMLFactory("Simulation");
	    File f = new File(xmlFileLocation);
	    if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
	        try {
	        	return factory.getSimulation(parser.getRootElement(f.getAbsolutePath()));
	        }
	        catch (XMLFactoryException e) {
	            System.err.println("Reading file " + f.getPath());
	            e.printStackTrace();
	            return null;
	        }
	    }
	    else{
	    	System.err.println("Not xml file. " + f.getAbsolutePath());
	    	return null;
	    }
	}
	
	private Grid<?> getSimulationGrid(SimulationXMLModel simulation) throws XMLFactoryException {
		try{
			return getGridInstance(simulation, simulation.getInitialLayout(), simulation.getSimulationName(), ResourceBundle.getBundle(MainWindow.DEFAULT_RESOURCE_PACKAGE + GRID_RESOURCES));
		}
		catch(SimulationInstantiationException e){
			e.printStackTrace();
			throw e;
		}
		catch(XMLFactoryException e){
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