package cellsociety_team11.gui;

import java.util.Map;
import java.util.ResourceBundle;

import cellsociety_team11.CellSociety;
import cellsociety_team11.Grid;
import cellsociety_team11.MainController;
import cellsociety_team11.SimulationInstantiationException;
import javafx.scene.Scene;
import javafx.scene.paint.Color;


/**
 * @author Cleveland Quin Thompson V (ct168)
 * Main Window for CellSociety GUI. Handles instantiating the MainBorderPane,
 * which initializes the user controls, and updating the DisplayGrid based on the Grid input.
 */
public class MainWindow{
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	private Scene scene;
	private MainBorderPane root;
	private DisplayGrid<?> displayGridLeft; 
	private DisplayGrid<?> displayGridRight; 
	
	private ResourceBundle resourceBundle;
	private MainController mainController;
	
	
	public MainWindow(MainController simulationController, String language){
		this.displayGridLeft = null;
		this.displayGridRight = null;
		this.mainController = simulationController;
		this.resourceBundle = initResourceBundle(language);
		initScene();
	}
	
	@SuppressWarnings("unchecked")
	public <T> Boolean setGrid(Grid<T> grid, String simulationType, int numSides){
		Boolean chosenDisplay = true;
		if (grid!=null){
			try{
				if (this.displayGridLeft==null){
					this.displayGridLeft = new DisplayGrid<T>(grid, simulationType, numSides);
					this.root.setCenter(this.displayGridLeft);
					this.root.updateGraph((Map<Object, Integer>) this.displayGridLeft.getGrid().getStateTotals());
				}
				else{
					chosenDisplay = root.chooseDisplay();
					if (chosenDisplay == null)
						return null;
					else if(chosenDisplay){
						this.displayGridLeft = new DisplayGrid<T>(grid, simulationType, numSides);
						if (this.displayGridRight!=null)
							this.root.setLeft(this.displayGridLeft);
						else
							this.root.setCenter(this.displayGridLeft);
					}
					else{
						this.displayGridRight = new DisplayGrid<T>(grid, simulationType, numSides);
						this.root.setCenter(null);
						this.root.setLeft(this.displayGridLeft);
						this.root.setRight(this.displayGridRight);
					}
				}
			}
			catch(SimulationInstantiationException s){
				System.out.print("Caught SimException");
				root.showErrorAlert();
			}
		}
		return chosenDisplay;
	}
	
	@SuppressWarnings("unchecked")
	public void updateGrids(){
		if (displayGridLeft !=null){
			displayGridLeft.updateDisplayCells();
			this.root.updateGraph((Map<Object, Integer>) this.displayGridLeft.getGrid().getStateTotals());
		}
		if (displayGridRight != null){
			displayGridRight.updateDisplayCells();
		}
	}
	
	
	private ResourceBundle initResourceBundle(String language){
		return ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
	}
	
	private void initScene(){
		this.root = this.initRoot();
		this.scene = new Scene(this.root, CellSociety.INIT_WIDTH, CellSociety.INIT_HEIGHT, Color.GHOSTWHITE);
	}
	
	private MainBorderPane initRoot(){
		return new MainBorderPane(this.mainController, this.resourceBundle);
	}
	
	public Scene getScene(){
		return this.scene;
	}
		
}
