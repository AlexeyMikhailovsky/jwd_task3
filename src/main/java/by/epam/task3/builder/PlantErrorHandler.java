package by.epam.task3.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class PlantErrorHandler implements ErrorHandler {

    static Logger logger = LogManager.getLogger();

    public void warning(SAXParseException e){
        logger.warn(getErrorPosition(e) + " - " + e.getMessage());
    }

    public void error(SAXParseException e){
        logger.error(getErrorPosition(e) + " - " + e.getMessage());
    }

    public void fatalError(SAXParseException e){
        logger.fatal(getErrorPosition(e) + " - " + e.getMessage());
    }

    private String getErrorPosition(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }

}
