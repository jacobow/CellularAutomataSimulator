package cellsociety_team11.slime_mold;

import java.util.ArrayList;

import cellsociety_team11.Cell;
import cellsociety_team11.Coordinates;
import cellsociety_team11.Grid;

public class SlimeMoldCell extends Cell<Integer>{

	public static final int EMPTY = 0;
	public static final int SLIME = 1;

	private ArrayList<Integer> cAMP;
	private int evaporationFactor;

	public SlimeMoldCell(Integer value, Coordinates coordinates, Grid<Integer> grid, int shape, int evaporationFactor) {
		super(value, coordinates, grid, shape);
		this.evaporationFactor = evaporationFactor;
		cAMP = new ArrayList<Integer>();
	}

	public int getTotalCAMP() {
		int sum = 0;
		for(Integer i  : cAMP) {
			sum += i;
		}
		return sum;
	}

	public void addToCAMP() {
		cAMP.add(evaporationFactor);
	}

	public void evaporate() {
		int index = 0;
		for(Integer i : cAMP) {
			i--;
			if(i <= 0) cAMP.remove(index);
			index++;
		}
	}

	public void diffuse() {
		for(Cell<Integer> cell : getNeighbors()) {
			if(cell.getValue() == SLIME) continue;
			((SlimeMoldCell)cell).addToCAMP();
		}
		this.addToCAMP();
	}

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

	public int getEvaporationFactor() {
		return evaporationFactor;
	}

	public void setEvaporationFactor(int evaporationFactor) {
		this.evaporationFactor = evaporationFactor;
	}
}
