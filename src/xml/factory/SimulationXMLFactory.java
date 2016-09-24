package xml.factory;

import xml.model.SimulationXMLModel;
import org.w3c.dom.Element;
import java.util.Objects;


/**
 * An XMLFactory that gives back a simulation object.
 */
public abstract class SimulationXMLFactory extends XMLFactory {
    private String mySimulationType;
    

    /**
     * Create a factory for making simulation objects.  
     */
    protected SimulationXMLFactory (String personType) {
        mySimulationType = personType;
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
    public abstract SimulationXMLModel getSimulation (Element root) throws XMLFactoryException;

    /**
     * @see XMLFactory#isValidFile()
     */
    @Override
    protected boolean isValidFile (Element root) {
        return Objects.equals(getAttribute(root, "SimulationType"), getSimulationType());
    }
}
