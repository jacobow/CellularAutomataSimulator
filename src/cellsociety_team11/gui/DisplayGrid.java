package cellsociety_team11.gui;

import java.lang.reflect.Constructor;

import cellsociety_team11.Cell;
import cellsociety_team11.CellSociety;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Display Grid that holds the DisplayCells that are specific to the simulation type.
 * In current implementation, this is instantiated on every grid update rather than updated.
 * This isn't great from a performance standpoint, but made it more extensible in the meantime,
 * as updating the DisplayGrid in MainWindow becomes much easier.
 * Ulimately will make this more "efficient" once we have the new CA to implement.
 */
public class DisplayGrid<T> extends GridPane{
	private static final double MAX_WIDTH = CellSociety.INIT_WIDTH*3/4;
	private Grid<T> grid;
	private double cellSize;
	private SimulationType simulationType;
	

	public DisplayGrid(Grid<T> grid, SimulationType simulationType) {
		this.simulationType = simulationType;
		this.grid = grid;
		initDisplayGrid();
	}
	
	private void initDisplayCells(){
		for (int i = 0; i < grid.getHeight(); i++){
			for (int j = 0; j < grid.getWidth(); j++){
				Coordinates currentCoords = new Coordinates(i, j);
				Cell<T> currentCell = grid.getCell(currentCoords);
				this.add(getDisplayCellInstance(currentCell.getValue(), currentCoords), j, i);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private DisplayCell<T> getDisplayCellInstance(T cellValue, Coordinates coordinates){
		Class <? extends DisplayCell<?>> simulationClass = simulationType.getDisplayCellClass();
		Constructor<? extends DisplayCell<?>> myConstructor = (Constructor<? extends DisplayCell<?>>) simulationClass.getConstructors()[0];
		DisplayCell<T> displayCell = null;
		try{
			displayCell = (DisplayCell<T>) myConstructor.newInstance(new Object[] {cellValue, coordinates});
		}
		catch(Exception e){
			System.err.println("Couldn't Get DisplayCell Instance");
			return null;
		}
		return displayCell;
	}
	
	private void initDisplayGrid(){
		initConstraints();
		if (grid.getHeight() > grid.getWidth()){
			setDimensions(grid.getHeight());
		}
		else{
			setDimensions(grid.getWidth());
		}
		
		initDisplayCells();
	}
	
	private void initConstraints(){
		
		for (int i = 0; i<grid.getWidth(); i++){
			this.addCustomColumnConstraint();
		}
		
		for (int i = 0; i<grid.getHeight(); i++){
			this.addCustomRowConstraint();
		}
	}
	
	private void addCustomColumnConstraint(){
		ColumnConstraints columnResizing = new ColumnConstraints();
		columnResizing.prefWidthProperty().bind(this.widthProperty().divide(grid.getWidth()));
		this.getColumnConstraints().add(columnResizing);
	}
	
	private void addCustomRowConstraint(){
		RowConstraints rowResizing = new RowConstraints();
		rowResizing.prefHeightProperty().bind(this.heightProperty().divide(grid.getHeight()));
		this.getRowConstraints().add(rowResizing);
	}
	
	private void setDimensions(int largestDimension){
		cellSize = MAX_WIDTH/(double) largestDimension;
		this.setMaxWidth(grid.getWidth()*cellSize);
		this.setMaxHeight(grid.getHeight()*cellSize);
		this.setMinWidth(grid.getWidth()*cellSize);
		this.setMinHeight(grid.getHeight()*cellSize);
	}

}
