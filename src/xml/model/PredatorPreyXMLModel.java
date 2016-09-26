package xml.model;

import xml.model.SimulationXMLModel;

/**
 * SimulationXMLModel object for Predator Prey.
 */
public class PredatorPreyXMLModel extends SimulationXMLModel {
    private String myInitialLayout;
    private String myPreyBreedingSpan;
    private String myPredatorBreedingSpan;
    private String myPredatorLifeSpan;

    public PredatorPreyXMLModel (String name, String title, String author, String rows, String columns, String initialLayout, String preyBreedingSpan, 
                                 String predatorBreedingSpan, String predatorLifeSpan) {
        super(name, title, author, rows, columns);
        myInitialLayout = initialLayout;
        myPreyBreedingSpan = preyBreedingSpan;
        myPredatorBreedingSpan = predatorBreedingSpan;
        myPredatorLifeSpan = predatorLifeSpan;
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
    public String getMyPreyBreedingSpan () {
        return myPreyBreedingSpan;
    }

    @Override
    public String getMyPredatorBreedingSpan () {
        return myPredatorBreedingSpan;
    }

    @Override
    public String getMyPredatorLifeSpan () {
        return myPredatorLifeSpan;
    }

    @Override
    public String toString () {        
        StringBuilder result = new StringBuilder();
        result.append("Predator Prey {")
              .append("Name='").append(getSimulationName()).append("', ")
              .append("Author='").append(getAuthor()).append("', ")
              .append("Rows='").append(getRows()).append("', ")
              .append("Columns='").append(getColumns()).append("', ")
              .append("PreyBreedingSpan='").append(myPreyBreedingSpan).append("', ")
              .append("PredatorBreedingSpan='").append(myPredatorBreedingSpan).append("', ")
              .append("PredatorLifeSpan='").append(myPredatorLifeSpan)
              .append('}');
       return result.toString();
    }
}
