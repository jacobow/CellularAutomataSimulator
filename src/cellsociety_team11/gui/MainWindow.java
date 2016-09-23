package cellsociety_team11.gui;

import java.util.ResourceBundle;

import com.sun.org.apache.regexp.internal.recompile;

import cellsociety_team11.CellSociety;
import cellsociety_team11.Grid;
import cellsociety_team11.SimulationController;
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
	private Grid<?> grid;
	
	private ResourceBundle resourceBundle;
	private SimulationController simulationController;
	
	
	public MainWindow(SimulationController simulationController, String language){
		displayGrid = null;
		grid = null;
		this.simulationController = simulationController;
		this.resourceBundle = initResourceBundle(language);
		initScene();
	}
	
	public void setGrid(Grid<?> grid){
		this.grid = grid;
		DisplayGrid displayGrid = new DisplayGrid(grid);
		this.root.setCenter(displayGrid);
	}
	
	public void updateGrid(){
		if (grid != null && displayGrid!=null){
			displayGrid.updateDisplayCells(grid);
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
		return new MainBorderPane(simulationController, resourceBundle);
	}
	
	public Scene getScene(){
		return scene;
	}
	
	
	
	
}
