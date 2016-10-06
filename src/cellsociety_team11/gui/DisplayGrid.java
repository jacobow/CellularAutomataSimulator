package cellsociety_team11.gui;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;


import cellsociety_team11.Cell;
import cellsociety_team11.CellSociety;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.SimulationInstantiationException;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Display Grid that holds the DisplayCells that are specific to the simulation type.
 * In current implementation, this is instantiated on every grid update rather than updated.
 * This isn't great from a performance standpoint, but made it more extensible in the meantime,
 * as updating the DisplayGrid in MainWindow becomes much easier.
 * Ultimately will make this more "efficient" once we have the new CA to implement.
 */
public class DisplayGrid<T> extends Pane{
	public static final String DISPLAY_GRID_RESOURCES = "DisplayGridClasses";
	public static final double MAX_WIDTH = CellSociety.INIT_WIDTH*2/4;
	private Grid<T> grid;
	private String simulationType;
	

	public DisplayGrid(Grid<T> grid, String simulationType, int numSides) throws SimulationInstantiationException{
		this.simulationType = simulationType;
		this.grid = grid;
		initDisplayGrid(numSides);
	}
	
	@SuppressWarnings("unchecked")
	public void updateDisplayCells(){
		for (Node d : this.getChildren()){
			DisplayCell<T> displayCell = (DisplayCell<T>) d;
			Cell<T> gridCell = grid.getCell(displayCell.getCoordinates());
			displayCell.updateValue(gridCell.getValue());
		}
			
	}
	
	
	
	public Grid<T> getGrid(){
		return this.grid;
	}
	
	private void initDisplayCells(int numSides){
		for (int i = 0; i < this.grid.getHeight(); i++){
			for (int j = 0; j < this.grid.getWidth(); j++){
				Coordinates currentCoords = new Coordinates(i, j);
				Cell<T> currentCell = this.grid.getCell(currentCoords);
				DisplayCell<T> displayCell;
				double cellWidth = MAX_WIDTH/getMaxDimension();
				try{
					displayCell = getDisplayCellInstance(currentCell.getValue(), currentCoords, cellWidth, numSides, ResourceBundle.getBundle(MainWindow.DEFAULT_RESOURCE_PACKAGE + DISPLAY_GRID_RESOURCES));
				}
				catch(SimulationInstantiationException e){
					e.printStackTrace();
					throw e;
				}
				//orientDisplayCell(displayCell, cellWidth, numSides, i, j);
				this.getChildren().add(displayCell);
			}
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	private DisplayCell<T> getDisplayCellInstance(T cellValue, Coordinates coordinates, double cellWidth, int numSides, ResourceBundle resourceBundle) throws SimulationInstantiationException{
		try{
			Class<?> simulationClass = Class.forName(resourceBundle.getString(this.simulationType));
			Constructor<?> myConstructor = simulationClass.getConstructors()[0];
			return (DisplayCell<T>) myConstructor.newInstance(new Object[] {cellValue, coordinates, cellWidth, numSides});
		}
		catch(Exception e){
			System.out.println(this.simulationType);
			throw new SimulationInstantiationException(e.getMessage(), e);
		}
	}
	
	private void initDisplayGrid(int numSides){
		setDimensions(getMaxDimension());
		initDisplayCells(numSides);
	}
	
	private int getMaxDimension(){
		return this.grid.getHeight() > this.grid.getWidth() ? this.grid.getHeight() : this.grid.getWidth();
	}
	
	private void setDimensions(int largestDimension){
		double cellSize = MAX_WIDTH/(double) largestDimension;
		this.setMaxWidth(this.grid.getWidth()*cellSize);
		this.setMaxHeight(this.grid.getHeight()*cellSize);
		this.setPrefWidth(this.grid.getWidth()*cellSize);
		this.setPrefHeight(this.grid.getHeight()*cellSize);
	}
}
