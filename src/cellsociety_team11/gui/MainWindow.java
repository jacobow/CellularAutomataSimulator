package cellsociety_team11.gui;

import java.util.ResourceBundle;

import com.sun.org.apache.regexp.internal.recompile;

import cellsociety_team11.CellSociety;
import cellsociety_team11.Grid;
import cellsociety_team11.MainController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * @author quin
 *
 */
public class MainWindow{
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private Scene scene;
	private MainBorderPane root;
	private DisplayGrid displayGrid; 
	
	private ResourceBundle resourceBundle;
	private MainController mainController;
	
	
	public MainWindow(MainController simulationController, String language){
		displayGrid = null;
		this.mainController = simulationController;
		this.resourceBundle = initResourceBundle(language);
		initScene();
	}
	
	public <T> void setGrid(Grid<T> grid, SimulationType simulationType){
		if (grid!=null){
			if (this.displayGrid == null){
				this.displayGrid = new DisplayGrid<T>(grid, simulationType);
				this.root.setCenter(displayGrid);
			}
			else{
				this.displayGrid.updateDisplayCells(grid);
			}
		}
		
	}
	
	private ResourceBundle initResourceBundle(String language){
		return ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
	
	private void initScene(){
		this.root = this.initRoot();
		scene = new Scene(this.root, CellSociety.INIT_WIDTH, CellSociety.INIT_HEIGHT, Color.GHOSTWHITE);
	}
	
	private MainBorderPane initRoot(){
		return new MainBorderPane(mainController, resourceBundle);
	}
	
	public Scene getScene(){
		return scene;
	}
		
}
