package xml.factory;

import xml.model.PredatorPreyXMLModel;
import org.w3c.dom.Element;


/**
 * Creates Professor object from an XML file.
 *
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class PredatorPreyXMLFactory extends SimulationXMLFactory {
    private static final String XML_TAG_NAME = "Predator Prey";


    /**
     * Create factory capable of generating Professor objects.
     */
    public PredatorPreyXMLFactory () {
        super(XML_TAG_NAME);
    }

    @Override
    public PredatorPreyXMLModel getSimulation (Element root) throws XMLFactoryException {
        if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimulationType());
        }
        // BUGBUG: hard coding tagNames is a bad idea
        String name = getTextValue(root, "name");
        String author = getTextValue(root, "author");
        String rows = getTextValue(root, "rows");
        String columns = getTextValue(root, "columns");
        String initialLayout = getTextValue(root, "initialLayout");
        String preyBreedingSpan = getTextValue(root, "preyBreedingSpan");
        String predatorBreedingSpan = getTextValue(root, "predatorBreedingSpan");
        String predatorLifeSpan = getTextValue(root, "predatorLifeSpan");
        return new PredatorPreyXMLModel(name, author, rows, columns, initialLayout, preyBreedingSpan, predatorBreedingSpan, predatorLifeSpan);
    }
}
