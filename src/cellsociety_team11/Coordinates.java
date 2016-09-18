package cellsociety_team11;

public class Coordinates {

	private int i;

	private int j;

	/**
	 * Grid coordinates constructor.
	 *
	 * @param i
	 *            row number in grid.
	 * @param j
	 *            column number in grid.
	 */
	public Coordinates(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int[] getCoordinates() {
		return new int[] { i, j };
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

}