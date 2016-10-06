// This entire file is part of my masterpiece.
// Cleveland Thompson V (ct168)

package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Highest Level of Abstraction for displaying the representation of each cell in the grid.
 * I think that this class is well designed because even though it represents the cell in the display,
 * it outsources the math-heavy operations of defining the shape of the cell and moving the cell, both of which are dependant on the number of sides--to cellShape.
 * So, changing the rules for the orientation and shape of the cell can be updated in CellShape, preventing tampering with the central DisplayCell Class.
 * Originally I had the management of the shape (polygon) in this class and the orientation management in DisplayGrid, but the DisplayCell has to have access
 * to its coordinates, width, and number of sides anyway, so it made more sense to encapsulate the management of the actual shape of the cell in a separate class (CellShape).
 * 
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
