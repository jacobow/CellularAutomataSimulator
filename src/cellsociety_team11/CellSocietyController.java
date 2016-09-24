package cellsociety_team11;

import cellsociety_team11.game_of_life.GameOfLifeCell;
import cellsociety_team11.game_of_life.GameOfLifeGrid;
import cellsociety_team11.game_of_life.GameOfLifeRules;
import cellsociety_team11.gui.MainBorderPane;
import cellsociety_team11.gui.MainWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class CellSocietyController implements SimulationController{
	public static final Boolean[][] TEST_INIT_GRID= new Boolean[][]{
		{false, false, false, true, true},
		{false, false, true, true, false},
		{false, true, true, false, false},
		{false, true, false, false, true},
		{false, true, false, false, true}			
		};
	
	private static final double INIT_FRAMES_PER_SECOND = 4;
    private static final double MILLISECOND_DELAY = 1000.0 / INIT_FRAMES_PER_SECOND;
	
	private MainWindow mainWindow;
	private GameOfLifeGrid grid;
	private Timeline timeline;
	private double simulationSpeed;
	
	public CellSocietyController(String language){
		simulationSpeed = MainBorderPane.SPEED_SLIDER_START;
		this.mainWindow = new MainWindow(this, language);
		grid = new GameOfLifeGrid(TEST_INIT_GRID);
		//testSetGrid();
		this.mainWindow.setGrid(grid);
		
	}
	
	private void testSetGrid(){
		for (int i = 0; i < grid.getWidth(); i++){
			for (int j = 0; j<grid.getHeight(); j++){
				Coordinates currentCoords = new Coordinates(i, j);
				GameOfLifeCell currentCell = (GameOfLifeCell) grid.getCell(currentCoords);
				currentCell.setNewValue(TEST_INIT_GRID[i][j]);
			}
		}
	}
	
	public Scene getScene(){
		return mainWindow.getScene();
	}
	
	@Override
	public void startSimulation(){
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY * simulationSpeed),
                e -> this.nextStepSimulation());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();
	}

	@Override
	public void nextStepSimulation() {
		grid.nextGrid();
		System.out.println("Updated Grid");
		printGrid(grid);
		mainWindow.setGrid(grid);
	}
	
	
	
	private void printGrid(Grid grid){
		for (int i = 0; i < grid.getWidth(); i++){
			for (int j = 0; j< grid.getHeight(); j++){
				Cell currCell = grid.getCell(new Coordinates(i, j));
				System.out.print(currCell.getValue().toString() + " ");
			}
			System.out.println("");
		}
	}
	
	@Override
	public void updateSimulationSpeed(MouseEvent speedUpdated) {
		// TODO Auto-generated method stub
		Slider speedSlider = (Slider) speedUpdated.getSource();
		this.simulationSpeed = speedSlider.getValue();
		timeline.setDelay(Duration.millis(MILLISECOND_DELAY * simulationSpeed));
	}

	@Override
	public void stopSimulation() {
		// TODO Auto-generated method stub
		timeline.stop();
	}
}
