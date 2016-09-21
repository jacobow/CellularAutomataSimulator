package cellsociety_team11.gui;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author quin
 *
 */
public class DisplayCell extends Pane{

	public DisplayCell(){
		super();
		Rectangle cellRectangle = new Rectangle();
		cellRectangle.widthProperty().bind(this.widthProperty());
		cellRectangle.heightProperty().bind(this.heightProperty());
		cellRectangle.setFill(Color.WHITE);
		cellRectangle.setStroke(Color.BLACK);
		this.getChildren().add(cellRectangle);
	}
}
