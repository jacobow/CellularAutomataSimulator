package cellsociety_team11.game_of_life;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;
import xml.factory.XMLFactoryException;
import xml.model.SimulationXMLModel;

public class GameOfLifeGrid extends Grid<Boolean>{

	private String shape;

	public GameOfLifeGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
	    super(intToBool(valueGrid), simulation);
	    try {
		shape = simulation.getShape();
	    } catch (XMLFactoryException e) {
	        e.printStackTrace();
	    }
	}

	public GameOfLifeGrid(Boolean[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
	}

	@Override
	protected Rule<Boolean> createRule(SimulationXMLModel simulation) {
		return new GameOfLifeRules();
	}

	@Override
	public Cell<Boolean> createNewCell(Boolean value, Coordinates coordinates, String shape) {
		return new GameOfLifeCell(value, coordinates, this, shape);
	}

	@Override
	public Cell<Boolean> getEmptyCell() {
		return new GameOfLifeCell(false, null, this, shape);
	}

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


}