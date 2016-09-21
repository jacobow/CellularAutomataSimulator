package cellsociety_team11;

import com.sun.glass.ui.CommonDialogs.Type;

import cellsociety_team11.gui.DisplayCell;
import cellsociety_team11.gui.MainWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class CellSocietyController {
	private static final int FRAMES_PER_SECOND = 5;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	
	private MainWindow mainWindow;
	private GameOfLifeGrid grid;
	private Timeline timeline;
	/**Allows for a list of Simulation Types in the GUI
	 * We can remove or completely refactor--it's late and this might be dumb
	 */
	public enum SimulationType{
		
		GAMEOFLIFE ("Game of Life", GameOfLifeGrid.class),
		SCHELLINGS ("Schelling's Model of Segregation", null);
		
		private final String title;
		private final Class<? extends Grid<?>> gridClass; 
		
		private SimulationType(String title, Class<? extends Grid<?>> gridClass) {
			this.title=title;
			this.gridClass = gridClass;
		}
		
		public String getTitle(){
			return this.title;
		}
		
		public Class<? extends Grid<?>> getGridClass(){
			return this.gridClass;
		}
	}
	
	public CellSocietyController(){
		this.mainWindow = new MainWindow();
		grid = new GameOfLifeGrid(20, 20, new GameOfLifeRules());
		this.mainWindow.addGrid(grid);
		createStartButtonListener();
	}
	
	private void createStartButtonListener(){
		 Button startButton = (Button) mainWindow.getScene().lookup("#startButton");
		 startButton.setOnMouseClicked(e -> handleStartButtonClick(e));
		 
		 Button stepButton = (Button) mainWindow.getScene().lookup("#stepButton");
		 stepButton.setOnMouseClicked(e -> handleStepButtonClick(e));
		 
		 GridPane displayGrid = (GridPane) mainWindow.getScene().lookup("#displayGrid");
		 for (Node displayCell : displayGrid.getChildren()){
				DisplayCell currDisplayCell = (DisplayCell) displayCell;
				currDisplayCell.setOnMouseClicked(e -> handleGridClicked(e, currDisplayCell));
		 }
		 
		 //grid.getCell(currDisplayCell.getCoordinates()).setValue(!currDisplayCell.getValue());
	}
	
	private void handleGridClicked(MouseEvent e, DisplayCell currDisplayCell){
		grid.getCell(currDisplayCell.getCoordinates()).setValue(!grid.getCell(currDisplayCell.getCoordinates()).getValue());;
		//currCell.setValue((boolean)!((boolean)currCell.getValue()));
		System.out.println("Sah dood: " + grid.getCell(currDisplayCell.getCoordinates()).getValue());
	}
	
	private void handleStartButtonClick(MouseEvent mouseEvent){
		this.initAnimation();
	}
	
	private void handleStepButtonClick(MouseEvent mouseEvent){
		System.out.println("STEP");
		this.step();
	}
	
	public Scene getScene(){
		return mainWindow.getScene();
	}
	
	public void step(){
		grid.nextGrid();
		mainWindow.addGrid(grid);
	}
	
	private void initAnimation(){
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> this.step());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();
	}
}
