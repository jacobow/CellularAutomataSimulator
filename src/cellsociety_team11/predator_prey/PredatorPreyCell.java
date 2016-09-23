package cellsociety_team11.predator_prey;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;

public class PredatorPreyCell extends Cell<Integer>{

	private int breedingTimer;
	private int breedingSpan;
	private int deathTimer;
	private int lifeSpan;

	public PredatorPreyCell(Integer value, Rule<Integer> rule, Coordinates coordinates, Grid<Integer> grid) {
		super(value, rule, coordinates, grid);
	}

	public int getBreedingTimer() {
		return breedingTimer;
	}

	public void setBreedingTimer(int time) {
		deathTimer = time;
	}

	public void setBreedingSpan(int time) {
		breedingSpan = time;
	}

	public void resetBreedingTimer() {
		breedingTimer = breedingSpan;
	}

	public void tickBreedingTimer() {
		breedingTimer--;
	}

	public int getDeathTimer() {
		return deathTimer;
	}

	public void setDeathTimer(int time) {
		deathTimer = time;
	}

	public void setLifeSpan(int time) {
		lifeSpan = time;
	}

	public void resetDeathTimer() {
		deathTimer = lifeSpan;
	}

	public void tickDeathTimer() {
		deathTimer--;
	}

	public void upTickDeathTimer() {
		deathTimer++;
	}
}
