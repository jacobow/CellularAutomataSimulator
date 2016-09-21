package cellsociety_team11;

import com.sun.glass.ui.CommonDialogs.Type;

import cellsociety_team11.gui.MainWindow;

public class CellSocietyController {

	
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
		// MainWindow mainWindow = new MainWindow();
		GameOfLifeGrid currGrid = new GameOfLifeGrid(20, 20, new GameOfLifeRules());
	}
}
