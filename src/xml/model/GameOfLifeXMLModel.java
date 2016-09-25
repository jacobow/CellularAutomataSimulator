package xml.model;

import xml.model.SimulationXMLModel;

/**
 * SimulationXMLModel object for Game of Life.
 */
public class GameOfLifeXMLModel extends SimulationXMLModel {
    private String myInitialLayout;

    public GameOfLifeXMLModel (String name, String title, String author, String rows, String columns, String initialLayout) {
        super(name, title, author, rows, columns);
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
              .append("Name='").append(getSimulationName()).append("', ")
              .append("Author='").append(getAuthor()).append("', ")
              .append("Rows='").append(getRows()).append("', ")
              .append("Columns='").append(getColumns()).append("', ")
              .append('}');
       return result.toString();
    }
}
