package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import cellsociety_team11.Cell;
import cellsociety_team11.CellSociety;

/**
 * @author quin
 *
 */
public class DisplayGrid extends GridPane{
	private static final double MAX_WIDTH = CellSociety.INIT_WIDTH*3/4;
	private Grid<?> grid;
	private double cellSize;
	
	public DisplayGrid(Grid<?> grid){
		this.grid = grid;
		initDisplayGrid();
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
	
	private void setDimensions(int largestDimension){
		cellSize = MAX_WIDTH/(double) largestDimension;
		this.setWidth(grid.getWidth()*cellSize);
		this.setHeight(grid.getHeight()*cellSize);
		this.setMaxWidth(grid.getWidth()*cellSize);
		this.setMaxHeight(grid.getHeight()*cellSize);
		this.setMinWidth(grid.getWidth()*cellSize);
		this.setMinHeight(grid.getHeight()*cellSize);
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
	
	private void initDisplayCells(){
		for (int i = 0; i < grid.getHeight(); i++){
			for (int j = 0; j < grid.getWidth(); j++){
				Coordinates currentCoords = new Coordinates(i, j);
				Cell<?> currentCell = grid.getCell(currentCoords);
				DisplayCell displayCell = new DisplayCell((boolean) currentCell.getValue(), currentCoords);
				this.add(displayCell, j, i);
			}
		}
	}
	
	//duplicated code for testing purposes right now
	public void updateDisplayCells(Grid<?> newGrid){
		for (Node displayCell : this.getChildren()){
			DisplayCell currDisplayCell = (DisplayCell) displayCell;
			Coordinates currCoordinates = currDisplayCell.getCoordinates();
			Cell<?> currentCell = newGrid.getCell(new Coordinates(currCoordinates.getI(), currCoordinates.getJ()));
			currDisplayCell.updateAlive((boolean) currentCell.getValue());
		}
	}
}
