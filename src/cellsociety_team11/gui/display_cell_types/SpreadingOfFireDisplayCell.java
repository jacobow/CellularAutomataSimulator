package cellsociety_team11.gui.display_cell_types;

import cellsociety_team11.Coordinates;
import cellsociety_team11.gui.SquareDisplayCell;
import javafx.scene.paint.Color;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Display Cell For Spreading of Fire Simulation
 */
public class SpreadingOfFireDisplayCell extends SquareDisplayCell<Integer>{

	public SpreadingOfFireDisplayCell(Integer treeStatus, Coordinates coordinates) {
		// TODO Auto-generated constructor stub
		super(treeStatus, coordinates);
	}
	
	@Override
	protected Color getColor() {
		if (this.getValue().equals(2)){
			return Color.RED;
		}
		else if(this.getValue().equals(1)){
			return Color.GREEN;
		}
		else{
			return Color.GRAY;
		}
	}

}
