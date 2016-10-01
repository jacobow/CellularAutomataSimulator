package xml.model;

import java.util.Arrays;

/**
 * A value object for a simulation.
 *
 * @author Noel Moon
 */
public class SimulationXMLModel {
    
    private String mySimulationName;
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
    private String myCellTypeQuantities;

    public SimulationXMLModel (String simulationName) {
        mySimulationName = simulationName;
    }
    
    public SimulationXMLModel(String simulationName, String author, String rows, String columns, String shape, String world,
                              String isRandomInitialLayout,
                              String initialLayout, String probability,
                              String preyBreedingSpan, String predatorBreedingSpan, 
                              String predatorLifeSpan, String cellTypeQuantities){
        mySimulationName = simulationName;
        myAuthor = author;
        myRows = rows;
        myColumns = columns;
        myShape = shape;
        myWorld = world; 
        this.isRandomInitialLayout = isRandomInitialLayout;
        myInitialLayout = initialLayout;
        myProbability = probability;
        myPreyBreedingSpan = preyBreedingSpan;
        myPredatorBreedingSpan = predatorBreedingSpan;
        myPredatorLifeSpan = predatorLifeSpan;
        myCellTypeQuantities = cellTypeQuantities;
    }
    
    public String getSimulationName () {
        return mySimulationName;
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
    
    public String getShape () {
        return myShape;
    }

    public String getWorld () {
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
    
    public Integer[] getCellTypeQuantities () {
        int numCellTypes = 1;
        for (int i=0; i<myCellTypeQuantities.length(); i++) {
            if (myCellTypeQuantities.charAt(i) == '/') {
                numCellTypes++;
            }
        }
        Integer[] result = new Integer[numCellTypes];
        int index = 0;
        for (int i=0; i<numCellTypes; i++) {
            result[i] = Integer.parseInt(myCellTypeQuantities.substring(index, index+2));
            index = index + 4;
        }
        return result;
    }
    
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("{ Name='").append(getSimulationName()).append("', ")
              .append("Author='").append(getAuthor()).append("', ")
              .append("Rows='").append(getRows()).append("', ")
              .append("Columns='").append(getColumns()).append(" , ")
              .append("Shape= ").append(getShape()).append(" , ")
              .append("World= ").append(getWorld()).append(" , ")
              .append('}');
       return result.toString();
    }
}

