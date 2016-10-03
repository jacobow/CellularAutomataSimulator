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
    private String isCompletelyRandomInitialLayout;
    private String isConcentratedRandomInitialLayout;
    private String myInitialLayout;
    private String myPreyBreedingSpan;
    private String myPredatorBreedingSpan;
    private String myPredatorLifeSpan;
    private String myProbability;
    private String myCellTypeQuantities;
    private String myEvaporationFactor;
    private ResourceBundle myResources;

    public SimulationXMLModel (String simulationName) {
        myResources = ResourceBundle.getBundle(XML_RESOURCES);
        mySimulationName = simulationName;
    }
    
    public SimulationXMLModel(String simulationName, String author, String rows, String columns, String shape, String world,
                              String isCompletelyRandomInitialLayout, String isConcentratedRandomInitialLayout,
                              String initialLayout, String probability,
                              String preyBreedingSpan, String predatorBreedingSpan, 
                              String predatorLifeSpan, String cellTypeQuantities, String evaporationFactor){
        myResources = ResourceBundle.getBundle(XML_RESOURCES);
        mySimulationName = simulationName;
        myAuthor = author;
        myRows = rows;
        myColumns = columns;
        myShape = shape;
        myWorld = world;
        this.isCompletelyRandomInitialLayout = isCompletelyRandomInitialLayout;
        this.isConcentratedRandomInitialLayout = isConcentratedRandomInitialLayout;
        myInitialLayout = initialLayout;
        myProbability = probability;
        myPreyBreedingSpan = preyBreedingSpan;
        myPredatorBreedingSpan = predatorBreedingSpan;
        myPredatorLifeSpan = predatorLifeSpan;
        myCellTypeQuantities = cellTypeQuantities;
        myEvaporationFactor = evaporationFactor;
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
        checkForInvalidInput(myRows, myResources.getString("InvalidRowsInput"));
        return Integer.parseInt(myRows);
    }
    
    private void checkForInvalidInput (String input, String errorMessage) throws XMLFactoryException {
        try {
            if (Integer.parseInt(input) < 0) {
                throw new XMLFactoryException(errorMessage);
            }
        } catch (NumberFormatException e) {
            try {
                if (Float.parseFloat(input) < 0.0){
                    throw new XMLFactoryException(errorMessage);
                }
            } catch (NumberFormatException f){
                throw new XMLFactoryException(errorMessage);
            }
        }

    }

    public int getColumns () throws XMLFactoryException {
        checkForEmptyInput(myColumns, myResources.getString("NoColumnsInput"));
        checkForInvalidInput(myColumns, myResources.getString("InvalidColumnsInput"));
        return Integer.parseInt(myColumns);
    }
    
    public int getShape () throws XMLFactoryException {
        checkForEmptyInput(myShape, myResources.getString("NoShapeInput"));
        checkForInvalidInput(myShape, myResources.getString("InvalidShapeInput"));
        return Integer.parseInt(myShape);
    }

    public String getWorld () throws XMLFactoryException {
        checkForEmptyInput(myWorld, myResources.getString("NoWorldInput"));
        return myWorld;
    }
    
    public Integer[][] getInitialLayout () throws XMLFactoryException {
        checkForEmptyInput(isConcentratedRandomInitialLayout, myResources.getString("NoIsRandomInitialLayoutInput"));
        if (Boolean.parseBoolean(isCompletelyRandomInitialLayout)) {           
            return createCompletelyRandomInitialLayout();
        } else if (Boolean.parseBoolean(isConcentratedRandomInitialLayout)) {
            return createConcentratedRandomInitialLayout();
        } else {
            checkForEmptyInput(myInitialLayout, myResources.getString("NoInitialLayoutInput"));
            return createInitialLayout();
        }
    }

    private Integer[][] createCompletelyRandomInitialLayout () throws XMLFactoryException {
        Integer initialLayout[][] = new Integer[getRows()][getColumns()];
        Integer[] cellTypeQuantities = getCellTypeQuantities();
        int numCellTypes = cellTypeQuantities.length;
        for (int i=0; i<getRows(); i++){
            for (int j=0; j<getColumns(); j++){
                initialLayout[i][j] = (int) (Math.random() * numCellTypes);
            }
        }
        return initialLayout;
    }
    
    private Integer[] getCellTypeQuantities () throws XMLFactoryException {
        checkForEmptyInput(myCellTypeQuantities, myResources.getString("NoCellTypeQuantitiesInput"));
        int numCellTypes = 1;
        int sum = 0;
        for (int i=0; i<myCellTypeQuantities.length(); i++) {
            if (myCellTypeQuantities.charAt(i) == '/') {
                numCellTypes++;
            }
        }
        Integer[] result = new Integer[numCellTypes];
        int index = 0;
        for (int i=0; i<numCellTypes; i++) {
            try {
                result[i] = Integer.parseInt(myCellTypeQuantities.substring(index, index+3));
                sum += result[i];
                index = index + 4;
            } catch (NumberFormatException e) {
                throw new XMLFactoryException(myResources.getString("InvalidCellTypeQuantitiesFormat"));
            }
        }
        if (Boolean.parseBoolean(isConcentratedRandomInitialLayout) & (sum != ((getRows() * getColumns())))) {
            throw new XMLFactoryException(myResources.getString("InvalidCellTypeQuantitiesFormat"));
        }
        return result;
    }

    private Integer[][] createConcentratedRandomInitialLayout () throws XMLFactoryException {
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
    
    private Integer[][] createInitialLayout () throws XMLFactoryException {
        Integer[] cellTypeQuantities = getCellTypeQuantities();
        Integer initialLayout[][] = new Integer[getRows()][getColumns()];
        int strIndex = 0;
        for (int i=0; i<getRows(); i++){
            for (int j=0; j<getColumns(); j++){
                int value = Character.getNumericValue((myInitialLayout.charAt(strIndex)));
                if (value == -1 || value >= cellTypeQuantities.length) {
                    throw new XMLFactoryException(myResources.getString("InvalidInitialLayoutInput"));
                }
                initialLayout[i][j] = Character.getNumericValue((myInitialLayout.charAt(strIndex)));
                strIndex++;
            }
            strIndex++;
        }
        return initialLayout;
    }
    
    public int getPreyBreedingSpan () throws XMLFactoryException {
        checkForEmptyInput(myPreyBreedingSpan, myResources.getString("NoPreyBreedingSpanInput"));
        checkForInvalidInput(myPreyBreedingSpan, myResources.getString("InvalidPreyBreedingSpanInput"));
        return Integer.parseInt(myPreyBreedingSpan);
    }
    
    public int getPredatorBreedingSpan () throws XMLFactoryException {
        checkForEmptyInput(myPredatorBreedingSpan, myResources.getString("NoPredatorBreedingSpanInput"));
        checkForInvalidInput(myPredatorBreedingSpan, myResources.getString("InvalidPredatorBreedingSpanInput"));
        return Integer.parseInt(myPredatorBreedingSpan);
    }

    public int getPredatorLifeSpan () throws XMLFactoryException {
        checkForEmptyInput(myPredatorLifeSpan, myResources.getString("NoPredatorLifeSpanInput"));
        checkForInvalidInput(myPredatorLifeSpan, myResources.getString("InvalidPredatorLifeSpanInput"));
        return Integer.parseInt(myPredatorLifeSpan);
    }
    
    public float getProbability () throws XMLFactoryException {
        checkForEmptyInput(myProbability, myResources.getString("NoProbabilityInput"));
        checkForInvalidInput(myProbability, myResources.getString("InvalidProbabilityInput"));
        return Float.parseFloat(myProbability);
    }
    
    public int getEvaporationFactor () throws XMLFactoryException {
        checkForEmptyInput(myEvaporationFactor, myResources.getString("NoEvaporationFactorInput"));
        checkForInvalidInput(myEvaporationFactor, myResources.getString("InvalidEvaporationFactorInput"));
        return Integer.parseInt(myEvaporationFactor);
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
                  //.append("evap factor").append(getEvaporationFactor())
                  .append('}');
        }
        catch (XMLFactoryException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return result.toString();
    }
}

