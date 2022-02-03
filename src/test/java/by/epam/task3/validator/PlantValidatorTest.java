package by.epam.task3.validator;

import by.epam.task3.exception.PlantException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlantValidatorTest {
    @Test
    public void testValidateXML() throws PlantException {
        PlantValidator validator = new PlantValidator();
        boolean actual  = validator.validateXml("resources/flowers.xml","resources/flowers.xsd");
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidateXMLError() throws PlantException {
        PlantValidator validator = new PlantValidator();
        boolean actual  = validator.validateXml("resources/flowers.xml","resources/flowers.xsd");
        Assert.assertTrue(actual,"There are multiple occurrences of ID value");
    }

    @Test
    public void testValidateXMLFatal() throws PlantException {
        PlantValidator validator = new PlantValidator();
        boolean actual  = validator.validateXml("resources/flowers.xml","resources/flowers.xsd");
        Assert.assertFalse(actual,"FATAL PlantErrorHandler");
    }
}
