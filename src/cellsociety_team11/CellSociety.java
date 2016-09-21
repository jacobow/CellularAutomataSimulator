package cellsociety_team11;

import cellsociety_team11.gui.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class CellSociety extends Application{

	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start (Stage primaryStage) throws Exception{
		MainWindow mainWindow = new MainWindow();
		GameOfLifeGrid currGrid = new GameOfLifeGrid(20, 20, new GameOfLifeRules());
		mainWindow.addGrid(currGrid);
		primaryStage.setTitle("Cell Society");
		primaryStage.setScene(mainWindow.getScene());
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
}
