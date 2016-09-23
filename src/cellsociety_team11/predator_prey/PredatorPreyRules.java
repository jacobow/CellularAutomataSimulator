package cellsociety_team11.predator_prey;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class PredatorPreyRules implements Rule<Integer>{

	public static final int EMPTY = 0;
	public static final int PREDATOR = 1;
	public static final int PREY = 2;

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

	private Integer movePredator(PredatorPreyCell cell, Grid<Integer> grid, Coordinates coordinates) {
		boolean needsToBreed = false;
		if(cell.getDeathTimer() <= 0) return EMPTY;
		cell.tickDeathTimer();
		cell.tickBreedingTimer();
		if(cell.getBreedingTimer() <= 0) needsToBreed = true;
		HashSet<PredatorPreyCell> neighbors = getNeighbors(coordinates, grid);
		for(PredatorPreyCell neighbor : neighbors) {
			if(neighbor.getValue() == PREY && neighbor.getNewValue() == null) {
				cell.upTickDeathTimer();
				neighbor.setNewValue(PREDATOR);
				neighbor.setDeathTimer(cell.getDeathTimer());
				neighbor.setBreedingTimer(cell.getBreedingTimer());
				if(needsToBreed) {
					cell.resetDeathTimer();
					cell.resetBreedingTimer();
					return PREDATOR;
				}
				return EMPTY;
			}
		}
		for(PredatorPreyCell n : neighbors) {
			if(n.getValue() == EMPTY && n.getNewValue() == null) {
				n.setNewValue(PREDATOR);
				n.setDeathTimer(cell.getDeathTimer());
				n.setBreedingTimer(cell.getBreedingTimer());
				if(needsToBreed) {
					cell.resetDeathTimer();
					cell.resetBreedingTimer();
					return PREDATOR;
				}
				return EMPTY;
			}
		}
		if(!getEmptyCells(grid).isEmpty()) {
			fillRandomEmptyCell(PREDATOR, cell, grid);
			return EMPTY;
		}
		return PREDATOR;
	}

	private Integer movePrey(PredatorPreyCell cell, Grid<Integer> grid, Coordinates coordinates) {
		boolean needsToBreed = false;
		cell.tickBreedingTimer();
		if(cell.getBreedingTimer() <= 0) needsToBreed = true;
		HashSet<PredatorPreyCell> neighbors = getNeighbors(coordinates, grid);
		for(PredatorPreyCell neighbor : neighbors) {
			if(neighbor.getValue() == EMPTY && neighbor.getNewValue() == null) {
				neighbor.setNewValue(PREY);
				neighbor.setBreedingTimer(cell.getBreedingTimer());
				if(needsToBreed) {
					cell.resetBreedingTimer();
					return PREY;
				}
				return EMPTY;
			}
		}
		return PREY;
	}

	private HashSet<PredatorPreyCell> getNeighbors(Coordinates coordinates, Grid<Integer> grid) {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		HashSet<PredatorPreyCell> neighbors = new HashSet<PredatorPreyCell>();
		for(int y = -1; i <= 1; y++) {
			for(int x = -1; j <= 1; x++) {
				if(y == x) {
					continue;
				}
				if(0 > i+y || i+y >= grid.getHeight() || 0 > j+x || j+x >= grid.getWidth()) {
					neighbors.add(new PredatorPreyCell(EMPTY, null, null, null));
				}
				else {
					neighbors.add((PredatorPreyCell) grid.getCell(new Coordinates(i+y, j+x)));
				}
			}
		}
		return neighbors;
	}

	private ArrayList<PredatorPreyCell> getEmptyCells(Grid<Integer> grid) {
		ArrayList<PredatorPreyCell> emptyCells = new ArrayList<PredatorPreyCell>();
		for(int i = 0; i < grid.getHeight(); i++) {
			for(int j = 0; j < grid.getWidth(); j++) {
				if(grid.getGridMatrix()[i][j].getValue() == EMPTY &&
				   grid.getGridMatrix()[i][j].getNewValue() == null) {
					emptyCells.add((PredatorPreyCell) grid.getGridMatrix()[i][j]);
				}
			}
		}
		return emptyCells;
	}

	private void fillRandomEmptyCell(int value, PredatorPreyCell cell, Grid<Integer> grid) {
		Random r = new Random();
		PredatorPreyCell randCell = getEmptyCells(grid).get(r.nextInt());
		randCell.setNewValue(value);
		randCell.setDeathTimer(cell.getDeathTimer());
		randCell.setBreedingTimer(cell.getBreedingTimer());
	}
}
