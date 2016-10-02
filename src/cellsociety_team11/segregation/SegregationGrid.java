package cellsociety_team11.segregation;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;
import xml.factory.XMLFactoryException;
import xml.model.SimulationXMLModel;

public class SegregationGrid extends Grid<Integer> {

	public static final int EMPTY = 0;

	private int shape;
	private double threshold;

	public SegregationGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
		try {
		    shape = simulation.getShape();
		    threshold = simulation.getProbability();
		} catch (XMLFactoryException e) {
		    e.printStackTrace();
		}
	}

	@Override
	protected Rule<Integer> createRule(SimulationXMLModel simulation) {
		try {
	        return new SegregationRules(simulation.getProbability());
	    } catch (XMLFactoryException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	/**
	 * creates a new cell in the grid
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates, int shape) {
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