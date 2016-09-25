package cellsociety_team11;

import cellsociety_team11.game_of_life.GameOfLifeCell;
import cellsociety_team11.game_of_life.GameOfLifeGrid;
import cellsociety_team11.game_of_life.GameOfLifeRules;
import cellsociety_team11.gui.MainBorderPane;
import cellsociety_team11.gui.MainWindow;
import cellsociety_team11.gui.SimulationType;
import cellsociety_team11.predator_prey.PredatorPreyGrid;
import cellsociety_team11.segregation.SegregationGrid;
import cellsociety_team11.spreading_of_fire.SpreadingOfFireGrid;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class CellSocietyController implements MainController{
	public static final Boolean[][] BOOL_INIT_GRID= new Boolean[][]{
		{false, false, false, true, true},
		{false, false, true, true, false},
		{false, true, true, false, false},
		{false, true, false, false, true},
		{false, true, false, false, true}
		};

	public static final Integer[][] INT_INIT_GRID= new Integer[][]{
		{0, 0, 0, 0},
		{0, 2, 1, 2},
		{0, 0, 1, 0},
		{1, 1, 0, 1},
		{0, 0, 0, 0}
		};

	private static final double INIT_FRAMES_PER_SECOND = 4;
    private static final double MILLISECOND_DELAY = 1000.0 / INIT_FRAMES_PER_SECOND;

	private MainWindow mainWindow;
	private Grid<?> grid;
	private Timeline timeline;
	private double simulationSpeed;
	private SimulationType simulationType;

	public CellSocietyController(String language){

		simulationSpeed = MainBorderPane.SPEED_SLIDER_START;
		this.mainWindow = new MainWindow(this, language);
		simulationType = SimulationType.PREDATOR_PREY;
		//grid = new SpreadingOfFireGrid(INT_INIT_GRID, 0.5);
		//grid = new SegregationGrid(INT_INIT_GRID, 0.5);
		grid = new PredatorPreyGrid(INT_INIT_GRID, 3, 3, 3);
		//testSetGrid();
		this.mainWindow.setGrid(grid, this.simulationType);

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
		//printGrid(grid);
		mainWindow.setGrid(grid, this.simulationType);
	}



	private void printGrid(Grid<?> grid){
		for (int i = 0; i < grid.getWidth(); i++){
			for (int j = 0; j< grid.getHeight(); j++){
				Cell<?> currCell = grid.getCell(new Coordinates(i, j));
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
