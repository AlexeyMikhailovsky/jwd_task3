package by.epam.task3.builder;

import by.epam.task3.entity.*;
import by.epam.task3.exception.PlantException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

public class DomBuilder extends PlantBuilder{

    private static Logger logger = LogManager.getLogger();
    private DocumentBuilder documentBuilder;

    @Override
    public Set<Plant> getPlants() {
        return super.getPlants();
    }

    public DomBuilder() {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error( "Error in Dom: " + e.getMessage());
        }
    }

    @Override
    public void buildSetPlants(String filename) throws PlantException {
        Document doc;
        try {
            doc = documentBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList treeList = root.getElementsByTagName("tree");
            NodeList flowerList = root.getElementsByTagName("flower");
            for (int i = 0; i < treeList.getLength(); i++) {
                Element plantElement = (Element) treeList.item(i);
                Tree tree = buildTree(plantElement);
                plants.add(tree);
            }
            for (int i = 0; i < flowerList.getLength(); i++) {
                Element plantElement = (Element) flowerList.item(i);
                Flower flower = buildFlower(plantElement);
                plants.add(flower);
            }
        } catch (IOException ex) {
            logger.error( "IO error : " + filename,ex);
            throw new PlantException("IO error : " + filename,ex);
        } catch (SAXException ex) {
            logger.error( "Error in Dom: " + ex.getMessage());
            throw new PlantException("Error in Dom: " + ex.getMessage());
        }
    }

    private Tree buildTree(Element element) {
        Tree tree = new Tree();
        tree.setName(element.getAttribute("name"));
        build(element, tree);
        return tree;
    }

    private Flower buildFlower(Element element) {
        Flower flower = new Flower();
        String name = String.valueOf(element.getAttribute("name").toUpperCase());
        flower.setName(name);
        build(element, flower);
        return flower;
    }

    private void build(Element element, Plant plant) {
        if (element.getAttribute("origin")!= null){
            plant.setOrigin(element.getAttribute("origin"));}
        plant.setId(element.getAttribute("id"));
        VisualParameters parameters = plant.getParameters();
        Element parametersElement = (Element) element.getElementsByTagName("visual-parameters").item(0);
        parameters.setStemColor(Color.valueOf(getElementTextContent(parametersElement, "stem-color").toUpperCase()));
        parameters.setLeafColor(Color.valueOf(getElementTextContent(parametersElement, "leaf-color").toUpperCase()));
        parameters.setSize(getElementTextContent(parametersElement, "average-size"));
        plant.setMultiplying(Multiplying.valueOf(getElementTextContent(parametersElement, "multiplying").toUpperCase()));
        plant.setSoil(Soil.valueOf(getElementTextContent(parametersElement,"soil").toUpperCase()));

    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
