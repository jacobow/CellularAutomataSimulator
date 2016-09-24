package cellsociety_team11;
public class Cell<T> {
	private T currentValue;
	private T newValue;
	private Grid<T> grid;
	private Rule<T> rule;
	private Coordinates coordinates;
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
		rule = grid.getRule();
		this.coordinates = coordinates;
		this.grid = grid;
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
}