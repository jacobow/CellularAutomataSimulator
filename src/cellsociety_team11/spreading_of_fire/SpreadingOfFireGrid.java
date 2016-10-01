package cellsociety_team11.spreading_of_fire;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class SpreadingOfFireGrid extends Grid<Integer>{

	public static final int EMPTY = 0;

	private String shape;
	private double probCatch;

	/**
	 *creates a grid for the spreading of fire simulation
	 */
	public SpreadingOfFireGrid(Integer[][] valueGrid, double probCatch, String shape, String world) {
		super(valueGrid, new SpreadingOfFireRules(probCatch), shape, world);
		this.probCatch = probCatch;
	}

	/**
	 * creates a new cell for grid initialization
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates, String shape) {
		return new SpreadingOfFireCell(value, coordinates, this, shape);
	}

	@Override
	public Cell<Integer> getEmptyCell() {
		return new SpreadingOfFireCell(EMPTY, null, this, shape);
	}

	public double getProbCatch() {
		return probCatch;
	}

	public void setProbCatch(double probCatch) {
		this.probCatch = probCatch;
		((SpreadingOfFireRules)this.getRule()).setProbCatch(probCatch);
	}

}
