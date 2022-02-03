package by.epam.task3.entity;

public enum Soil {

    PODZOLIC("podzolic"),
    SOD_PODZOLIC("sod-podzolic"),
    UNPAVED("unpaved");

    private String soil;

    Soil(String color) {
        this.soil = color;
    }

    public String getSoil() {
        return soil;
    }
}
