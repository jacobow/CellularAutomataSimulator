package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author quin
 *
 */
public class DisplayCell extends Pane{
	private boolean alive;
	private Rectangle cellRectangle;
	private Coordinates coordinates;
	
	public DisplayCell(boolean alive, Coordinates coordinates){
		super();
		this.alive = alive;
		this.coordinates = coordinates;
		initRectangle();
		this.getChildren().add(cellRectangle);
		//this.setOnMouseClicked(e -> handleCellClicked(e));
	}
	
	public void updateAlive(boolean newAlive){
		this.alive = newAlive;
		setColor(getColor());
	}
	
	public Coordinates getCoordinates(){
		return this.coordinates;
	}
	
	public boolean getValue(){
		return alive;
	}
	
	private void initRectangle(){
		cellRectangle = new Rectangle();
		cellRectangle.widthProperty().bind(this.widthProperty());
		cellRectangle.heightProperty().bind(this.heightProperty());
		setColor(getColor());
		cellRectangle.setStroke(Color.BLACK);
	}
	
	private Color getColor(){
		if (alive){
			return Color.GREEN;
		}
		return Color.BLUE;
	}
	
	
	private void setColor(Color color){
		cellRectangle.setFill(color);
	}
	
	/*private void handleCellClicked(MouseEvent mouseEvent){
		this.alive = !this.alive;
		setColor(getColor());
	}*/
}
