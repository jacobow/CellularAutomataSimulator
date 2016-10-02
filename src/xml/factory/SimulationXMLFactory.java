package xml.factory;

import xml.model.SimulationXMLModel;
import org.w3c.dom.Element;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * An XMLFactory that gives back a simulation object.
 * 
 * @author Noel Moon
 */
public class SimulationXMLFactory extends XMLFactory {
    private static final String XML_RESOURCES = "resources.XMLResources";
    
    private ResourceBundle myResources;
    private String myXMLType;

    /**
     * Create a factory for making simulation objects.  
     */
    public SimulationXMLFactory (String XMLType) {
        myResources = ResourceBundle.getBundle(XML_RESOURCES);
        myXMLType = XMLType;
    }

    /**
     * @return the type of simulation this file represents
     */
    public String getSimulationType () {
        return myXMLType;
    }

    /**
     * Get the actual simulation contained in the XML File.
     */
    public SimulationXMLModel getSimulation (Element root) throws XMLFactoryException{
        if (! isValidFile(root)) {
            throw new XMLFactoryException(myResources.getString("NotValidFile"), getSimulationType());
        }
        String name = getTextValue(root, myResources.getString("XMLTagName"));
        String author = getTextValue(root, myResources.getString("XMLTagAuthor"));
        String rows = getTextValue(root, myResources.getString("XMLTagRows"));
        String columns = getTextValue(root, myResources.getString("XMLTagColumns"));
        String shape = getTextValue(root, myResources.getString("XMLTagShape"));
        String world = getTextValue(root, myResources.getString("XMLTagWorld"));
        String isRandomInitialLayout = getTextValue(root, myResources.getString("XMLTagIsRandomInitialLayout"));
        String initialLayout = getTextValue(root, myResources.getString("XMLTagInitialLayout"));
        String probability = getTextValue(root, myResources.getString("XMLTagProbability"));
        String preyBreedingSpan = getTextValue(root, myResources.getString("XMLTagPreyBreedingSpan"));
        String predatorBreedingSpan = getTextValue(root, myResources.getString("XMLTagPredatorBreedingSpan"));
        String predatorLifeSpan = getTextValue(root, myResources.getString("XMLTagPredatorLifeSpan"));
        String cellTypeQuantities = getTextValue(root, myResources.getString("XMLTagCellTypeQuantities"));
        return new SimulationXMLModel(name, author, rows, columns, shape, world, isRandomInitialLayout, initialLayout, 
                                      probability, preyBreedingSpan, predatorBreedingSpan, predatorLifeSpan,  
                                      cellTypeQuantities);
    }

    /**
     * @see XMLFactory#isValidFile()
     */
    @Override
    protected boolean isValidFile (Element root) {
        return Objects.equals(getAttribute(root, myResources.getString("XMLTagXMLType")), getSimulationType());
    }
}