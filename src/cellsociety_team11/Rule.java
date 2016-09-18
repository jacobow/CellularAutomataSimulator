package cellsociety_team11;

public interface Rule<T> {
	/**
	 * calculates a new value from a cell's current value and it's neighbors
	 */
	T calculateNewValue(T value, Grid<T> grid, Coordinates coordinates);

}
