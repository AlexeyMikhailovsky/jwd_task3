package by.epam.task3.builder;

import by.epam.task3.exception.PlantException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxBuilder extends PlantBuilder{

    private static Logger logger = LogManager.getLogger();
    private PlantHandler handler = new PlantHandler();
    private XMLReader reader;

    public SaxBuilder() {
        super();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Error in Sax: " + e.getMessage());

        }
        reader.setErrorHandler(new PlantErrorHandler());
        reader.setContentHandler(handler);
    }

    @Override
    public void buildSetPlants(String filename) throws PlantException {
        try {
            reader.parse(filename);
        } catch (IOException e) {
            logger.error("IO error : " + filename,e);
            throw new PlantException("IO error : " + filename,e);
        } catch (SAXException e) {
            logger.error("Error in Sax: " + e.getMessage());
            throw new PlantException("Error in Sax: " + e.getMessage());
        }
        plants = handler.getPlants();
    }
}
