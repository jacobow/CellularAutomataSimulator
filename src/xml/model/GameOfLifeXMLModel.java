package xml.model;

import xml.model.SimulationXMLModel;

/**
 * CAXMLModel object for Game of Life.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class GameOfLifeXMLModel extends SimulationXMLModel {
    private String myInitialLayout;

    public GameOfLifeXMLModel (String name, String author, String initialLayout) {
        super(name, author);
        myInitialLayout = initialLayout;
    }

    public boolean[][] getInitialLayout () {
        for (int i=0; i<myInitialLayout.length(); i++){
            if (myInitialLayout.charAt(i)=='/'){
                
            }
        }
        return null;
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
