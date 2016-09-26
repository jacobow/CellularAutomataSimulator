package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Highest Level of Abstraction for displaying the representation of each cell in the grid
 */
public abstract class DisplayCell<T> extends Pane{
	protected T currentValue;
	protected Coordinates coordinates;
	protected Shape cellShape;
	
	public DisplayCell(T value, Coordinates coordinates) {
		super();
		this.currentValue = value;
		this.coordinates = coordinates;
		this.cellShape = initCell();
		this.getChildren().add(cellShape);
		setColor(getColor());
	}
	
	
	/*
	 * Updates current value of cell. Necessary for the implementation
	 * in which I update each DisplayCell as oppose to re-instantiate DisplayGrid;
	 * keeping for legacy reasons until the project is finished
	 */
	public void updateValue(T newValue){
		//TODO: Check if this method is still necessary as we approach final product
		currentValue = newValue;
		setColor(getColor());
	}
	
	/*
	 * Gets the current value of the coordinates. Necessary for the implementation
	 * in which I update each DisplayCell as oppose to re-instantiate DisplayGrid;
	 * keeping for legacy reasons until the project is finished
	 */
	public Coordinates getCoordinates(){
		return this.coordinates;
	}
	
	protected abstract Shape initCell();
	
	protected abstract Color getColor();
	
	protected void setColor(Color color){
		cellShape.setFill(color);
	}

}
