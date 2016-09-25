package xml.factory;

import xml.model.SpreadingOfFireXMLModel;
import org.w3c.dom.Element;


/**
 * Creates SpreadingOfFire object from an XML file.
 */
public class SpreadingOfFireXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_NAME = "Spreading of Fire";
    private static final String XML_TAG_TITLE = "name";
    private static final String XML_TAG_AUTHOR = "author";
    private static final String XML_TAG_ROWS = "rows";
    private static final String XML_TAG_COLUMNS = "columns";
    private static final String XML_TAG_INITIAL_LAYOUT = "initialLayout";

    /**
     * Create factory capable of generating SpreadingOfFire objects.
     */
    public SpreadingOfFireXMLFactory () {
        super(XML_TAG_NAME);
    }

    @Override
    public SpreadingOfFireXMLModel getSimulation (Element root) throws XMLFactoryException {
        if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimulationType());
        }
        String name = getTextValue(root, XML_TAG_TITLE);
        String author = getTextValue(root, XML_TAG_AUTHOR);
        String rows = getTextValue(root, XML_TAG_ROWS);
        String columns = getTextValue(root, XML_TAG_COLUMNS);
        String initialLayout = getTextValue(root, XML_TAG_INITIAL_LAYOUT);
        return new SpreadingOfFireXMLModel(name, author, rows, columns, initialLayout);
    }
}
