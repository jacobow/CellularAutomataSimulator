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
		this.predatorLifeSpan = predatorLifeSpan;
		this.preyBreedingSpan = preyBreedingSpan;
		this.predatorBreedingSpan = predatorBreedingSpan;
	}

	/**
	 * creates a new cell in the grid and initializes its timers if needed
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates) {
		PredatorPreyCell cell = new PredatorPreyCell(value, coordinates, this);
		if(value == PREY) cell.setBreedingSpan(preyBreedingSpan);
		if(value == PREDATOR) {
			cell.setBreedingSpan(predatorBreedingSpan);
			cell.setLifeSpan(predatorLifeSpan);
		}
		return cell;
	}

}
