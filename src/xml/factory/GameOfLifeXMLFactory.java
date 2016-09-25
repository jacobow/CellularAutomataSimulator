package xml.factory;

import xml.model.GameOfLifeXMLModel;
import org.w3c.dom.Element;


/**
 * Creates Game of Life object from an XML file.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class GameOfLifeXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_TYPE = "Simulation";
    private static final String XML_TAG_NAME = "name";
    private static final String XML_TAG_TITLE = "title";
    private static final String XML_TAG_AUTHOR = "author";
    private static final String XML_TAG_ROWS = "rows";
    private static final String XML_TAG_COLUMNS = "columns";
    private static final String XML_TAG_INITIAL_LAYOUT = "initialLayout";

    /**
     * Create factory capable of generating Game of Life objects.
     */
    public GameOfLifeXMLFactory () {
        super(XML_TAG_TYPE);
    }

    @Override
    public GameOfLifeXMLModel getSimulation (Element root) throws XMLFactoryException {
        if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimulationType());
        }
        String name = getTextValue(root, XML_TAG_NAME);
        String title = getTextValue(root, XML_TAG_TITLE);
        String author = getTextValue(root, XML_TAG_AUTHOR);
        String rows = getTextValue(root, XML_TAG_ROWS);
        String columns = getTextValue(root, XML_TAG_COLUMNS);
        String initialLayout = getTextValue(root, XML_TAG_INITIAL_LAYOUT);
        return new GameOfLifeXMLModel(name, title, author, rows, columns, initialLayout);
    }
}
