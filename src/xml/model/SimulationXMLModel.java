package xml.model;

/**
 * A value object for a simulation.
 *
 * @author Noel Moon
 */
public class SimulationXMLModel {
    private static final String BLANK_STR = "";
    
    private String mySimulationName;
    private String myTitle;
    private String myAuthor;
    private String myRows;
    private String myColumns;
    private String myShape;
    private String myWorld;
    private String isRandomInitialLayout;
    private String myInitialLayout;
    private String myPreyBreedingSpan;
    private String myPredatorBreedingSpan;
    private String myPredatorLifeSpan;
    private String myProbability;

    public SimulationXMLModel (String simulationName) {
        mySimulationName = simulationName;
    }
    
    public SimulationXMLModel (String simulationName, String title, String author, String rows, String columns, String shape, String world,
                               String isRandomInitialLayout,
                               String initialLayout) {
        this(simulationName, title, author, rows, columns, shape, world, isRandomInitialLayout, initialLayout, BLANK_STR, BLANK_STR, BLANK_STR, BLANK_STR);
    }
    
    public SimulationXMLModel (String simulationName, String title, String author, String rows, String columns, String shape, String world,
                               String isRandomInitialLayout,
                               String initialLayout,
                               String probability) {
        this(simulationName, title, author, rows, columns, shape, world, isRandomInitialLayout, initialLayout, probability, BLANK_STR, BLANK_STR, BLANK_STR);
    }
    
    public SimulationXMLModel(String simulationName, String title, String author, String rows, String columns, String shape, String world,
                              String isRandomInitialLayout,
                              String initialLayout, 
                              String preyBreedingSpan, String predatorBreedingSpan, String predatorLifeSpan){
        this(simulationName, title, author, rows, columns, shape, world, isRandomInitialLayout, initialLayout, BLANK_STR, preyBreedingSpan, predatorBreedingSpan, predatorLifeSpan);
    }
    
    public SimulationXMLModel(String simulationName, String title, String author, String rows, String columns, String shape, String world,
                              String isRandomInitialLayout,
                              String initialLayout, String probability, String preyBreedingSpan, String predatorBreedingSpan, 
                              String predatorLifeSpan){
        mySimulationName = simulationName;
        myTitle = title;
        myAuthor = author;
        myRows = rows;
        myColumns = columns;
        this.isRandomInitialLayout = isRandomInitialLayout;
        myInitialLayout = initialLayout;
        myProbability = probability;
        myPreyBreedingSpan = preyBreedingSpan;
        myPredatorBreedingSpan = predatorBreedingSpan;
        myPredatorLifeSpan = predatorLifeSpan;
    }
    
    public String getSimulationName () {
        return mySimulationName;
    }

    public String getTitle () {
        return myTitle;
    }
    
    public String getAuthor () {
        return myAuthor;
    }
    
    public int getRows () {
        return Integer.parseInt(myRows);
    }

    public int getColumns () {
        return Integer.parseInt(myColumns);
    }
    
    public String getMyShape () {
        return myShape;
    }

    public String getMyWorld () {
        return myWorld;
    }
    
    public Integer[][] getInitialLayout () {
        if (Boolean.parseBoolean(isRandomInitialLayout)) {
            return null;
        } else {
            Integer initialLayout[][] = new Integer[getRows()][getColumns()];
            int strIndex = 0;
            for (int i=0; i<getRows(); i++){
                for (int j=0; j<getColumns(); j++){
                    initialLayout[i][j] = Character.getNumericValue((myInitialLayout.charAt(strIndex)));
                    strIndex++;
                }
                strIndex++;
            }
            return initialLayout;
        }
    }
    
    public int getPreyBreedingSpan () {
        return Integer.parseInt(myPreyBreedingSpan);
    }
    
    public int getPredatorBreedingSpan () {
        return Integer.parseInt(myPredatorBreedingSpan);
    }

    public int getPredatorLifeSpan () {
        return Integer.parseInt(myPredatorLifeSpan);
    }
    
    public float getProbability () {
        return Float.parseFloat(myProbability);
    }
    
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("{ Name='").append(getSimulationName()).append("', ")
              .append("Title='").append(getTitle()).append("', ")
              .append("Author='").append(getAuthor()).append("', ")
              .append("Rows='").append(getRows()).append("', ")
              .append("Columns='").append(getColumns())
              .append('}');
       return result.toString();
    }
}

