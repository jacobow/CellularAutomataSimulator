package cellsociety_team11.gui;

import cellsociety_team11.gui.display_cell_types.square_display_cells.GameOfLifeDisplayCell;
import cellsociety_team11.gui.display_cell_types.square_display_cells.PredatorPreyDisplayCell;
import cellsociety_team11.gui.display_cell_types.square_display_cells.SegregationDisplayCell;
import cellsociety_team11.gui.display_cell_types.square_display_cells.SpreadingOfFireDisplayCell;

/**
 * @author Cleveland Quin Thompson V (ct168)
 * Enumerable Type for choosing the correct DisplayCell based on the Simulation Type.
 * Owned by the gui and managed by the CellSocietyController
 */
public enum SimulationType{
	
	GAMEOFLIFE ("Game of Life", GameOfLifeDisplayCell.class),
	SEGREGATION ("Schelling's Model of Segregation", SegregationDisplayCell.class),
	SPREADING_OF_FIRE ("Spreading of Fire", SpreadingOfFireDisplayCell.class),
	PREDATOR_PREY ("Predator Prey", PredatorPreyDisplayCell.class);
	
	
	private final String title;
	private final Class <? extends DisplayCell<?>> displayCellClass;
	
	private SimulationType(String title, Class <? extends DisplayCell<?>> displayCellClass) {
		this.title=title;
		this.displayCellClass = displayCellClass;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	/*public Class<? extends Grid<?>> getGridClass(){
		return this.gridClass;
	}*/
	
	public Class<? extends DisplayCell<?>> getDisplayCellClass(){
		return this.displayCellClass;
	}
}
