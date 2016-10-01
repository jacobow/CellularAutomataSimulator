package xml.main;
import java.io.File;
import java.util.Arrays;
import xml.model.SimulationXMLModel;
import xml.parser.XMLParser;
import xml.factory.SimulationXMLFactory;
import xml.factory.XMLFactoryException;


/**
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class Main {
    private static final String XML_FILE_LOCATION = "data/CA_xml/GameOfLife.xml";
    private static final String XML_SUFFIX = ".xml";

    public static void main (String[] args) {
        XMLParser parser = new XMLParser();
        SimulationXMLFactory factory = new SimulationXMLFactory("Simulation");
        /*
        for (File f : folder.listFiles()) {
            if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
                try {
                    SimulationXMLModel p = factory.getSimulation(parser.getRootElement(f.getAbsolutePath()));
                    System.out.println(p);
                }
                catch (XMLFactoryException e) {
                    System.err.println("Reading file " + f.getPath());
                    e.printStackTrace();
                }
            }
        }
        */
        File f = new File(XML_FILE_LOCATION);
        if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
            try {
                SimulationXMLModel model = factory.getSimulation(parser.getRootElement(f.getAbsolutePath()));
                for (int i=0; i<model.getRows(); i++){
                    System.out.println(Arrays.toString(model.getInitialLayout()[i]));
                }
                System.out.println(model);
                System.out.println(Arrays.toString(model.getCellTypeQuantities()));
            }
            catch (XMLFactoryException e) {
                System.err.println("Reading file " + f.getPath());
                e.printStackTrace();
            }
        }
    }
}
