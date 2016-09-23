package cellsociety_team11;

public abstract class Grid<T> {

	protected Cell<T> [][] gridMatrix;
	protected int height;
	protected int width;


	@SuppressWarnings("unchecked")
	public Grid(int height, int length, Rule<T> rule) {
		this.height = height;
		this.width = length;
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
	public abstract Cell<T> createNewCell(Coordinates coordinates, Rule<T> rule);

	public Cell<T>[][] getGridMatrix() {
		return gridMatrix;
	}
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
