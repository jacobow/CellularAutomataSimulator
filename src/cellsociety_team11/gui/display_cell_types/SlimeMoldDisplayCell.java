package cellsociety_team11.gui.display_cell_types;

import cellsociety_team11.Coordinates;
import cellsociety_team11.gui.DisplayCell;
import javafx.scene.paint.Color;

public class SlimeMoldDisplayCell extends DisplayCell<Integer>{

	public SlimeMoldDisplayCell(Integer race, Coordinates coordinates, double width, int numSides) {
		super(race, coordinates, width, numSides);
	}
	

	@Override
	protected Color getColor() {
		if (this.getValue().equals(1)){
			return Color.GREEN;
		}
		else{
			return Color.GRAY;
		}
	}

}
