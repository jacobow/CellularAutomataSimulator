package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author quin
 *
 */
public class GameOfLifeDisplayCell extends SquareDisplayCell<Boolean>{
	
	public GameOfLifeDisplayCell(Boolean alive, Coordinates coordinates){
		super(alive, coordinates);
	}

	@Override
	protected Color getColor() {
		if (currentValue){
			return Color.GREEN;
		}
		return Color.BLUE;
	}
}
