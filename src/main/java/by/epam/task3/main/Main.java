package by.epam.task3.main;

import by.epam.task3.builder.BuilderFactory;
import by.epam.task3.builder.SaxBuilder;
import by.epam.task3.entity.Plant;
import by.epam.task3.exception.PlantException;
import by.epam.task3.validator.PlantValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Main
{
    static Logger logger = LogManager.getLogger();
    public static Set<Plant> setPlants;

    public static void main(String[] args) throws PlantException {
        String path="resources/flowers.xml";
        PlantValidator validator= new PlantValidator();
        if ( validator.validateXml(path,"resources/flowers.xsd")) {
            SaxBuilder plantsBuilder = (SaxBuilder) BuilderFactory.createPlantBuilder("Sax");
            plantsBuilder.buildSetPlants(path);
            setPlants=plantsBuilder.getPlants();
            setPlants.forEach(System.out::println);
        }
    }
}
