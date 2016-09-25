package xml.model;

import xml.model.SimulationXMLModel;

/**
 * CAXMLModel object for Game of Life.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class PredatorPreyXMLModel extends SimulationXMLModel {
    private String myInitialLayout;

    public PredatorPreyXMLModel (String name, String author, String rows, String columns, String initialLayout) {
        super(name, author, rows, columns);
        myInitialLayout = initialLayout;
    }

    public Boolean[][] getInitialLayout () {
        Boolean initialLayout[][] = new Boolean[getRows()][getColumns()];
        int strIndex = 0;
        for (int i=0; i<getRows(); i++){
            for (int j=0; j<getColumns(); j++){
                if (myInitialLayout.charAt(strIndex) == '1'){
                    initialLayout[i][j] = true;
                } else {
                    initialLayout[i][j] = false;
                }
                strIndex++;
            }
            strIndex++;
        }
        return initialLayout;
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Game of Life {")
              .append("Name='").append(getName()).append("', ")
              .append("Author='").append(getAuthor()).append("', ")
              .append("Initial Layout='").append(myInitialLayout)
              .append('}');
       return result.toString();
    }
}
