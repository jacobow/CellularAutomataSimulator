# cellsociety 

Put your source code, resources, and property files here.

How to use XML:
1. Make a file in eclipse in the format of one of the .xml files that can be found in the data folder.
2. In order to access information in the xml file do the following: (this is an example for Spreading of Fire) (don't forget to import the necessary files)

```java
private static final String XML_SPREADING_OF_FIRE_LOCATION = "data/CA_xml/SpreadingOfFire.xml";

XMLParser parser = new XMLParser();
SpreadingOfFireXMLFactory factory = new SpreadingOfFireXMLFactory();
File f = new File(XML_SPREADING_OF_FIRE_LOCATION);
if (f.isFile() && f.getName().endsWith(XML_SUFFIX)) {
    try {
        SpreadingOfFireXMLModel p = factory.getSimulation(parser.getRootElement(f.getAbsolutePath()));
        p.getInitialLayout(); #This is the method that gets the initial layout of the grid.
    }
    catch (XMLFactoryException e) {
        System.err.println("Reading file " + f.getPath());
        e.printStackTrace();
    }
}
```
