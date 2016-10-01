package cellsociety_team11.gui;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

import cellsociety_team11.Cell;
import cellsociety_team11.CellSociety;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.SimulationInstantiationException;
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
	public static final String DISPLAY_GRID_RESOURCES = "DisplayGridClasses";
	private static final double MAX_WIDTH = CellSociety.INIT_WIDTH*3/4;
	private Grid<T> grid;
	private String simulationType;
	

	public DisplayGrid(Grid<T> grid, String simulationType) throws SimulationInstantiationException{
		this.simulationType = simulationType;
		this.grid = grid;
		initDisplayGrid();
	}
	
	private void initDisplayCells(){
		for (int i = 0; i < this.grid.getHeight(); i++){
			for (int j = 0; j < this.grid.getWidth(); j++){
				Coordinates currentCoords = new Coordinates(i, j);
				Cell<T> currentCell = this.grid.getCell(currentCoords);
				try{
					this.add(getDisplayCellInstance(currentCell.getValue(), currentCoords, ResourceBundle.getBundle(MainWindow.DEFAULT_RESOURCE_PACKAGE + DISPLAY_GRID_RESOURCES)), j, i);
				}
				catch(SimulationInstantiationException e){
					throw e;
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private DisplayCell<T> getDisplayCellInstance(T cellValue, Coordinates coordinates, ResourceBundle resourceBundle) throws SimulationInstantiationException{
		try{
			Class<?> simulationClass = Class.forName(resourceBundle.getString(this.simulationType));
			Constructor<?> myConstructor = simulationClass.getConstructors()[0];
			return (DisplayCell<T>) myConstructor.newInstance(new Object[] {cellValue, coordinates});
		}
		catch(Exception e){
			throw new SimulationInstantiationException(e.getMessage(), e);
		}
	}
	
	private void initDisplayGrid(){
		initConstraints();
		if (this.grid.getHeight() > this.grid.getWidth()){
			setDimensions(this.grid.getHeight());
		}
		else{
			setDimensions(this.grid.getWidth());
		}
		
		initDisplayCells();
	}
	
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
	}
	
	private void setDimensions(int largestDimension){
		double cellSize = MAX_WIDTH/(double) largestDimension;
		this.setMaxWidth(this.grid.getWidth()*cellSize);
		this.setMaxHeight(this.grid.getHeight()*cellSize);
		this.setMinWidth(this.grid.getWidth()*cellSize);
		this.setMinHeight(this.grid.getHeight()*cellSize);
	}

}
