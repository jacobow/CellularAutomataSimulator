package cellsociety_team11;

import java.util.HashSet;

public class GameOfLifeRules implements Rule<Boolean>{


	/**
	 * calculates a new value based based on a current value and the surrounding neighbors
	 * using the rules of Conway's Game Of Life
	 */
	@Override
	public Boolean calculateNewValue(Boolean currentValue, Grid<Boolean> grid, Coordinates coordinates) {
		int lifeCount = 0;
		for(GameOfLifeCell cell : getNeighbors(coordinates, grid)) {
			if(cell.getValue()) lifeCount++;
		}
		if(currentValue && lifeCount > 1 && lifeCount < 4) {
			return true;
		}
		if(!currentValue && lifeCount == 3) {
			return true;
		}
		else return false;
	}

	/**
	 *retrieves the neighbors of a cell's coordinates and returns them in a HashSet
	 */
	private HashSet<GameOfLifeCell> getNeighbors(Coordinates coordinates, Grid<Boolean> grid) {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		HashSet<GameOfLifeCell> neighbors = new HashSet<GameOfLifeCell>();
		for(int y = -1; i <= 1; y++) {
			for(int x = -1; j <= 1; x++) {
				if(y == 0 && x == 0) {
					continue;
				}
				if(0 < i+y || i+y >= grid.getHeight() || 0 < j+x || j+x >= grid.getWidth()) {
					neighbors.add(new GameOfLifeCell(false, null, null, null));
				}
				else {
					neighbors.add((GameOfLifeCell) grid.getCell(new Coordinates(i+y, j+x)));
				}
			}
		}
		return neighbors;
	}

}
