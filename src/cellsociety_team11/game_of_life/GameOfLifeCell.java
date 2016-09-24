package cellsociety_team11.game_of_life;

import java.util.HashSet;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class GameOfLifeCell extends Cell<Boolean>{

	/**
	 * constructs a simple cell for Conway's Game of Life.  Cells are
	 * Boolean valued because Cells only exist in two states (dead or alive)
	 */
	public GameOfLifeCell(Boolean value, Coordinates coordinates, Grid<Boolean> grid) {
		super(value, coordinates, grid);
	}

	public HashSet<GameOfLifeCell> getNeighbors() {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		HashSet<GameOfLifeCell> neighbors = new HashSet<GameOfLifeCell>();
		for(int y = -1; i <= 1; y++) {
			for(int x = -1; j <= 1; x++) {
				if(y == 0 && x == 0) {
					continue;
				}
				if(0 > i+y || i+y >= grid.getHeight() || 0 > j+x || j+x >= grid.getWidth()) {
					neighbors.add(new GameOfLifeCell(false, null, null));
				}
				else {
					neighbors.add((GameOfLifeCell) grid.getCell(new Coordinates(i+y, j+x)));
				}
			}
		}
		return neighbors;
	}
}
