package cellsociety_team11.segregation;

import java.util.ArrayList;
import java.util.HashSet;
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
		double neighborSize = getNeighbors(coordinates, grid).size();
		for(SegregationCell agent : getNeighbors(coordinates, grid)) {
			if(agent.getValue() == value) valueProportion += 1/neighborSize;
		}
		if(valueProportion < threshold) {
			fillRandomEmptyCell(value, grid);
			return EMPTY;
		}
		return value;
	}
	//finds the potentially 6 neighbors of a coordinate on a grid.  Edge coordinates are
	//given empty dummy cells as some neighbors
	private HashSet<SegregationCell> getNeighbors(Coordinates coordinates, Grid<Integer> grid) {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		HashSet<SegregationCell> neighbors = new HashSet<SegregationCell>();
		for(int y = -1; i <= 1; y++) {
			for(int x = -1; j <= 1; x++) {
				if(y == 0 && x == 0) {
					continue;
				}
				if(0 > i+y || i+y >= grid.getHeight() || 0 > j+x || j+x >= grid.getWidth()) {
					neighbors.add(new SegregationCell(EMPTY, null, null));
				}
				else {
					neighbors.add((SegregationCell) grid.getCell(new Coordinates(i+y, j+x)));
				}
			}
		}
		return neighbors;
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
