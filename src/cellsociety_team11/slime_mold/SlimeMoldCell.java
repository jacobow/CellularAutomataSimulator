package cellsociety_team11.slime_mold;

import java.util.ArrayList;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class SlimeMoldCell extends Cell<Integer>{

	public static final Integer EMPTY = 0;
	public static final Integer SLIME = 1;

	private ArrayList<Integer> cAMP;
	private int evaporationFactor;

	/**
	 * creates a new slime mold cell
	 * @param value
	 * 			either slime or empty
	 * @param coordinates
	 * 			location of the cell on the grid
	 * @param grid
	 * 			grid that the cell is wrong
	 * @param shape
	 * 			shape of the cell
	 * @param evaporationFactor
	 * 			the number of turns it takes for cAMP to completely evaporate
	 */
	public SlimeMoldCell(Integer value, Coordinates coordinates, Grid<Integer> grid, int shape, int evaporationFactor) {
		super(value, coordinates, grid, shape);
		this.evaporationFactor = evaporationFactor;
		cAMP = new ArrayList<Integer>();
	}

	private int getTotalCAMP() {
		int sum = 0;
		for(Integer i  : cAMP) {
			sum += i;
		}
		return sum;
	}
	//add to a cell's cAMP
	private void addToCAMP() {
		cAMP.add(evaporationFactor);
	}

	/**
	 * evaporates a cell's cAMP
	 */
	public void evaporate() {
		for(Integer i : cAMP) {
			i--;
		}
		for(int i = 0; i < cAMP.size(); i++) {
			if(cAMP.get(i) <= 0) cAMP.remove(i);
		}
	}

	/**
	 * diffuses cAMP into a cell
	 */
	public void diffuse() {
		for(Cell<Integer> cell : getNeighbors()) {
			if(cell.getValue() == SLIME) continue;
			((SlimeMoldCell)cell).addToCAMP();
		}
		this.addToCAMP();
	}

	/**
	 * moves a cells value to the neighboring cell with the most cAMP
	 * @param grid
	 * 			grid the cell should be moved on
	 * @return
	 * 			return true if movement occurred and false otherwise
	 */
	public boolean move(Grid<Integer> grid) {
		boolean hasMoved = false;
		int mostCAMP = 0;
		Coordinates destination = this.getCoordinates();
		for(Cell<Integer> cell : getNeighbors()) {
			if(cell.getCoordinates() == null || cell.getValue() == SLIME || cell.getNewValue() == SLIME) continue;
			if(((SlimeMoldCell)cell).getTotalCAMP() > mostCAMP) {
				destination = cell.getCoordinates();
				mostCAMP = ((SlimeMoldCell)cell).getTotalCAMP();
				hasMoved = true;
			}
		}
		grid.getCell(destination).setNewValue(SLIME);
		return hasMoved;
	}

	/**
	 * gets the evaporation factor
	 */
	public int getEvaporationFactor() {
		return evaporationFactor;
	}

	/**
	 * sets the evaporation factor
	 */
	public void setEvaporationFactor(int evaporationFactor) {
		this.evaporationFactor = evaporationFactor;
	}
}
