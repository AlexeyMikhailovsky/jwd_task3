package by.epam.task3.builder;

public class BuilderFactory {

    private enum TypeParser {
        SAX, STAX, DOM
    }

    private BuilderFactory() {
    }

    public static PlantBuilder createPlantBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new DomBuilder();
            case STAX:
                return new StaxBuilder();
            case SAX:
                return new SaxBuilder();
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name());
        }
    }
}
