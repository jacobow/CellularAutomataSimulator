package cellsociety_team11;

import cellsociety_team11.gui.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class CellSociety extends Application{
	public static final double INIT_WIDTH = 800;
	public static final double INIT_HEIGHT = 800;
	
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start (Stage primaryStage) throws Exception{
		CellSocietyController cellSocietyController = new CellSocietyController();
		primaryStage.setTitle("Cell Society");
		primaryStage.setScene(cellSocietyController.getScene());
		primaryStage.centerOnScreen();
		primaryStage.setMinHeight(INIT_HEIGHT);
		primaryStage.setMinWidth(INIT_WIDTH);
		primaryStage.show();
	}
}
