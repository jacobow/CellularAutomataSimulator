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
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates) {
		return new SegregationCell(value, coordinates, this);
	}




	

}
