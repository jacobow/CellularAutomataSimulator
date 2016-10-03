package cellsociety_team11.spreading_of_fire;

import java.util.List;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;
import xml.factory.XMLFactoryException;
import xml.model.SimulationXMLModel;

/*
 * @author Jacob Warner
 * @author Cleveland Thompson
 */
public class SpreadingOfFireGrid extends Grid<Integer>{

	public static final int EMPTY = 0;

	private int shape;
	private double probCatch;

	/**
	 * creates new spreading of fire grid
	 * @param valueGrid
	 * 			2d array of the different cell values
	 * @param simulation
	 * 			holds the parameters (shape) of the grid
	 */
	public SpreadingOfFireGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
		try {
		    shape = simulation.getShape();
	        probCatch = simulation.getProbability();
		} catch (XMLFactoryException e) {
		    e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cellsociety_team11.Grid#createRule(xml.model.SimulationXMLModel)
	 */
	@Override
	protected Rule<Integer> createRule(SimulationXMLModel simulation) {
		try {
			return new SpreadingOfFireRules(simulation.getProbability());
		    } catch (XMLFactoryException e) {
		        e.printStackTrace();
		        return null;
		    }
	}
	/**
	 * creates a new cell for grid initialization
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates, int shape) {
		return new SpreadingOfFireCell(value, coordinates, this, shape);
	}

	/**
	 * gets an empty cell for a finite grid
	 */
	@Override
	public Cell<Integer> getEmptyCell() {
		return new SpreadingOfFireCell(EMPTY, null, this, shape);
	}

	/**
	 * gets the probCatch parameter
	 */
	public double getProbCatch() {
		return probCatch;
	}

	/**
	 * sets the probCatch parameter
	 */
	public void setProbCatch(double probCatch) {
		this.probCatch = probCatch;
		((SpreadingOfFireRules)this.getRule()).setProbCatch(probCatch);
	}

	@Override
	public List<Double> getSimulationParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}