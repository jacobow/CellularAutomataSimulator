package cellsociety_team11;

import java.util.HashSet;

public abstract class Cell<T> {

	private T currentValue;
	private T newValue;
	protected Grid<T> grid;
	private Rule<T> rule;
	protected Coordinates coordinates;

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
	public Cell(T value, Coordinates coordinates, Grid<T> grid) {
		this.currentValue = value;
		newValue = null;
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
	public abstract HashSet<?> getNeighbors();
}