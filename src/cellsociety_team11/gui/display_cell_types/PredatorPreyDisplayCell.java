package cellsociety_team11.gui.display_cell_types;

import cellsociety_team11.Coordinates;
import cellsociety_team11.gui.SquareDisplayCell;
import javafx.scene.paint.Color;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Display Cell For Predator Prey Simulation
 */
public class PredatorPreyDisplayCell extends SquareDisplayCell<Integer>{

	public PredatorPreyDisplayCell(Integer cellType, Coordinates coordinates) {
		super(cellType, coordinates);
	}
	
	@Override
	protected Color getColor() {
		if (currentValue.equals(2)){
			return Color.YELLOW;
		}
		else if(currentValue.equals(1)){
			return Color.RED;
		}
		else{
			return Color.GRAY;
		}
	}

}
