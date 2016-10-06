// This entire file is part of my masterpiece.
// Cleveland Thompson V (ct168)

package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Highest Level of Abstraction for displaying the representation of each cell in the grid
 */
public abstract class DisplayCell<T> extends Pane implements DisplayCellInterface{
	private T currentValue;
	private Coordinates coordinates;
	private CellShape cellShape;
	private int numSides;
	private double cellWidth;
	
	public DisplayCell(T value, Coordinates coordinates, double width, int numSides) {
		super();
		this.currentValue = value;
		this.coordinates = coordinates;
		this.numSides = numSides;
		this.cellWidth = width;
		this.cellShape = initCell(width, numSides);
		this.getChildren().add(cellShape);
		setColor(getColor());
	}
	
	/*
	 * 
	 */
	@Override
	public void moveCell(double x, double y){
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
	
	@Override
	public int getNumberOfSides(){
		return this.numSides;
	}
	
	@Override
	public double getCellWidth(){
		return this.cellWidth;
	}
	
	
	
	public void updateValue(T newValue){
		this.currentValue = newValue;
		setColor(getColor());
	}
	
	/*
	 * Gets the current value of the coordinates. 
	 * 
	 */
	@Override
	public Coordinates getCoordinates(){
		return this.coordinates;
	}
	
	private CellShape initCell(double width, int numSides){
		CellShape cellShape = new CellShape(this);
		cellShape.setStroke(Color.BLACK);
		return cellShape;
	}
	
	protected abstract Color getColor();
	
	protected void setColor(Color color){
		this.cellShape.setFill(color);
	}
	
	protected T getValue() {
		return this.currentValue;		
	}
	
	protected Shape getCellShape(){
		return this.cellShape;
	}

}
