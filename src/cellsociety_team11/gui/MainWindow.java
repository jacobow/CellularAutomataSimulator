package cellsociety_team11.gui;

import cellsociety_team11.CellSociety;
import cellsociety_team11.Grid;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * @author quin
 *
 */
public class MainWindow{
	private Scene scene;
	private MainBorderPane root;
	private DisplayGrid displayGrid; 
	
	public MainWindow(){
		displayGrid = null;
		initScene();
	}
	
	public void addGrid(Grid<?> grid){
		if (displayGrid == null){
			DisplayGrid displayGrid = new DisplayGrid(grid);
			root.setCenter(displayGrid);
		}
		else{
			displayGrid.updateDisplayCells(grid);
		}
		
		//Rectangle cellRectangle = new Rectangle(100, 100);
		//cellRectangle.setFill(Color.RED);
		//cellRectangle.setStroke(Color.BLACK);
		
		
	}
	
	private void initScene(){
		this.initRoot();
		scene = new Scene(this.root, CellSociety.INIT_WIDTH, CellSociety.INIT_HEIGHT, Color.GHOSTWHITE);
	}
	
	private void initRoot(){
		root = new MainBorderPane();
		
	}
	
	
	public Scene getScene(){
		return scene;
	}
	
	
	
	
}
