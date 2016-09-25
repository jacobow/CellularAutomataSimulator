package xml.model;

/**
 * A value object for a CA.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class SimulationXMLModel {
    private String mySimulationName;
    private String myTitle;
    private String myAuthor;
    private String myRows;
    private String myColumns;
    private String myInitialLayout;
    private String myPreyBreedingSpan;
    private String myPredatorBreedingSpan;
    private String myPredatorLifeSpan;

    public SimulationXMLModel (String simulationName) {
        mySimulationName = simulationName;
    }
    
    public SimulationXMLModel (String simulationName, String title, String author, String rows, String columns, String initialLayout) {
        mySimulationName = simulationName;
        myTitle = title;
        myAuthor = author;
        myRows = rows;
        myColumns = columns;
        myInitialLayout = initialLayout;
    }
    
    public SimulationXMLModel(String simulationName, String title, String author, String rows, String columns, String initialLayout, 
                              String preyBreedingSpan, String predatorBreedingSpan, String predatorLifeSpan){
        mySimulationName = simulationName;
        myTitle = title;
        myAuthor = author;
        myRows = rows;
        myColumns = columns;
        myInitialLayout = initialLayout;
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
    
    public Integer[][] getInitialLayout () {
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
    
    public int getPreyBreedingSpan () {
        return Integer.parseInt(myPreyBreedingSpan);
    }
    
    public int getPredatorBreedingSpan () {
        return Integer.parseInt(myPredatorBreedingSpan);
    }

    public int getPredatorLifeSpan () {
        return Integer.parseInt(myPredatorLifeSpan);
    }
    
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("{ Name='").append(getSimulationName()).append("', ")
              .append("Title='").append(getTitle()).append("', ")
              .append("Author='").append(getAuthor()).append("', ")
              .append("Rows='").append(getRows()).append("', ")
              .append("Columns='").append(getColumns()).append("', ")
              .append('}');
       return result.toString();
    }
}

