package xml.factory;

import xml.model.SimulationXMLModel;
import org.w3c.dom.Element;
import java.util.Objects;


/**
 * An XMLFactory that gives back a simulation object.
 */
public class SimulationXMLFactory extends XMLFactory {
    private static final String XML_TAG_TYPE = "Simulation";
    private static final String XML_TAG_NAME = "name";
    private static final String XML_TAG_TITLE = "title";
    private static final String XML_TAG_AUTHOR = "author";
    private static final String XML_TAG_ROWS = "rows";
    private static final String XML_TAG_COLUMNS = "columns";
    private static final String XML_TAG_INITIAL_LAYOUT = "initialLayout";
    private static final String XML_TAG_PREY_BREEDING_SPAN = "preyBreedingSpan";
    private static final String XML_TAG_PREDATOR_BREEDING_SPAN = "predatorBreedingSpan";
    private static final String XML_TAG_PREDATOR_LIFE_SPAN = "predatorLifeSpan";
    private static final String XML_TAG_PROBABILITY = "probability";
    
    private String mySimulationType;
    

    /**
     * Create a factory for making simulation objects.  
     */
    public SimulationXMLFactory (String simulationType) {
        mySimulationType = simulationType;
    }
    
    public SimulationXMLFactory () {
    }

    /**
     * @return the type of simulation this file represents
     */
    public String getSimulationType () {
        return mySimulationType;
    }

    /**
     * Get the actual simulation contained in this XML File.
     */
    public SimulationXMLModel getSimulation (Element root) throws XMLFactoryException{
        if (! isValidFile(root)) {
            throw new XMLFactoryException("XML file does not represent a %s", getSimulationType());
        }
        String name = getTextValue(root, XML_TAG_NAME);
        String title = getTextValue(root, XML_TAG_TITLE);
        String author = getTextValue(root, XML_TAG_AUTHOR);
        String rows = getTextValue(root, XML_TAG_ROWS);
        String columns = getTextValue(root, XML_TAG_COLUMNS);
        String initialLayout = getTextValue(root, XML_TAG_INITIAL_LAYOUT);
        if (name.equals("Predator Prey")) {
            String preyBreedingSpan = getTextValue(root, XML_TAG_PREY_BREEDING_SPAN);
            String predatorBreedingSpan = getTextValue(root, XML_TAG_PREDATOR_BREEDING_SPAN);
            String predatorLifeSpan = getTextValue(root, XML_TAG_PREDATOR_LIFE_SPAN);
            return new SimulationXMLModel(name, title, author, rows, columns, initialLayout, preyBreedingSpan, predatorBreedingSpan, predatorLifeSpan);
        } if (name.equals("Segregation") || name.equals("Spreading of Fire")) {
            String probability = getTextValue(root, XML_TAG_PROBABILITY);
            return new SimulationXMLModel(name, title, author, rows, columns, initialLayout, probability);
        } else {
            return new SimulationXMLModel(name, title, author, rows, columns, initialLayout);
        }
    }

    /**
     * @see XMLFactory#isValidFile()
     */
    @Override
    protected boolean isValidFile (Element root) {
        return Objects.equals(getAttribute(root, "SimulationType"), getSimulationType());
    }
}
