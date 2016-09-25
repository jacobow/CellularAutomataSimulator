package cellsociety_team11.gui;

public enum SimulationType{
	
	GAMEOFLIFE ("Game of Life", GameOfLifeDisplayCell.class),
	SEGREGATION ("Schelling's Model of Segregation", null),
	SPREADING_OF_FIRE ("Spreading of Fire", null),
	PREDATOR_PREY ("Predator Prey", null);
	
	
	private final String title;
	//private final Class<? extends Grid<?>> gridClass; 
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
