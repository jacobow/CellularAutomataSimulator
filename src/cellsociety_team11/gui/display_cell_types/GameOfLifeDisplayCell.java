package cellsociety_team11.gui.display_cell_types;

import cellsociety_team11.Coordinates;
import cellsociety_team11.gui.SquareDisplayCell;
import javafx.scene.paint.Color;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Display Cell For Game of Life Simulation
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