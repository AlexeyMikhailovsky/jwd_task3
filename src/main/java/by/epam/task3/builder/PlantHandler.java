package by.epam.task3.builder;

import by.epam.task3.entity.Color;
import by.epam.task3.entity.Flower;
import by.epam.task3.entity.Plant;
import by.epam.task3.entity.Tree;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class PlantHandler extends DefaultHandler {

    private Set<Plant> plants;
    private Plant current;
    private PlantXmlTag currentXmlTag;
    private EnumSet<PlantXmlTag> withText;

    public PlantHandler() {
        plants = new HashSet<>();
        withText = EnumSet.range(PlantXmlTag.NAME, PlantXmlTag.SOIL);
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (PlantXmlTag.FLOWER.getTitle().equals(qName)) {
            current = new Flower();
            String name = String.valueOf(attrs.getValue("name").toUpperCase());
            ((Flower) current).setName(name);
            if (attrs.getValue("origin") != null) {
                current.setOrigin(attrs.getValue("origin"));
            }
            if (attrs.getValue("id") != null) {
                current.setId(attrs.getValue("id"));
            }
        } else if (PlantXmlTag.TREE.getTitle().equals(qName)) {
            current = new Tree();
            ((Tree) current).setName(attrs.getValue("name"));
            if (attrs.getValue("origin") != null) {
                current.setOrigin(attrs.getValue("origin"));
            }
            if (attrs.getValue("id") != null) {
                current.setId(attrs.getValue("id"));
            }
        } else {
            PlantXmlTag temp = PlantXmlTag.valueOf(qName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (PlantXmlTag.FLOWER.getTitle().equals(qName)
                || PlantXmlTag.TREE.getTitle().equals(qName)) {
            plants.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case AVERAGE_SIZE:
                    current.getParameters().setSize(data.toUpperCase());
                    break;
                case LEAF_COLOR:
                    current.getParameters().setLeafColor(Color.valueOf(data.toUpperCase()));
                    break;
                case STEM_COLOR:
                    current.getParameters().setStemColor(Color.valueOf(data.toUpperCase()));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}
