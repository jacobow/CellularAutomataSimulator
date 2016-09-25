package cellsociety_team11.gui.display_cell_types;

import cellsociety_team11.Coordinates;
import cellsociety_team11.gui.SquareDisplayCell;
import javafx.scene.paint.Color;

public class SpreadingOfFireDisplayCell extends SquareDisplayCell<Integer>{

	public SpreadingOfFireDisplayCell(Integer treeStatus, Coordinates coordinates) {
		// TODO Auto-generated constructor stub
		super(treeStatus, coordinates);
	}
	
	@Override
	protected Color getColor() {
		if (currentValue.equals(2)){
			return Color.RED;
		}
		else if(currentValue.equals(1)){
			return Color.GREEN;
		}
		else{
			return Color.GRAY;
		}
	}

}
