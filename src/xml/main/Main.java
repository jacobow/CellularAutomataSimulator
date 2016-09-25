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
    private static final String XML_FILE_LOCATION = "data/CA_xml/GameOfLife2.xml";
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
                if (model.getSimulationName().equals("Game of Life")){
                    Boolean[][] initialLayout = new Boolean[model.getRows()][model.getColumns()];
                    for (int i=0; i<model.getRows(); i++){
                        for (int j=0; j<model.getColumns(); j++){
                            initialLayout[i][j] = (model.getInitialLayout()[i][j] == 1);
                        }
                    }
                    System.out.println(Arrays.toString(initialLayout[1]));
                    
                }
                
            }
            catch (XMLFactoryException e) {
                System.err.println("Reading file " + f.getPath());
                e.printStackTrace();
            }
        }
    }
}
