package cellsociety_team11;

import cellsociety_team11.game_of_life.GameOfLifeCell;
import cellsociety_team11.game_of_life.GameOfLifeGrid;
import cellsociety_team11.game_of_life.GameOfLifeRules;
import cellsociety_team11.gui.MainWindow;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
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

	private static final int FRAMES_PER_SECOND = 4;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

	private MainWindow mainWindow;
	private GameOfLifeGrid grid;
	private Timeline timeline;
	private double simulationSpeed;

	public CellSocietyController(String language){
		this.mainWindow = new MainWindow(this, language);
		grid = new GameOfLifeGrid(TEST_INIT_GRID);
		this.mainWindow.setGrid(grid);

	}

	public Scene getScene(){
		return mainWindow.getScene();
	}

	@Override
	public void startSimulation(){
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> this.nextStepSimulation());
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();
	}

	@Override
	public void nextStepSimulation() {
		grid.nextGrid();
		//mainWindow.setGrid(grid);
	}

	@Override
	public void updateSimulationSpeed(MouseEvent speedUpdated) {
		// TODO Auto-generated method stub

	}
}
