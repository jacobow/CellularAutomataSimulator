package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public abstract class SquareDisplayCell<T> extends DisplayCell<T>{

	public SquareDisplayCell(T value, Coordinates coordinates) {
		super(value, coordinates);
	}
	@Override
	protected Shape initCell() {
		Rectangle cellRectangle = new Rectangle();
		cellRectangle.widthProperty().bind(this.widthProperty());
		cellRectangle.heightProperty().bind(this.heightProperty());
		cellRectangle.setStroke(Color.BLACK);
		return cellRectangle;
	}

	@Override
	protected abstract Color getColor();

}
