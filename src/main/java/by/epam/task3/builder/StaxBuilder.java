package by.epam.task3.builder;

import by.epam.task3.entity.*;
import by.epam.task3.exception.PlantException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;

public class StaxBuilder extends PlantBuilder{

    private static Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public StaxBuilder() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildSetPlants(String filename) throws PlantException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(PlantXmlTag.FLOWER.getTitle())) {
                        Flower flower = buildFlower(reader);
                        plants.add(flower);
                    } else if (name.equals(PlantXmlTag.TREE.getTitle())) {
                        Tree tree = buildTree(reader);
                        plants.add(tree);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException ex) {
            logger.error("Error in Stax: " + ex.getMessage());
            throw new PlantException("Error in Stax: "+ ex.getMessage());
        } catch (IOException ex) {
            logger.error("IO error : " + filename, ex);
            throw new PlantException("IO error : " + filename, ex);
        }
    }

    private Flower buildFlower(XMLStreamReader reader) throws XMLStreamException {
        Flower flower = new Flower();
        String name_of_flower = String.valueOf(reader.getAttributeValue(null, PlantXmlTag.NAME.getTitle()).toUpperCase());
        flower.setName(name_of_flower);
        build(reader, flower);
        return flower;
    }

    private Tree buildTree(XMLStreamReader reader) throws XMLStreamException {
        Tree tree = new Tree();
        tree.setName(reader.getAttributeValue(null, PlantXmlTag.NAME.getTitle()));
        tree.setCrownWidth(reader.getAttributeValue(null, PlantXmlTag.CROWN_WIDTH.getTitle()));
        build(reader, tree);
        return tree;
    }

    private void build(XMLStreamReader reader, Plant plant) throws XMLStreamException {
        plant.setOrigin(reader.getAttributeValue(null, PlantXmlTag.ORIGIN.getTitle()));
        plant.setId(reader.getAttributeValue(null, PlantXmlTag.ID.getTitle()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case VISUAL_PARAMETERS:
                            plant.setParameters(getXMLParameters(reader));
                            break;
                        case GROWING_TIPS:
                            plant.setGrowingTips(getXMLTips(reader));
                            break;
                        case SOIL:
                            plant.setSoil(Soil.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case MULTIPLYING:
                            plant.setMultiplying(Multiplying.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_")) == PlantXmlTag.FLOWER ||
                            PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_")) == PlantXmlTag.TREE) {
                        return;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <flowers>");
    }

    private VisualParameters getXMLParameters(XMLStreamReader reader) throws XMLStreamException {
        VisualParameters parameters = new VisualParameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case STEM_COLOR:
                            parameters.setStemColor(Color.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case LEAF_COLOR:
                            parameters.setLeafColor(Color.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case AVERAGE_SIZE:
                            parameters.setSize(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_")) == PlantXmlTag.VISUAL_PARAMETERS) {
                        return parameters;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <visual-parameters>");
    }

    private GrowingTips getXMLTips(XMLStreamReader reader) throws XMLStreamException {
        GrowingTips tips = new GrowingTips();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case TEMPERATURE:
                            tips.setTemperature(getXMLText(reader));
                            break;
                        case LIGHTING:
                            tips.setLighting(getXMLText(reader));
                            break;
                        case WATERING:
                            tips.setWatering(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_")) == PlantXmlTag.GROWING_TIPS) {
                        return tips;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <growing-tips>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
