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
	private String shape;

	public PredatorPreyGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
		preyBreedingSpan = simulation.getPreyBreedingSpan();
		predatorBreedingSpan = simulation.getPredatorBreedingSpan();
		predatorLifeSpan = simulation.getPredatorLifeSpan();
		setTimers(predatorLifeSpan, preyBreedingSpan, predatorBreedingSpan);
		shape = simulation.getShape();
	}

	@Override
	protected Rule<Integer> createRule(SimulationXMLModel simulation) {
		return new PredatorPreyRules();
	}

	/**
	 * creates a new cell in the grid and initializes its timers if needed
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates, String shape) {
		return new PredatorPreyCell(value, coordinates, this, shape);
	}

	private void setTimers(int predatorLifeSpan, int preyBreedingSpan, int predatorBreedingSpan) {
		for(int i = 0; i < this.getHeight(); i++) {
			for(int j = 0; j < this.getWidth(); j++) {
				PredatorPreyCell cell = (PredatorPreyCell) this.getGridMatrix()[i][j];
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
	/**
	 * gets the prey breeding span parameter
	 */
	public int getPreyBreedingSpan() {
		return preyBreedingSpan;
	}
	/**
	 * sets the prey breeding span parameter
	 */
	public void setPreyBreedingSpan(int preyBreedingSpan) {
		this.preyBreedingSpan = preyBreedingSpan;
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				((PredatorPreyCell)getGridMatrix()[i][j]).setLifeSpan(preyBreedingSpan);
			}
		}
	}
	/**
	 * gets the predator breeding span parameter
	 */
	public int getPredatorBreedingSpan() {
		return predatorBreedingSpan;
	}
	/**
	 * sets the predator breeding span parameter
	 */
	public void setPredatorBreedingSpan(int predatorBreedingSpan) {
		this.predatorBreedingSpan = predatorBreedingSpan;
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				((PredatorPreyCell)getGridMatrix()[i][j]).setLifeSpan(predatorBreedingSpan);
			}
		}
	}
	/**
	 * gets the predator life span parameter
	 */
	public int getPredatorLifeSpan() {
		return predatorLifeSpan;
	}
	/**
	 * sets the predator life span parameter
	 */
	public void setPredatorLifeSpan(int predatorLifeSpan) {
		this.predatorLifeSpan = predatorLifeSpan;
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				((PredatorPreyCell)getGridMatrix()[i][j]).setLifeSpan(predatorLifeSpan);
			}
		}
	}

	@Override
	public Cell<Integer> getEmptyCell() {
		return new PredatorPreyCell(EMPTY, null, this, shape);
	}




}