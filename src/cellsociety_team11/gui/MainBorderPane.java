package cellsociety_team11.gui;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import javafx.scene.control.Button;
import cellsociety_team11.CellSociety;
import cellsociety_team11.Grid;
import cellsociety_team11.CellSocietyController.SimulationType;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * @author quin
 *
 */
public class MainBorderPane extends BorderPane{
	private MenuBar menuBar;
	
	public MainBorderPane(){
		initMainBorderPane();
	}
	
	private void initMainBorderPane(){
		createMenuBar();
		createStartButton();		
	}
	
	private void createMenuBar(){
		menuBar = new MenuBar();
		Menu simulationMenu = createSimulationSelectorMenu();
		menuBar.getMenus().add(simulationMenu);
		this.setTop(menuBar);
	}
	
	private void createStartButton(){
		Button startButton = new Button("Start");
		startButton.setId("startButton");
		this.setLeft(startButton);
		
		Button stepButton = new Button("Step");
		stepButton.setId("stepButton");
		this.setRight(stepButton);
	}
	
	private Menu createSimulationSelectorMenu(){
		Menu simMenu = new Menu("Simulation");
		for (SimulationType s : SimulationType.values()) {
			MenuItem simMenuItem = new MenuItem(s.getTitle());
			simMenuItem.setOnAction(e -> handleMenuItemAction(e, s));
			simMenu.getItems().add(simMenuItem);
		}

		return simMenu;
	}
	
	private void handleMenuItemAction(ActionEvent actionEvent, SimulationType simulationType){
		MenuItem actionMenuItem = (MenuItem) actionEvent.getSource();
		//Node myParent = this.getParent();
		
		//potentially get parent and pass simulation type and let Controller deal
		// Put a pin in this for now -- I think I'm overthinking
		/*Class<? extends Grid<?>> gridClass = simulationType.getGridClass();
		// need to check if this class is a subclass of grid
		if (gridClass != null){
			Constructor<?> constructor = gridClass.getConstructor(parameterTypes)
		}*/
		
	}
	
	
	
	
	
	
	
}
