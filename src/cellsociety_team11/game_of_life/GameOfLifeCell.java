package cellsociety_team11.game_of_life;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class GameOfLifeCell extends Cell<Boolean>{

	/**
	 * constructs a simple cell for Conway's Game of Life.  Cells are
	 * Boolean valued because Cells only exist in two states (dead or alive)
	 */
	public GameOfLifeCell(Boolean value, Rule<Boolean> rule, Coordinates coordinates, Grid<Boolean> grid) {
		super(value, rule, coordinates, grid);
	}

}
