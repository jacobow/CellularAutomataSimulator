package cellsociety_team11.game_of_life;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class GameOfLifeGrid extends Grid<Boolean>{

	private String shape;

	/**
	 * constructs a Grid of Boolean-valued cells, since Conway's Game of Life only gives cells
	 * two states in which they can be
	 */
	public GameOfLifeGrid(Boolean[][] valueMatrix, String shape, String world) {
		super(valueMatrix, new GameOfLifeRules(), shape, world);
		this.shape = shape;
	}

	/**
	 * creates a new empty (dead) cell
	 */
	@Override
	public Cell<Boolean> createNewCell(Boolean value, Coordinates coordinates, String shape) {
		return new GameOfLifeCell(value, coordinates, this, shape);
	}

	@Override
	public Cell<Boolean> getEmptyCell() {
		return new GameOfLifeCell(false, null, this, shape);
	}

}
