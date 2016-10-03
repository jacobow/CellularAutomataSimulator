package cellsociety_team11.gui.display_cell_types.square_display_cells;

import cellsociety_team11.Coordinates;
import cellsociety_team11.gui.DisplayCell;
import cellsociety_team11.gui.display_cell_types.SquareDisplayCell;
import javafx.scene.paint.Color;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Display Cell For Predator Prey Simulation
 */
public class PredatorPreyDisplayCell extends DisplayCell<Integer>{

	public PredatorPreyDisplayCell(Integer cellType, Coordinates coordinates, double width, int numSides) {
		super(cellType, coordinates, width, numSides);
	}
	
	@Override
	protected Color getColor() {
		if (this.getValue().equals(2)){
			return Color.YELLOW;
		}
		else if(this.getValue().equals(1)){
			return Color.RED;
		}
		else{
			return Color.GRAY;
		}
	}

}
