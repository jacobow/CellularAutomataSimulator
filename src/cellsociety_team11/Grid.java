package cellsociety_team11;
public abstract class Grid<T> {
	protected Cell<T> [][] gridMatrix;
	protected int height;
	protected int width;
	private Rule<T> rule;
	/**
	 * creates a new grid
	 * @param valueGrid
	 *				grid of the values of the cells
	 * @param rule
	 * 				the rules which the cells will follow
	 */
	@SuppressWarnings("unchecked")
	public Grid(T[][] valueGrid, Rule<T> rule) {
		height = valueGrid.length;
		width = valueGrid[0].length;
		this.rule = rule;
		this.gridMatrix = new Cell[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				gridMatrix[i][j] = createNewCell(valueGrid[i][j], new Coordinates(i, j));
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
	public abstract Cell<T> createNewCell(T value, Coordinates coordinates);
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
}