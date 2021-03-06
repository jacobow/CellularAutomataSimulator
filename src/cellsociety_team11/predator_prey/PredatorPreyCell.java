package cellsociety_team11.predator_prey;


import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class PredatorPreyCell extends Cell<Integer> {

	private int breedingTimer;
	private int breedingSpan;
	private int deathTimer;
	private int lifeSpan;

	/**
	 * creates a new predator-prey cell
	 */
	public PredatorPreyCell(Integer value, Coordinates coordinates, Grid<Integer> grid, int shape) {
		super(value, coordinates, grid, shape);
	}
	/**
	 * gets the timer that tracks when a cell should breed
	 */
	public int getBreedingTimer() {
		return breedingTimer;
	}

	/**
	 * sets the timer for when the cell should breed
	 */
	public void setBreedingTimer(int time) {
		breedingTimer = time;
	}

	/**
	 * sets the time it takes the breeding timer to end
	 */
	public void setBreedingSpan(int time) {
		breedingSpan = time;
	}

	/**
	 * gets the time it takes the breeding timer to end
	 */
	public int getBreedingSpan() {
		return breedingSpan;
	}

	/**
	 * resets the breeding timer to full time
	 */
	public void resetBreedingTimer() {
		breedingTimer = breedingSpan;
	}

	/**
	 * ticks the breeding timer
	 */
	public void tickBreedingTimer() {
		breedingTimer--;
	}

	/**
	 * gets the timer for when the cell should die
	 */
	public int getDeathTimer() {
		return deathTimer;
	}

	/**
	 * sets the timer for when the cell should die
	 */
	public void setDeathTimer(int time) {
		deathTimer = time;
	}

	/**
	 * sets the time it takes the death timer to end
	 */
	public void setLifeSpan(int time) {
		lifeSpan = time;
	}

	/**
	 * gets the time it takes the death timer to end
	 */
	public int getLifeSpan() {
		return lifeSpan;
	}

	/**
	 * resets the death timer to full time
	 */
	public void resetDeathTimer() {
		deathTimer = lifeSpan;
	}

	/**
	 * ticks the death timer
	 */
	public void tickDeathTimer() {
		deathTimer--;
	}

	/**
	 * adds a second to the death timer
	 */
	public void upTickDeathTimer() {
		deathTimer++;
	}
}
