package xml.model;

import xml.model.SimulationXMLModel;

/**
 * SimulationXMLModel object for Spreading of Fire.
 */
public class SpreadingOfFireXMLModel extends SimulationXMLModel {
    private String myInitialLayout;

    public SpreadingOfFireXMLModel (String name, String title, String author, String rows, String columns, String initialLayout) {
        super(name, title, author, rows, columns);
        myInitialLayout = initialLayout;
    }

    public Integer[][] getInitialLayout () {
        Integer initialLayout[][] = new Integer[getRows()][getColumns()];
        int strIndex = 0;
        for (int i=0; i<getRows(); i++){
            for (int j=0; j<getColumns(); j++){
                if (myInitialLayout.charAt(strIndex) == '0'){
                    initialLayout[i][j] = 0;
                } else if (myInitialLayout.charAt(strIndex) == '1') {
                    initialLayout[i][j] = 1;
                } else {
                    initialLayout[i][j] = 2;
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
        result.append("Spreading of Fire {")
              .append("Name='").append(getSimulationName()).append("', ")
              .append("Author='").append(getAuthor()).append("', ")
              .append("Rows='").append(getRows()).append("', ")
              .append("Columns='").append(getColumns()).append("', ")
              .append('}');
       return result.toString();
    }
}
