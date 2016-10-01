package cellsociety_team11.spreading_of_fire;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;
import xml.model.SimulationXMLModel;

/*
 * @author Jacob Warner
 * @author Cleveland Thompson
 */
public class SpreadingOfFireGrid extends Grid<Integer>{

	public static final int EMPTY = 0;	
	
	public SpreadingOfFireGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
	}

	/*
	 * (non-Javadoc)
	 * @see cellsociety_team11.Grid#createRule(xml.model.SimulationXMLModel)
	 */
	@Override
	protected Rule<Integer> createRule(SimulationXMLModel simulation) {
		return new SpreadingOfFireRules(simulation.getProbability());
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