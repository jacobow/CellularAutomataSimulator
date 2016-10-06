// This entire file is part of my masterpiece.
// Cleveland Thompson V (ct168)

package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
/*
 * 
 * @author Cleveland Thompson V (ct168)
 * 
 * Creates inferface for DisplayCell so that the shape of the cell can orient itself on the grid.
 */
public interface DisplayCellInterface {

	public double getCellWidth();
	
	public int getNumberOfSides();
	
	public Coordinates getCoordinates();
	
	public void moveCell(double x, double y);
}
