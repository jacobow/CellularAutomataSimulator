package cellsociety_team11.gui;

import cellsociety_team11.Coordinates;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Highest Level of Abstraction for displaying the representation of each cell in the grid
 */
public abstract class DisplayCell<T> extends Pane{
	private T currentValue;
	private Coordinates coordinates;
	private Shape cellShape;
	private double radius;
	
	public DisplayCell(T value, Coordinates coordinates, double width, int numSides) {
		super();
		this.currentValue = value;
		this.coordinates = coordinates;
		this.cellShape = initCell(width, numSides);
		this.getChildren().add(cellShape);
		setColor(getColor());
	}
	
	public void moveCell(double x, double y){
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
	
	public void customRotate(double angle){
		this.cellShape.setRotate(angle);
	}
	
	public double getRadius(){
		return this.radius;
	}
	
	
	/*
	 * Updates current value of cell. Necessary for the implementation
	 * in which I update each DisplayCell as oppose to re-instantiate DisplayGrid;
	 * keeping for legacy reasons until the project is finished
	 */
	public void updateValue(T newValue){
		//TODO: Check if this method is still necessary as we approach final product
		this.currentValue = newValue;
		setColor(getColor());
	}
	
	/*
	 * Gets the current value of the coordinates. Necessary for the implementation
	 * in which I update each DisplayCell as oppose to re-instantiate DisplayGrid;
	 * keeping for legacy reasons until the project is finished
	 */
	public Coordinates getCoordinates(){
		return this.coordinates;
	}
	
	private Shape initCell(double width, int numSides){
		Polygon cellShape = new Polygon(getPolygonPoints(width, numSides));
		cellShape.setStroke(Color.BLACK);
		return cellShape;
	}
	
	private double[] getPolygonPoints(double width, int numSides){
		double[] polyPoints = new double[numSides * 2];
		double interiorAngle = getTotalInteriorAngleRadians(numSides)/numSides;
		for (int i = 0; i < numSides; i++){
			polyPoints[2*i] = vertixCalculation(interiorAngle, width, numSides, i, true);
			polyPoints[2*i+1] = vertixCalculation(interiorAngle, width, numSides, i, false);
		}
		return polyPoints;
	}
	
	private double vertixCalculation(double interiorAngle, double width, int numSides, int i, boolean xFactor){
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
	
	
	protected abstract Color getColor();
	
	protected void setColor(Color color){
		this.cellShape.setFill(color);
	}
	
	protected T getValue() {
		return this.currentValue;		
	}
	
	protected Shape getCellShape(){
		return this.cellShape;
	}

}
