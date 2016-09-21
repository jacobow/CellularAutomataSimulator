package cellsociety_team11;

public abstract class Grid<T> {

	private Cell<T> [][] gridMatrix;
	private int height;
	private int width;
	private Rule<T> rule;


	public Grid(int height, int length, Rule rule) {
		this.height = height;
		this.width = length;
		this.rule = rule;
		this.gridMatrix = new Cell[height][length];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < length; j++) {
				gridMatrix[i][j] = createNewCell(new Coordinates(i, j), rule);
			}
		}
	}
	/**
	 * Manipulate the Grid into its next state
	 */
	public void nextGrid() {
		for(int i = 1; i < height-1; i++) {
			for(int j = 1; j < width-1; j++) {
				gridMatrix[i][j].evaluateCell();
			}
		}
		for(int i = 1; i < height-1; i++) {
			for(int j = 1; j < width-1; j++) {
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
	public abstract Cell<T> createNewCell(Coordinates coordinates, Rule<T> rule);

	/**
	 * @return
	 * 		height of the grid
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @return
	 * 		width of the grid
	 */
	public int getWidth() {
		return width;
	}
}
