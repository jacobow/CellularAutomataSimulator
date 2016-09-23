package cellsociety_team11;

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
