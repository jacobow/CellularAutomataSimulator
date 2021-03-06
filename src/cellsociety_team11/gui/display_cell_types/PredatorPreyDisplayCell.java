// This entire file is part of my masterpiece.
// Cleveland Thompson V (ct168)


package cellsociety_team11.gui.display_cell_types;

import cellsociety_team11.Coordinates;
import cellsociety_team11.gui.DisplayCell;
import javafx.scene.paint.Color;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Display Cell For Predator Prey Simulation
 * Example of Extension of DisplayCell (hence why I'm including in masterpiece)
 * Actual Discussion in DisplayCell
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
