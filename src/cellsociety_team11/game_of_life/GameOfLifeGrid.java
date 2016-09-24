package cellsociety_team11.game_of_life;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class GameOfLifeGrid extends Grid<Boolean>{

	/**
	 * constructs a Grid of Boolean-valued cells, since Conway's Game of Life only gives cells
	 * two states in which they can be
	 */
	public GameOfLifeGrid(Boolean[][] valueMatrix) {
		super(valueMatrix, new GameOfLifeRules());
	}

	/**
	 * creates a new empty (dead) cell
	 */
	@Override
	public Cell<Boolean> createNewCell(Boolean value, Coordinates coordinates) {
		return new GameOfLifeCell(false, coordinates, this);
	}

}
