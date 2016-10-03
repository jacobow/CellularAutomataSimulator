package cellsociety_team11.slime_mold;

import java.util.List;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;
import xml.factory.XMLFactoryException;
import xml.model.SimulationXMLModel;

public class SlimeMoldGrid extends Grid<Integer> {

	public static final int EMPTY = 0;
	public static final int SLIME = 1;

	private int shape;
	private int evaporationFactor;

	/**
	 * creates new slime mold grid
	 * @param valueGrid
	 * 			2d array of the different cell values
	 * @param simulation
	 * 			holds the parameters (shape) of the grid
	 */
	public SlimeMoldGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
		try {
			shape = simulation.getShape();
            evaporationFactor = simulation.getEvaporationFactor();
            }
		catch (XMLFactoryException e) {
            e.printStackTrace();
            }
	}

	/**
	 * instantiates the rules of a slime mold grid
	 */
	@Override
	protected Rule<Integer> createRule(SimulationXMLModel simulation) {
		return new SlimeMoldRules();
	}

	/**
	 * creates a new slime mold cell in the grid
	 */
	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates, int shape) {
		return new SlimeMoldCell(value, coordinates, this, shape, evaporationFactor);
	}

	/**
	 * gets an empty cell for the finite grid
	 */
	@Override
	public Cell<Integer> getEmptyCell() {
		//the 0 is just an empty evaporation factor
		return new SlimeMoldCell(EMPTY, null, this, shape, 0);
	}

	/**
	 * gets the evaporation factor
	 */
	public int getEvaporationFactor() {
		return evaporationFactor;
	}

	/**
	 * sets the evaporation factor
	 */
	public void setEvaporationFactor(int evaporationFactor) {
		this.evaporationFactor = evaporationFactor;
		for(int i = 0; i < getHeight(); i++) {
			for(int j = 0; j < getWidth(); j++) {
				((SlimeMoldCell)getGridMatrix()[i][j]).setEvaporationFactor(evaporationFactor);
			}
		}
	}

	@Override
	public List<Double> getSimulationParameters() {
		// TODO Auto-generated method stub
		return null;
	}

}
