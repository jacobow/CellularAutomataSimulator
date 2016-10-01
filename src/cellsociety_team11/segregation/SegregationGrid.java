package cellsociety_team11.segregation;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.predator_prey.PredatorPreyCell;

public class SegregationGrid extends Grid<Integer>{

	public static final int EMPTY = 0;

	private String shape;
	private double threshold;

	/**
	 * creates a new segregation grid
	 * @param valueGrid
	 * 				grid of the values of the cells
	 * @param theshold
	 * 				a parameter that determines the tolerance of agents
	 */
	public SegregationGrid(Integer[][] valueGrid, double theshold, String shape, String world) {
		super(valueGrid, new SegregationRules(theshold), shape, world);
		this.shape = shape;
		this.threshold = threshold;
	}

	/**
	 * creates a new cell in the grid
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates, String shape) {
		return new SegregationCell(value, coordinates, this, shape);
	}

	@Override
	public Cell<Integer> getEmptyCell() {
		return new SegregationCell(EMPTY, null, this, shape);
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
		((SegregationRules)this.getRule()).setThreshold(threshold);
	}

}
