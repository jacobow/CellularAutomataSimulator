package cellsociety_team11.game_of_life;

import java.util.List;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;
import xml.factory.XMLFactoryException;
import xml.model.SimulationXMLModel;

public class GameOfLifeGrid extends Grid<Boolean>{

	private int shape;

	/**
	 * creates new game of life grid
	 * @param valueGrid
	 * 			2d array of the different cell values
	 * @param simulation
	 * 			holds the parameters (shape) of the grid
	 */
	public GameOfLifeGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
	    super(intToBool(valueGrid), simulation);
	    try {
		shape = simulation.getShape();
	    } catch (XMLFactoryException e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * creates new game of life grid
	 * @param valueGrid
	 * 			2d array of the different cell values
	 * @param simulation
	 * 			holds the parameters (shape) of the grid
	 */
	public GameOfLifeGrid(Boolean[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
	}

	/**
	 * instantiates the rules of the game of life grid
	 */
	@Override
	protected Rule<Boolean> createRule(SimulationXMLModel simulation) {
		return new GameOfLifeRules();
	}
	/**
	 * creates a new cell in the grid
	 */
	@Override
	public Cell<Boolean> createNewCell(Boolean value, Coordinates coordinates, int shape) {
		return new GameOfLifeCell(value, coordinates, this, shape);
	}

	/**
	 * gets an empty cell
	 */
	@Override
	public Cell<Boolean> getEmptyCell() {
		return new GameOfLifeCell(false, null, this, shape);
	}

	/**
	 * converts an Boolean grid to an Integer grid
	 */
	private static Boolean[][] intToBool(Integer[][] array) {
	    int rows = array.length;
	    int columns = array[0].length;
	    Boolean[][] result = new Boolean[rows][columns];
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                result[i][j] = (array[i][j] == 1);
            }
        }
        return result;
	}
	@Override
	public List<Double> getSimulationParameters() {
		// TODO Auto-generated method stub
		return null;
	}


}