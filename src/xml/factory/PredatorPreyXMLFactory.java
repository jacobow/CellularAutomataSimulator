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
    private static final String XML_TAG_TITLE = "name";
    private static final String XML_TAG_AUTHOR = "author";
    private static final String XML_TAG_ROWS = "rows";
    private static final String XML_TAG_COLUMNS = "columns";
    private static final String XML_TAG_INITIAL_LAYOUT = "initialLayout";
    private static final String XML_TAG_PREY_BREEDING_SPAN = "preyBreedingSpan";
    private static final String XML_TAG_PREDATOR_BREEDING_SPAN = "predatorBreedingSpan";
    private static final String XML_TAG_PREDATOR_LIFE_SPAN = "predatorLifeSpan";

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
        String name = getTextValue(root, XML_TAG_TITLE);
        String author = getTextValue(root, XML_TAG_AUTHOR);
        String rows = getTextValue(root, XML_TAG_ROWS);
        String columns = getTextValue(root, XML_TAG_COLUMNS);
        String initialLayout = getTextValue(root, XML_TAG_INITIAL_LAYOUT);
        String preyBreedingSpan = getTextValue(root, XML_TAG_PREY_BREEDING_SPAN);
        String predatorBreedingSpan = getTextValue(root, XML_TAG_PREDATOR_BREEDING_SPAN);
        String predatorLifeSpan = getTextValue(root, XML_TAG_PREDATOR_LIFE_SPAN);
        return new PredatorPreyXMLModel(name, author, rows, columns, initialLayout, preyBreedingSpan, predatorBreedingSpan, predatorLifeSpan);
    }
}
