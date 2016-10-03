package cellsociety_team11.gui;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

import com.sun.org.apache.xpath.internal.operations.And;

import cellsociety_team11.Cell;
import cellsociety_team11.CellSociety;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.SimulationInstantiationException;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Display Grid that holds the DisplayCells that are specific to the simulation type.
 * In current implementation, this is instantiated on every grid update rather than updated.
 * This isn't great from a performance standpoint, but made it more extensible in the meantime,
 * as updating the DisplayGrid in MainWindow becomes much easier.
 * Ulimately will make this more "efficient" once we have the new CA to implement.
 */
public class DisplayGrid<T> extends Pane{
	public static final String DISPLAY_GRID_RESOURCES = "DisplayGridClasses";
	public static final double MAX_WIDTH = CellSociety.INIT_WIDTH*3/4;
	private Grid<T> grid;
	private String simulationType;
	

	public DisplayGrid(Grid<T> grid, String simulationType) throws SimulationInstantiationException{
		this.simulationType = simulationType;
		this.grid = grid;
		int numSides = 3;
		initDisplayGrid(numSides);
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
				orientDisplayCell(displayCell, cellWidth, numSides, i, j);
				//this.add(displayCell, j, i);
				this.getChildren().add(displayCell);
				//System.out.println("Shape Width " + displayCell.getWidth());
			}
		}
	}
	
	public void orientDisplayCell(DisplayCell<T> displayCell, double cellWidth, int numSides, int rowIndex, int colIndex){
		if (numSides == 3){
			if (!((colIndex % 2 != 0 && rowIndex % 2 != 0) || (colIndex % 2 == 0 && rowIndex % 2 == 0))){
				displayCell.customRotate(Math.toDegrees(Math.PI), 0);
				
			}
			//double newColIndex = (double) colIndex/2.0;
			//double xMoveFactor = cellWidth * newColIndex;
			//System.out.println("xfactor: " + newColIndex + " "+ xMoveFactor);
			displayCell.moveCell(cellWidth * ((double)colIndex)/2.0, rowIndex * cellWidth);
			
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
			throw new SimulationInstantiationException(e.getMessage(), e);
		}
	}
	
	private void initDisplayGrid(int numSides){
		//initConstraints();		
		setDimensions(getMaxDimension());
		initDisplayCells(numSides);
	}
	
	private int getMaxDimension(){
		return this.grid.getHeight() > this.grid.getWidth() ? this.grid.getHeight() : this.grid.getWidth();
	}
	/*
	private void initConstraints(){
		
		for (int i = 0; i<this.grid.getWidth(); i++){
			this.addCustomColumnConstraint();
		}
		
		for (int i = 0; i<this.grid.getHeight(); i++){
			this.addCustomRowConstraint();
		}
	}
	
	private void addCustomColumnConstraint(){
		ColumnConstraints columnResizing = new ColumnConstraints();
		columnResizing.prefWidthProperty().bind(this.widthProperty().divide(this.grid.getWidth()));
		this.getColumnConstraints().add(columnResizing);
	}
	
	private void addCustomRowConstraint(){
		RowConstraints rowResizing = new RowConstraints();
		rowResizing.prefHeightProperty().bind(this.heightProperty().divide(this.grid.getHeight()));
		this.getRowConstraints().add(rowResizing);
	}*/
	
	private void setDimensions(int largestDimension){
		double cellSize = MAX_WIDTH/(double) largestDimension;
		this.setMaxWidth(this.grid.getWidth()*cellSize);
		this.setMaxHeight(this.grid.getHeight()*cellSize);
		this.setPrefWidth(this.grid.getWidth()*cellSize);
		this.setPrefHeight(this.grid.getHeight()*cellSize);
	}
}
