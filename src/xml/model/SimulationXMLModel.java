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
    private String myRows;

    private String myColumns;

    public SimulationXMLModel (String name, String author, String rows, String columns) {
        myName = name;
        myAuthor = author;
        myRows = rows;
        myColumns = columns;
    }

    public String getName () {
        return myName;
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
}

