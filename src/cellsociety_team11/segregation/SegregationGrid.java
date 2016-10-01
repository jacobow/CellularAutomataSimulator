package cellsociety_team11.segregation;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;
import xml.model.SimulationXMLModel;

public class SegregationGrid extends Grid<Integer>{

	
	public static final int EMPTY = 0;

	public SegregationGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
	}
	
	@Override
	protected Rule<Integer> createRule(SimulationXMLModel simulation) {
		return new SegregationRules(simulation.getProbability());
	}
	/**
	 * creates a new cell in the grid
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates, String shape) {
		return new SegregationCell(value, coordinates, this, shape);
	}

	@Override
	public Cell<Integer> getEmptyCell() {
		return new SegregationCell(EMPTY, null, this, shape);
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
		((SegregationRules)this.getRule()).setThreshold(threshold);
	}




	

}