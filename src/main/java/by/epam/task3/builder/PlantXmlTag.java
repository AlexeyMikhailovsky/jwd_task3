package by.epam.task3.builder;

public enum PlantXmlTag {

    PLANTS("plants"),
    FLOWER("flower"),
    TREE("tree"),
    NAME("name"),
    ORIGIN("origin"),
    ID("id"),
    SOIL("soil"),
    GROWING_TIPS("growing-tips"),
    MULTIPLYING("multiplying"),
    STEM_COLOR("stem-color"),
    LEAF_COLOR("leaf-color"),
    AVERAGE_SIZE("average-size"),
    TEMPERATURE("temperature"),
    LIGHTING("lighting"),
    WATERING("watering"),
    CROWN_WIDTH("crown-width"),
    PETAL_NUMBER("petal-number"),
    VISUAL_PARAMETERS("visual-parameters");

    private String title;

    PlantXmlTag(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;

    }
}
