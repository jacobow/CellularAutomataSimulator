package cellsociety_team11.gui;

import java.util.ResourceBundle;

import cellsociety_team11.CellSociety;
import cellsociety_team11.Grid;
import cellsociety_team11.MainController;
import cellsociety_team11.SimulationInstantiationException;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


/**
 * @author Cleveland Quin Thompson V (ct168)
 * Main Window for CellSociety GUI. Handles instantiating the MainBorderPane,
 * which initializes the user controls, and updating the DisplayGrid based on the Grid input.
 */
public class MainWindow{
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private Scene scene;
	private MainBorderPane root;
	private DisplayGrid<?> displayGrid; 
	
	private ResourceBundle resourceBundle;
	private MainController mainController;
	private boolean dualDisplays;
	
	
	public MainWindow(MainController simulationController, String language){
		this.displayGrid = null;
		this.dualDisplays = false;
		this.mainController = simulationController;
		this.resourceBundle = initResourceBundle(language);
		initScene();
	}
	
	public <T> void setGrid(Grid<T> grid, String simulationType){
		this.displayGrid = null;
		if (grid!=null){
			try{
				this.displayGrid = new DisplayGrid<T>(grid, simulationType);
				this.root.setCenter(this.displayGrid);
			}
			catch(SimulationInstantiationException s){
				System.out.print("Caught SimException");
				root.showErrorAlert();
			}
		}
		
	}
	
	
	private ResourceBundle initResourceBundle(String language){
		return ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
	
	private void initScene(){
		this.root = this.initRoot();
		this.scene = new Scene(this.root, CellSociety.INIT_WIDTH, CellSociety.INIT_HEIGHT, Color.GHOSTWHITE);
	}
	
	private MainBorderPane initRoot(){
		return new MainBorderPane(this.mainController, this.resourceBundle);
	}
	
	public Scene getScene(){
		return this.scene;
	}
		
}
