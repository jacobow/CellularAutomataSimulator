package cellsociety_team11.predator_prey;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class PredatorPreyGrid extends Grid<Integer> {

	public static final int EMPTY = 0;
	public static final int PREDATOR = 1;
	public static final int PREY = 2;

	private int preyBreedingSpan;
	private int predatorBreedingSpan;
	private int predatorLifeSpan;

	/**
	 * creates a new predator-prey grid
	 * @param valueGrid
	 * 				sets the values of the cells in the grid
	 * @param predatorLifeSpan
	 * 				sets the lifeSpan of a predator
	 * @param predatorBreedingSpan
	 * 				sets the time until a predator breeds
	 * @param preyBreedingSpan
	 * 				sets the time until a prey breeds
	 */
	public PredatorPreyGrid(Integer[][] valueGrid, int predatorLifeSpan, int preyBreedingSpan, int predatorBreedingSpan) {
		super(valueGrid, new PredatorPreyRules());
		setTimers(predatorLifeSpan, preyBreedingSpan, predatorBreedingSpan);
	}

	/**
	 * creates a new cell in the grid and initializes its timers if needed
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates) {
		return new PredatorPreyCell(value, coordinates, this);
	}

	private void setTimers(int predatorLifeSpan, int preyBreedingSpan, int predatorBreedingSpan) {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				PredatorPreyCell cell = (PredatorPreyCell) gridMatrix[i][j];
				Integer value = cell.getValue();
				if(value == PREY) {
					cell.setBreedingSpan(preyBreedingSpan);
					cell.resetBreedingTimer();
				}
				if(value == PREDATOR) {
					cell.setBreedingSpan(predatorBreedingSpan);
					cell.resetBreedingTimer();
					cell.setLifeSpan(predatorLifeSpan);
					cell.resetDeathTimer();
				}
			}
		}
	}

}
