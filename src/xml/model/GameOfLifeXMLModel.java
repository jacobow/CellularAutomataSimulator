package xml.model;

import xml.model.SimulationXMLModel;

/**
 * CAXMLModel object for Game of Life.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class GameOfLifeXMLModel extends SimulationXMLModel {
    private String myDimensions;
    private String myInitialLayout;

    public GameOfLifeXMLModel (String name, String author, String dimensions, String initialLayout) {
        super(name, author);
        myDimensions = dimensions;
        myInitialLayout = initialLayout;
    }

    public String getClassTaught () {
        return myDimensions;
    }

    public String getOfficeLocation () {
        return myInitialLayout;
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("Game of Life {")
              .append("Name='").append(getName()).append("', ")
              .append("Author='").append(getAuthor()).append("', ")
              .append("Dimensions='").append(myDimensions).append("', ")
              .append("Initial Layout='").append(myInitialLayout)
              .append('}');
       return result.toString();
    }
}
