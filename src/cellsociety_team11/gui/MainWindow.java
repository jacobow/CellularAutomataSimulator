package cellsociety_team11.gui;

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
	private static final double INIT_WIDTH = 800;
	private static final double INIT_HEIGHT = 800;
	
	public MainWindow(){
		initScene();
	}
	
	public void addGrid(Grid<?> grid){
		DisplayGrid displayGrid = new DisplayGrid(grid);
		//Rectangle cellRectangle = new Rectangle(100, 100);
		//cellRectangle.setFill(Color.RED);
		//cellRectangle.setStroke(Color.BLACK);
		root.setCenter(displayGrid);
		
	}
	
	private void initScene(){
		this.initRoot();
		scene = new Scene(this.root, INIT_WIDTH, INIT_HEIGHT, Color.GHOSTWHITE);
	}
	
	private void initRoot(){
		root = new MainBorderPane();
		
	}
	
	
	public Scene getScene(){
		return scene;
	}
	
	
	
	
}
