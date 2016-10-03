package cellsociety_team11.predator_prey;

import java.util.ArrayList;
import java.util.Random;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class PredatorPreyRules implements Rule<Integer>{

	public static final int EMPTY = 0;
	public static final int PREDATOR = 1;
	public static final int PREY = 2;

	/**
	 * calculates a new value based on its neighboring cells.  In cases of breeding another cell is given this value,
	 * and in cases of energy completely consumed the predator or prey dies and receives an empty value
	 */
	@Override
	public Integer calculateNewValue(Cell<Integer> cell, Integer value, Grid<Integer> grid, Coordinates coordinates) {
		int newValue = value;
		if(value == PREY) {
			newValue = movePrey((PredatorPreyCell) cell, grid, coordinates);
		}
		if(value == PREDATOR) {
			newValue = movePredator((PredatorPreyCell) cell, grid, coordinates);
		}
		return newValue;
	}

	//moves a predator and determines if it must die or breed
	private Integer movePredator(PredatorPreyCell cell, Grid<Integer> grid, Coordinates coordinates) {
		cell.tickDeathTimer();
		cell.tickBreedingTimer();
		if(cell.getDeathTimer() <= 0) return EMPTY;
		ArrayList<Cell<Integer>> neighbors = ((PredatorPreyCell)cell).getNeighbors();
		for(Cell<Integer> neighborCell : neighbors) {
			PredatorPreyCell neighbor = (PredatorPreyCell) neighborCell;
			if(neighbor.getValue() == PREY &&
			  (neighbor.getNewValue() == null || neighbor.getNewValue() == PREY)) {
				cell.upTickDeathTimer();
				return swap(cell, neighbor);
			}
		}
		for(Cell<Integer> nCell : neighbors) {
			PredatorPreyCell n = (PredatorPreyCell) nCell;
			if(n.getValue() == EMPTY &&
			  (n.getNewValue() == null || n.getNewValue() == EMPTY) &&
			  n.getCoordinates() != null) {
				return swap(cell, n);
			}
		}
		if(!getEmptyCells(grid).isEmpty()) {
			fillRandomEmptyCell(PREDATOR, cell, grid);
			return EMPTY;
		}
		return PREDATOR;
	}

	//swaps two cells
	private Integer swap(PredatorPreyCell cell, PredatorPreyCell neighbor) {
		neighbor.setNewValue(cell.getValue());
		if(cell.getValue() == PREDATOR) {
			neighbor.setLifeSpan(cell.getLifeSpan());
			neighbor.setDeathTimer(cell.getDeathTimer());
		}
		neighbor.setBreedingSpan(cell.getBreedingSpan());
		neighbor.setBreedingTimer(cell.getBreedingTimer());
		if(cell.getBreedingTimer() <= 0) {
			if(cell.getValue() == PREDATOR) cell.resetDeathTimer();
			cell.resetBreedingTimer();
			neighbor.resetBreedingTimer();
			return cell.getValue();
		}
		return EMPTY;
	}

	//moves prey and determines if it must die or breed
	private Integer movePrey(PredatorPreyCell cell, Grid<Integer> grid, Coordinates coordinates) {
		cell.tickBreedingTimer();
		ArrayList<Cell<Integer>> neighbors = ((PredatorPreyCell)cell).getNeighbors();
		for(Cell<Integer> neighborCell : neighbors) {
			PredatorPreyCell neighbor = (PredatorPreyCell) neighborCell;
			if(neighbor.getValue() == EMPTY &&
			  (neighbor.getNewValue() == null || neighbor.getNewValue() == EMPTY) &&
			  neighbor.getCoordinates() != null) {
				return swap(cell, neighbor);
			}
		}
		return PREY;
	}

	//returns the empty cells of a grid
	private ArrayList<PredatorPreyCell> getEmptyCells(Grid<Integer> grid) {
		ArrayList<PredatorPreyCell> emptyCells = new ArrayList<PredatorPreyCell>();
		for(int i = 0; i < grid.getHeight(); i++) {
			for(int j = 0; j < grid.getWidth(); j++) {
				if(grid.getGridMatrix()[i][j].getValue() == EMPTY &&
				  (grid.getGridMatrix()[i][j].getNewValue() == null || grid.getGridMatrix()[i][j].getNewValue() == EMPTY)) {
					emptyCells.add((PredatorPreyCell) grid.getGridMatrix()[i][j]);
				}
			}
		}
		return emptyCells;
	}

	//fills a random empty cell's new value
	private void fillRandomEmptyCell(int value, PredatorPreyCell cell, Grid<Integer> grid) {
		Random r = new Random();
		PredatorPreyCell randCell = getEmptyCells(grid).get(r.nextInt(getEmptyCells(grid).size()));
		swap(cell, randCell);
	}
}
