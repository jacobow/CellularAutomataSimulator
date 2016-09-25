package xml.main;
import java.io.File;
import java.util.Arrays;
import xml.model.GameOfLifeXMLModel;
import xml.model.SpreadingOfFireXMLModel;
import xml.model.SimulationXMLModel;
import xml.parser.XMLParser;
import xml.factory.SimulationXMLFactory;
import xml.factory.GameOfLifeXMLFactory;
import xml.factory.SpreadingOfFireXMLFactory;
import xml.factory.XMLFactoryException;


/**
 * @author Rhondu Smithwick
 * @author Robert Duvall
 */
public class Main {
    private static final String XML_FILES_LOCATION = "data/CA_xml/";
    private static final String XML_SUFFIX = ".xml";


    public static void main (String[] args) {
        XMLParser parser = new XMLParser();
        SpreadingOfFireXMLFactory factory = new SpreadingOfFireXMLFactory();
        File folder = new File(XML_FILES_LOCATION);
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
        File f = new File("data/CA_xml/SpreadingOfFire.xml");
        if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
            try {
                SpreadingOfFireXMLModel p = factory.getSimulation(parser.getRootElement(f.getAbsolutePath()));
                for (int i=0; i<p.getRows(); i++){
                    System.out.println(Arrays.toString(p.getInitialLayout()[i]));
                }
                System.out.println(p);
            }
            catch (XMLFactoryException e) {
                System.err.println("Reading file " + f.getPath());
                e.printStackTrace();
            }
        }
    }
}
