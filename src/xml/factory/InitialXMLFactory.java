package xml.factory;

import org.w3c.dom.Element;
import xml.model.InitialXMLModel;


/**
 * Creates InitialXMLFactory object from an XML file.
 */
public class InitialXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_TYPE = "Simulation";
    private static final String XML_TAG_NAME = "name";

    /**
     * Create factory capable of generating Game of Life objects.
     */
    public InitialXMLFactory () {
        super(XML_TAG_TYPE);
    }

    @Override
    public InitialXMLModel getSimulation (Element root) throws XMLFactoryException {
        if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimulationType());
        }
        String name = getTextValue(root, XML_TAG_NAME);
        return new InitialXMLModel(name);
    }
}
