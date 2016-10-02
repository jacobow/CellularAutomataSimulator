package cellsociety_team11.slime_mold;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;
import cellsociety_team11.Rule;
import xml.model.SimulationXMLModel;

public class SlimeMoldGrid extends Grid<Integer> {

	public static final int EMPTY = 0;
	public static final int SLIME = 1;

	private String shape;
	private int evaporationFactor;

	public SlimeMoldGrid(Integer[][] valueGrid, SimulationXMLModel simulation) {
		super(valueGrid, simulation);
		shape = simulation.getShape();
		//evaporationFactor = simulation.getEvaporationFactor();
	}

	@Override
	protected Rule<Integer> createRule(SimulationXMLModel simulation) {
		return new SlimeMoldRules();
	}

	@Override
	public Cell<Integer> createNewCell(Integer value, Coordinates coordinates, String shape) {
		return new SlimeMoldCell(value, coordinates, this, shape, evaporationFactor);
	}

	@Override
	public Cell<Integer> getEmptyCell() {
		return new SlimeMoldCell(EMPTY, null, this, shape, 0);
	}

}
