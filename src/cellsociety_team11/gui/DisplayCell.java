package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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
		// TODO Auto-generated constructor stub
	}
	
	public T getValue(){
		return currentValue;
	}
	
	public void updateValue(T newValue){
		currentValue = newValue;
		setColor(getColor());
	}
	
	public Coordinates getCoordinates(){
		return this.coordinates;
	}
	
	protected abstract Shape initCell();
	
	protected abstract Color getColor();
	
	protected void setColor(Color color){
		cellShape.setFill(color);
	}

}
