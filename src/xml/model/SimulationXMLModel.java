package xml.model;

import java.util.ResourceBundle;
import xml.factory.XMLFactoryException;

/**
 * A value object for a simulation.
 *
 * @author Noel Moon
 */
public class SimulationXMLModel {
    private static final String XML_RESOURCES = "resources.XMLResources";
    
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
    private ResourceBundle myResources;

    public SimulationXMLModel (String simulationName) {
        myResources = ResourceBundle.getBundle(XML_RESOURCES);
        mySimulationName = simulationName;
    }
    
    public SimulationXMLModel(String simulationName, String author, String rows, String columns, String shape, String world,
                              String isRandomInitialLayout,
                              String initialLayout, String probability,
                              String preyBreedingSpan, String predatorBreedingSpan, 
                              String predatorLifeSpan, String cellTypeQuantities){
        myResources = ResourceBundle.getBundle(XML_RESOURCES);
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
    
    public String getSimulationName () throws XMLFactoryException {
        checkForEmptyInput(mySimulationName, myResources.getString("NoNameInput"));
        return mySimulationName;
    }
    
    private void checkForEmptyInput (String input, String errorMessage) throws XMLFactoryException {
        if (input.isEmpty()){
            throw new XMLFactoryException(errorMessage);
        }
    }
    
    public String getAuthor () throws XMLFactoryException {
        checkForEmptyInput(myAuthor, myResources.getString("NoAuthorInput"));
        return myAuthor;
    }
    
    public int getRows () throws XMLFactoryException {
        checkForEmptyInput(myRows, myResources.getString("NoRowsInput"));
        return Integer.parseInt(myRows);
    }

    public int getColumns () throws XMLFactoryException {
        checkForEmptyInput(myColumns, myResources.getString("NoColumnsInput"));
        return Integer.parseInt(myColumns);
    }
    
    public String getShape () throws XMLFactoryException {
        checkForEmptyInput(myShape, myResources.getString("NoShapeInput"));
        return myShape;
    }

    public String getWorld () throws XMLFactoryException {
        checkForEmptyInput(myWorld, myResources.getString("NoWorldInput"));
        return myWorld;
    }
    
    public Integer[][] getInitialLayout () throws XMLFactoryException {
        checkForEmptyInput(isRandomInitialLayout, myResources.getString("NoIsRandomInitialLayoutInput"));
        Integer initialLayout[][] = new Integer[getRows()][getColumns()];
        if (Boolean.parseBoolean(isRandomInitialLayout)) {
            return createRandomInitialLayout();
        } else {
            checkForEmptyInput(myInitialLayout, myResources.getString("NoInitialLayoutInput"));
            int strIndex = 0;
            for (int i=0; i<getRows(); i++){
                for (int j=0; j<getColumns(); j++){
                    if (Character.getNumericValue((myInitialLayout.charAt(strIndex))) == -1) {
                        throw new XMLFactoryException(myResources.getString("InvalidInitialLayoutInput"));
                    }
                    initialLayout[i][j] = Character.getNumericValue((myInitialLayout.charAt(strIndex)));
                    strIndex++;
                }
                strIndex++;
            }
            return initialLayout;
        }
    }

    private Integer[][] createRandomInitialLayout () throws XMLFactoryException {
        Integer initialLayout[][] = new Integer[getRows()][getColumns()];
        boolean marked[][] = new boolean[getRows()][getColumns()];
        Integer[] cellTypeQuantities = getCellTypeQuantities();
        for (int i=0; i<cellTypeQuantities.length; i++) {
            for (int j=0; j<cellTypeQuantities[i]; j++) {
                int randomRow = (int) (Math.random() * (getRows()));
                int randomCol = (int) (Math.random() * (getColumns()));
                while (marked[randomRow][randomCol]) {
                    randomRow = (int) (Math.random() * (getRows()));
                    randomCol = (int) (Math.random() * (getColumns()));
                }
                initialLayout[randomRow][randomCol] = i;
                marked[randomRow][randomCol] = true;
            }
        }
        return initialLayout;
    }
    
    private Integer[] getCellTypeQuantities () throws XMLFactoryException {
        checkForEmptyInput(myCellTypeQuantities, myResources.getString("NoCellTypeQuantitiesInput"));
        int numCellTypes = 1;
        for (int i=0; i<myCellTypeQuantities.length(); i++) {
            if (myCellTypeQuantities.charAt(i) == '/') {
                numCellTypes++;
            }
        }
        Integer[] result = new Integer[numCellTypes];
        int index = 0;
        for (int i=0; i<numCellTypes; i++) {
            result[i] = Integer.parseInt(myCellTypeQuantities.substring(index, index+3));
            index = index + 4;
        }
        return result;
    }
    
    public int getPreyBreedingSpan () throws XMLFactoryException {
        checkForEmptyInput(myPreyBreedingSpan, myResources.getString("NoPreyBreedingSpanInput"));
        return Integer.parseInt(myPreyBreedingSpan);
    }
    
    public int getPredatorBreedingSpan () throws XMLFactoryException {
        checkForEmptyInput(myPredatorBreedingSpan, myResources.getString("NoPredatorBreedingSpanInput"));
        return Integer.parseInt(myPredatorBreedingSpan);
    }

    public int getPredatorLifeSpan () throws XMLFactoryException {
        checkForEmptyInput(myPredatorLifeSpan, myResources.getString("NoPredatorLifeSpanInput"));
        return Integer.parseInt(myPredatorLifeSpan);
    }
    
    public float getProbability () throws XMLFactoryException {
        checkForEmptyInput(myProbability, myResources.getString("NoProbabilityInput"));
        return Float.parseFloat(myProbability);
    }
    
    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        try {
            result.append("{ Name='").append(getSimulationName()).append("', ")
                  .append("Author='").append(getAuthor()).append("', ")
                  .append("Rows='").append(getRows()).append("', ")
                  .append("Columns='").append(getColumns()).append(" , ")
                  .append("Shape= ").append(getShape()).append(" , ")
                  .append("World= ").append(getWorld()).append(" , ")
                  .append("initial layout= ").append(getInitialLayout())
                  .append('}');
        }
        catch (XMLFactoryException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return result.toString();
    }
}

