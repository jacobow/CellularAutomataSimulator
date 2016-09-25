package cellsociety_team11.gui.display_cell_types;

import cellsociety_team11.Coordinates;
import cellsociety_team11.gui.SquareDisplayCell;
import javafx.scene.paint.Color;

public class SegregationDisplayCell extends SquareDisplayCell<Integer>{

	public SegregationDisplayCell(Integer race, Coordinates coordinates) {
		// TODO Auto-generated constructor stub
		super(race, coordinates);
	}
	
	@Override
	protected Color getColor() {
		if (currentValue.equals(2)){
			return Color.BLACK;
		}
		else if(currentValue.equals(1)){
			return Color.WHITE;
		}
		else{
			return Color.GRAY;
		}
	}

}
