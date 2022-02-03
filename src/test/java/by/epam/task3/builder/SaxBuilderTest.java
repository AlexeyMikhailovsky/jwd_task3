package by.epam.task3.builder;

import by.epam.task3.entity.*;
import by.epam.task3.exception.PlantException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.Set;

import static by.epam.task3.entity.Color.GREEN;
import static by.epam.task3.entity.Color.PURPLE;

public class SaxBuilderTest {

    SaxBuilder plantsBuilder;

    @BeforeTest
    public void init() {
        plantsBuilder =new SaxBuilder();
    }

    @Test
    public void testBuildSetGems() throws PlantException {
        String xmlPath="resources/flowers.xml";
        plantsBuilder.buildSetPlants(xmlPath);
        Set<Plant> actual= plantsBuilder.getPlants();

        Flower expected=new Flower();
        expected.setName("Primrose");
        expected.setOrigin("USA");
        expected.setId("plant-1");
        VisualParameters visualParameter=new VisualParameters();
        visualParameter.setSize("15 cm");
        visualParameter.setLeafColor(GREEN);
        visualParameter.setStemColor(GREEN);
        expected.setParameters(visualParameter);
        expected.setPetalNumber(6);
        expected.setMultiplying(Multiplying.SEEDS);
        expected.setSoil(Soil.PODZOLIC);
        GrowingTips tips = new GrowingTips();
        tips.setWatering("100 ml/w");
        tips.setLighting("photophilous");
        tips.setTemperature("25 C");
        expected.setGrowingTips(tips);

        Assert.assertTrue(actual.contains(expected));

    }

    @Test (expectedExceptions = PlantException.class, expectedExceptionsMessageRegExp = ".*Error in Sax:.*")
    public void testBuildSetGemsError() throws PlantException {
        String xmlPath="resources/plantsNone.xml";

        plantsBuilder.buildSetPlants(xmlPath);
    }
}
