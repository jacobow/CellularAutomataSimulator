package cellsociety_team11.spreading_of_fire;

import java.util.HashSet;
import java.util.Random;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class SpreadingOfFireRules implements Rule<Integer>{

	public static final int EMPTY = 0;
	public static final int TREE = 1;
	public static final int BURNING = 2;

	private int probCatch;

	/**
	 * returns new value based on its neighbors.  Burning neighbors have a set probablity of causing the value
	 * to be burning as well
	 */
	@Override
	public Integer calculateNewValue(Cell<Integer> cell, Integer value, Grid<Integer> grid, Coordinates coordinates) {
		Random r = new Random();
		if(value == BURNING) return EMPTY;
		for(SpreadingOfFireCell land : getNeighbors(coordinates, grid)) {
			if(value == TREE &&
			   land.getValue() == BURNING &&
			   r.nextDouble() < probCatch) {
				return BURNING;
			}
		}
		return value;
	}

	public void setProbCatch(int probCatch) {
		this.probCatch = probCatch;
	}

	//returns the potentially 4 adjacent neighbors of a coordinate.  Edge coordinates
	//are given some empty dummy cells for neighbors
	private HashSet<SpreadingOfFireCell> getNeighbors(Coordinates coordinates, Grid<Integer> grid) {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		HashSet<SpreadingOfFireCell> neighbors = new HashSet<SpreadingOfFireCell>();
		for(int y = -1; i <= 1; y++) {
			for(int x = -1; j <= 1; x++) {
				if(y == x) {
					continue;
				}
				if(0 > i+y || i+y >= grid.getHeight() || 0 > j+x || j+x >= grid.getWidth()) {
					neighbors.add(new SpreadingOfFireCell(EMPTY, null, null, null));
				}
				else {
					neighbors.add((SpreadingOfFireCell) grid.getCell(new Coordinates(i+y, j+x)));
				}
			}
		}
		return neighbors;
	}

}
