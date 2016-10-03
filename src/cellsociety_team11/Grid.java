package cellsociety_team11;

import xml.factory.XMLFactoryException;
import xml.model.SimulationXMLModel;

public abstract class Grid<T> {

	private Cell<T> [][] gridMatrix;
	private int height;
	private int width;
	private String world;
	private Rule<T> rule;

	/**
	 * creates a new grid
	 * @param valueGrid
	 *				grid of the values of the cells
	 * @param simulation
	 * 				holds all of the parameters of the simulation
	 */
	@SuppressWarnings("unchecked")
	public Grid(T[][] valueGrid, SimulationXMLModel simulation) {
	    try {
		this.rule = createRule(simulation);
		this.height = valueGrid.length;
		this.width = valueGrid[0].length;
		this.gridMatrix = new Cell[height][width];
		this.world = simulation.getWorld();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				gridMatrix[i][j] = createNewCell(valueGrid[i][j], new Coordinates(i, j), simulation.getShape());
			}
		}
	    } catch (XMLFactoryException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 *
	 * Subclass has to take simulation and instantiate Rule
	 */
	protected abstract Rule<T> createRule(SimulationXMLModel simulation);
	/**
	 * Manipulate the Grid into its next state
	 */
	public void nextGrid() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(gridMatrix[i][j].getNewValue() == null) gridMatrix[i][j].evaluateCell();
			}
		}
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				gridMatrix[i][j].commitCell();
			}
		}
	}
	/**
	 * retrieves a cell in the grid from certain coordinates
	 * @param c
	 * 		coordinates where the cell is
	 * @return
	 * 		cell at the coordinates of the grid
	 */
	public Cell<T> getCell(Coordinates c) {
		return gridMatrix[c.getI()][c.getJ()];
	}
	/**
	 * creates a cell at certain coordinates
	 * @param coordinates
	 * 		coordinates of cell to be made
	 * @param rule
	 * 		Rule class the cell follows
	 * @return
	 * 		Cell at given coordinates
	 */
	public abstract Cell<T> createNewCell(T value, Coordinates coordinates, int shape);
	/**
	 * gets a cell at the edge
	 */
	public Cell<T> getEdgeCell(Coordinates coordinates) {
		switch(world) {
			case "finite":
				return getEmptyCell();
			case "toroidal":
				return getToroidalCell(coordinates);
			default:
				return null;
		}

	}

	/**
	 * subclasses implement this to define what an empty cell is
	 * for finite grids
	 */
	public abstract Cell<T> getEmptyCell();

	/**
	 * gets a cell on the other end of the toroidal grid
	 * @param coordinates
	 * 		coordinates outside the range of the displayed grid
	 */
	private Cell<T> getToroidalCell(Coordinates coordinates) {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		if(i < 0) i = height + i;
		if(i >= height) i = i - height;
		if(j < 0) j = width + j;
		if(j >= width) j = j - width;
		return getCell(new Coordinates(i, j));
	}
	/**
	 * gets the rules
	 */
	public Rule<T> getRule() {
		return rule;
	}
	/**
	 * sets a new rule
	 */
	public void setRule(Rule<T> rule) {
		this.rule = rule;
	}
	/**
	 * gets the grid matrix
	 */
	public Cell<T>[][] getGridMatrix() {
		return gridMatrix;
	}
	/**
	 * gets the height of the grid
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * gets the width of the grid
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * gets the world type
	 */
	public String getWorld() {
		return world;
	}
	/**
	 * sets the world type
	 */
	public void setWorld(String world) {
		this.world = world;
	}
}