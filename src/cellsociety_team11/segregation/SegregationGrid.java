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

	/**
	 * creates new segregation grid
	 * @param valueGrid
	 * 			2d array of the different cell values
	 * @param simulation
	 * 			holds the parameters of the grid
	 */
	public SegregationGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
		try {
		    shape = simulation.getShape();
		    threshold = simulation.getProbability();
		} catch (XMLFactoryException e) {
		    e.printStackTrace();
		}
	}

	/**
	 * instantiates the rules for the segregation grid
	 */
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

	/**
	 * gets an empty cell for the finite grid
	 */
	@Override
	public Cell<Integer> getEmptyCell() {
		return new SegregationCell(EMPTY, null, this, shape);
	}

	/**
	 * gets the threshold parameter
	 */
	public double getThreshold() {
		return threshold;
	}

	/**
	 * sets the threshold parameter
	 */
	public void setThreshold(double threshold) {
		this.threshold = threshold;
		((SegregationRules)this.getRule()).setThreshold(threshold);
	}

}