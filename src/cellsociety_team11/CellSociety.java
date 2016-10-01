package cellsociety_team11;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Main Class for JavaFX Application
 */
public class CellSociety extends Application{
	public static final double INIT_WIDTH = 800;
	public static final double INIT_HEIGHT = 800;
	
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start (Stage primaryStage) throws Exception{
		CellSocietyController cellSocietyController = new CellSocietyController("English");
		primaryStage.setTitle("Cell Society");
		primaryStage.setScene(cellSocietyController.getScene());
		primaryStage.centerOnScreen();
		primaryStage.setMinHeight(INIT_HEIGHT);
		primaryStage.setMinWidth(INIT_WIDTH);
		primaryStage.show();
	}
}
