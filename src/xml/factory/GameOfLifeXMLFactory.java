package xml.factory;

import xml.model.SimulationXMLModel;
import xml.model.GameOfLifeXMLModel;
import org.w3c.dom.Element;


/**
 * Creates Professor object from an XML file.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class GameOfLifeXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_NAME = "Game of Life";


    /**
     * Create factory capable of generating Professor objects.
     */
    public GameOfLifeXMLFactory () {
        super(XML_TAG_NAME);
    }

    @Override
    public SimulationXMLModel getSimulation (Element root) throws XMLFactoryException {
        if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimulationType());
        }
        // BUGBUG: hard coding tagNames is a bad idea
        String name = getTextValue(root, "name");
        String author = getTextValue(root, "author");
        String dimensions = getTextValue(root, "dimensions");
        String initialLayout = getTextValue(root, "initialLayout");
        return new GameOfLifeXMLModel(name, author, dimensions, initialLayout);
    }
}
