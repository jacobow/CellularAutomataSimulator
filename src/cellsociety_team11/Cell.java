package cellsociety_team11;

import java.util.ArrayList;

public abstract class Cell<T> {

	private T currentValue;
	private T newValue;
	protected Grid<T> grid;
	private Rule<T> rule;
	protected Coordinates coordinates;
	protected int shape;

	/**
	 * Cell of a grid
	 * @param value
	 * 		The characteristic of the cell which determines
	 * 		which rules it is to follow
	 * @param coordinates
	 * 		Location of the cell on the grid
	 * @param grid
	 * 		The grid the cell is located on
	 */
	public Cell(T value, Coordinates coordinates, Grid<T> grid, String shape) {
		this.currentValue = value;
		newValue = null;
		this.shape = shape;
		this.rule = grid.getRule();
		this.coordinates = coordinates;
		this.grid = grid;
	}
	/**
	 * gets the cell's coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	/**
	 * Gets the value of the cell
	 */
	public T getValue() {
		return currentValue;
	}
	/**
	 * Gets the new value of the cell
	 */
	public T getNewValue() {
		return newValue;
	}
	/**
	 * Sets the new value of the cell
	 */
	public void setNewValue(T value) {
		newValue = value;
	}
	/**
	 * gets the shape of the cell
	 */
	public int getShape() {
		return shape;
	}
	/**
	 * determines what the cell's next value should be
	 */
	public void evaluateCell() {
		newValue = rule.calculateNewValue(this, currentValue, grid, coordinates);
	}
	/**
	 *  commits the cell's next value as the current value
	 */
	public void commitCell() {
		currentValue = newValue;
		newValue = null;
	}
	/**
	 * gets a cell's neighbors
	 */
	public ArrayList<Cell<T>> getNeighbors(){
		switch (shape) {
			case 4:
				return getSquareNeighbors();
			case 3:
				return getTriangleNeighbors();
			case 6:
				return getHexagonNeighbors();
			default:
				return null;
		}
	}
	private ArrayList<Cell<T>> getSquareNeighbors() {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		ArrayList<Cell<T>> neighbors = new ArrayList<Cell<T>>();
		for(int y = -1; y <= 1; y++) {
			for(int x = -1; x <= 1; x++) {
				if(y == 0 && x == 0) {
					continue;
				}
				if(0 > i+y || i+y >= grid.getHeight() || 0 > j+x || j+x >= grid.getWidth()) {
					neighbors.add(grid.getEdgeCell(new Coordinates(i+y, j+x)));
				}
				else {
					neighbors.add(grid.getCell(new Coordinates(i+y, j+x)));
				}
			}
		}
		return neighbors;
	}
	private ArrayList<Cell<T>> getTriangleNeighbors() {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		int offset = -1;
		ArrayList<Cell<T>> neighbors = getSquareNeighbors();
		if(i%2 == 0 && j%2 == 0) offset = 1;
		if(i%2 != 0 && j%2 != 0) offset = 1;
		if(j+2 < grid.getWidth()) {
			neighbors.add(grid.getCell(new Coordinates(i, j+2)));
			neighbors.add(grid.getCell(new Coordinates(i + offset, j+2)));
		}
		else {
			neighbors.add(grid.getEdgeCell(new Coordinates(i, j+2)));
			neighbors.add(grid.getEdgeCell(new Coordinates(i + offset, j+2)));
		}
		if(j-2 >= 0) {
			neighbors.add(grid.getCell(new Coordinates(i, j-2)));
			neighbors.add(grid.getCell(new Coordinates(i + offset, j-2)));
		}
		else {
			neighbors.add(grid.getEdgeCell(new Coordinates(i, j-2)));
			neighbors.add(grid.getEdgeCell(new Coordinates(i + offset, j-2)));
		}
		return neighbors;
	}
	private ArrayList<Cell<T>> getHexagonNeighbors() {
		int i = coordinates.getI();
		int j = coordinates.getJ();
		ArrayList<Cell<T>> neighbors = new ArrayList<Cell<T>>();
		for(int y = -1; y <= 1; y++) {
			for(int x = -1; x <= 1; x++) {
				if((y == 0 && x == 0) || (y == 1 && x == -1) || (y == -1 && x == 1)) {
					continue;
				}
				if(0 > i+y || i+y >= grid.getHeight() || 0 > j+x || j+x >= grid.getWidth()) {
					neighbors.add(grid.getEdgeCell(new Coordinates(i+y, j+x)));
				}
				else {
					neighbors.add(grid.getCell(new Coordinates(i+y, j+x)));
				}
			}
		}
		return neighbors;
	}

}