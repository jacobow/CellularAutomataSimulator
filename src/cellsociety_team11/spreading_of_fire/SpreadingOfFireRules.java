package cellsociety_team11.spreading_of_fire;

import java.util.Random;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SpreadingOfFireRules implements Rule<Integer>{

	public static final int EMPTY = 0;
	public static final int TREE = 1;
	public static final int BURNING = 2;

	private double probCatch;

	/**
	 * creates a new set of rules for a spreading of fire simulation
	 * @param probCatch
	 * 		the probability that a tree catches fire
	 */
	public SpreadingOfFireRules(double probCatch) {
		this.probCatch = probCatch;
	}

	/**
	 * returns new value based on its neighbors.  Burning neighbors have a set probablity of causing the value
	 * to be burning as well
	 */
	@Override
	public Integer calculateNewValue(Cell<Integer> cell, Integer value, Grid<Integer> grid, Coordinates coordinates) {
		Random r = new Random();
		if(value == BURNING) return EMPTY;
		for(Cell<Integer> land : ((SpreadingOfFireCell)cell).getNeighbors()) {
			if(value == TREE &&
			   land.getValue() == BURNING &&
			   r.nextDouble() < probCatch) {
				return BURNING;
			}
		}
		return value;
	}

	/**
	 * gets the probCatch parameter
	 */
	public double getProbCatch() {
		return probCatch;
	}

	/**
	 * sets the probCatch parameter
	 */
	public void setProbCatch(double probCatch) {
		this.probCatch = probCatch;
	}

}
