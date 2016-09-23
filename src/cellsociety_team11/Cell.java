package cellsociety_team11;

public class Cell<T> {

	private T currentValue;
	private T newValue;
	private Grid<T> grid;
	private Rule<T> rule;
	private Coordinates coordinates;


	public Cell(T value, Rule<T> rule, Coordinates coordinates, Grid<T> grid) {
		this.currentValue = value;
		this.rule = rule;
		this.coordinates = coordinates;
		this.grid = grid;
	}

	public T getValue() {
		return currentValue;
	}

	public T getNewValue() {
		return newValue;
	}

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
