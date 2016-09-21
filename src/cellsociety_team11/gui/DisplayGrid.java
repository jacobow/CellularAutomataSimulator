package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.Group;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import cellsociety_team11.Cell;

/**
 * @author quin
 *
 */
public class DisplayGrid extends GridPane{

	private Grid<?> grid;
	
	public DisplayGrid(Grid<?> grid){
		this.grid = grid;
		initDisplayGrid();
	}
	
	private void initDisplayGrid(){
		initConstraints();
		initDisplayCells();
		//magic number for now
		this.setHeight(800);
		this.minWidthProperty().bind(this.heightProperty());
		this.maxWidthProperty().bind(this.heightProperty());
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
		this.autosize();
		for (int i = 0; i < grid.getHeight(); i++){
			
			for (int j = 0; j < grid.getWidth(); j++){
				DisplayCell displayCell = new DisplayCell();
				this.add(displayCell, j, i);
			}
		}
	}
}
