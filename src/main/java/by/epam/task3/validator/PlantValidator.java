package by.epam.task3.validator;

import by.epam.task3.builder.PlantErrorHandler;
import by.epam.task3.exception.PlantException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class PlantValidator {

    private static final PlantValidator instance = new PlantValidator();

    static Logger logger = LogManager.getLogger();

    public PlantValidator() {
    }

    public static PlantValidator getInstance() {
        return instance;
    }

    public static boolean validateXml(String xmlPath,String schemaPath) throws PlantException {

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaPath);

        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlPath);
            validator.setErrorHandler(new PlantErrorHandler());
            validator.validate(source);

        } catch (IOException e) {
            logger.error("IO exception : " + e.getMessage());
            throw new PlantException(e);
        } catch (SAXException e) {
            logger.error("Not valid Xml file : " + xmlPath );
            return false;
        }

        return true;
    }

}
