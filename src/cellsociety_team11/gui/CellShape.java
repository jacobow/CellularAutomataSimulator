// This entire file is part of my masterpiece.
// Cleveland Thompson V (ct168)



package cellsociety_team11.gui;

import javafx.scene.shape.Polygon;

/*
 * Included this class purely because it uses the displayCellInterface. I don't actually consider this code my best.
 * I wasn't sure whether or not you would want a reference to the use of the interface.
 */
public class CellShape extends Polygon{
	private double radius;
	private int numSides;
	private double cellWidth;
	private DisplayCellInterface displayCellInterface;
	
	public CellShape(DisplayCellInterface displayCellInterface) {
		
		this.displayCellInterface = displayCellInterface; 
		this.numSides = displayCellInterface.getNumberOfSides();
		this.cellWidth = displayCellInterface.getCellWidth();
		
		this.getPoints().addAll(getVertices());
		orientDisplayCell();
	}
	
	public double getRadius(){
		return this.radius;
	}
	
	private Double[] getVertices(){
		
		Double[] polyPoints = new Double[this.numSides * 2];
		double interiorAngle = getTotalInteriorAngleRadians(this.numSides)/this.numSides;
		for (int i = 0; i < this.numSides; i++){
			polyPoints[2*i] = vertexCalculation(interiorAngle, this.cellWidth, this.numSides, i, true);
			polyPoints[2*i+1] = vertexCalculation(interiorAngle, this.cellWidth, this.numSides, i, false);
		}
		return polyPoints;
	}
	
	private double vertexCalculation(double interiorAngle, double width, int numSides, int i, boolean xFactor){
		double rotateFactor1 = numSides==4 ? Math.PI/4 : 0;
		double rotateFactor2 = Math.PI/2;
		double angleArgument = 2*Math.PI*i/numSides + rotateFactor1 + rotateFactor2;
		double trigResult = xFactor ? Math.cos(angleArgument) : Math.sin(angleArgument);
		double amplitudeArgument = numSides%2==0 ? (width/2)  * 1/Math.sin(interiorAngle/2) : (width/2)/(Math.cos(interiorAngle/2));
		this.radius = amplitudeArgument;
		return (width/2) + amplitudeArgument*trigResult;
	}
	
	private double getTotalInteriorAngleRadians(int numSides){
		return (numSides - 2) * Math.PI;
	}
	
	public void orientDisplayCell(){
		double cellWidth, numSides, rowIndex, colIndex;
		cellWidth = this.cellWidth;
		numSides = this.numSides;
		rowIndex = this.displayCellInterface.getCoordinates().getI();
		colIndex = this.displayCellInterface.getCoordinates().getJ();
		
		if (numSides == 3){
			if (!((colIndex % 2 != 0 && rowIndex % 2 != 0) || (colIndex % 2 == 0 && rowIndex % 2 == 0))){
				this.setRotate(Math.toDegrees(Math.PI));
			}
			this.displayCellInterface.moveCell(cellWidth * ((double)colIndex)/2.0, rowIndex * 1.5*this.radius);
		}
		else if(numSides == 6){
			double horizontalOffset = rowIndex % 2 == 0 ? 0 : cellWidth/2;
			this.displayCellInterface.moveCell(cellWidth * ((double)colIndex) + horizontalOffset, rowIndex * (this.radius + cellWidth/4.0));
		}
		else{
			this.displayCellInterface.moveCell(cellWidth * (double)colIndex, cellWidth * (double) rowIndex);
		}
	}

}
