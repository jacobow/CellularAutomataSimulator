package cellsociety_team11.segregation;

import java.util.ArrayList;
import java.util.Random;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SegregationRules implements Rule<Integer>{

	public static final int EMPTY = 0;

	private double threshold;

	/**
	 * sets an instance of segregation rules at a certain threshold
	 * @param threshold
	 * 			a parameter that determines the tolerance of agents
	 */
	SegregationRules(double threshold) {
		this.threshold = threshold;
	}
	/**
	 * returns the value of a cell based on its alike neighbors.
	 * If there aren't enough alike neighbors to surpass a threshold,
	 * then a random empty cell is filled with the value.
	 */
	@Override
	public Integer calculateNewValue(Cell<Integer> cell, Integer value, Grid<Integer> grid, Coordinates coordinates) {
		if(value == EMPTY) return value;
		double valueProportion = 0;
		double neighborSize = ((SegregationCell)cell).getNeighbors().size();
		for(SegregationCell agent : ((SegregationCell)cell).getNeighbors()) {
			if(agent.getValue() == value) valueProportion += 1/neighborSize;
		}
		if(valueProportion < threshold) {
			fillRandomEmptyCell(value, grid);
			return EMPTY;
		}
		return value;
	}

	//fills a random empty cell with a specified value
	private void fillRandomEmptyCell(int value, Grid<Integer> grid) {
		Random r = new Random();
		ArrayList<SegregationCell> emptyCells = new ArrayList<SegregationCell>();
		for(int i = 0; i < grid.getHeight(); i++) {
			for(int j = 0; j < grid.getWidth(); j++) {
				if(grid.getGridMatrix()[i][j].getValue() == EMPTY &&
				   grid.getGridMatrix()[i][j].getNewValue() == null) {
					emptyCells.add((SegregationCell) grid.getGridMatrix()[i][j]);
				}
			}
		}
		emptyCells.get(r.nextInt()).setNewValue(value);
	}

}
