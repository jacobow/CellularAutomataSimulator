package cellsociety_team11.predator_prey;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;
import xml.model.SimulationXMLModel;

public class PredatorPreyGrid extends Grid<Integer> {

	

	public static final int EMPTY = 0;
	public static final int PREDATOR = 1;
	public static final int PREY = 2;

	private int preyBreedingSpan;
	private int predatorBreedingSpan;
	private int predatorLifeSpan;

	public PredatorPreyGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Rule<Integer> createRule(SimulationXMLModel simulation) {
		this.predatorBreedingSpan = simulation.getPredatorBreedingSpan();
		this.preyBreedingSpan = simulation.getPreyBreedingSpan();
		this.predatorLifeSpan = simulation.getPredatorLifeSpan();
		setTimers(this.predatorLifeSpan, this.preyBreedingSpan, this.predatorBreedingSpan);
		return new PredatorPreyRules();
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
