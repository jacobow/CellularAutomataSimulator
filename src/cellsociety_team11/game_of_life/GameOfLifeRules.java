package cellsociety_team11.game_of_life;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class GameOfLifeRules implements Rule<Boolean>{


	/**
	 * calculates a new value based based on a current value and the surrounding neighbors
	 * using the rules of Conway's Game Of Life
	 */
	@Override
	public Boolean calculateNewValue(Cell<Boolean> c, Boolean currentValue, Grid<Boolean> grid, Coordinates coordinates) {
		int lifeCount = 0;
		for(GameOfLifeCell cell : ((GameOfLifeCell)c).getNeighbors()) {
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

}
