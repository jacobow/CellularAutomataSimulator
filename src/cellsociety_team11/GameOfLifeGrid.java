package cellsociety_team11;

public class GameOfLifeGrid extends Grid<Boolean>{

	/**
	 * constructs a Grid of Boolean-valued cells, since Conway's Game of Life only gives cells
	 * two states in which they can be
	 */
	public GameOfLifeGrid(int height, int length, Rule<Boolean> rule) {
		super(height, length, rule);
	}

	/**
	 * creates a new empty (dead) cell
	 */
	@Override
	public Cell<Boolean> createNewCell(Coordinates coordinates, Rule<Boolean> rule) {
		return new GameOfLifeCell(false, rule, coordinates, this);
	}

}
