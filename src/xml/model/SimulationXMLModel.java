package xml.model;

/**
 * A value object for a CA.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class SimulationXMLModel {
    private String myName;
    private String myAuthor;

    public SimulationXMLModel (String name, String author) {
        myName = name;
        myAuthor = author;
    }

    public String getName () {
        return myName;
    }
    
    public String getAuthor () {
        return myAuthor;
    }
}

