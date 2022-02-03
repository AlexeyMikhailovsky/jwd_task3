package by.epam.task3.entity;

import java.time.YearMonth;
import java.util.Objects;

public abstract class Plant {

    private static final String DEFAULT_VALUE = " ";

    private String origin;
    private String id;
    private VisualParameters parameters;
    private Soil soil;
    private YearMonth date;
    private GrowingTips growingTips;
    private Multiplying multiplying;

    public Plant(){}

    public Plant(String origin, String id, VisualParameters parameters, Soil soil, YearMonth date, GrowingTips growingTips, Multiplying multiplying) {
        this.origin = origin;
        this.id = id;
        this.parameters = parameters;
        this.soil = soil;
        this.date = date;
        this.growingTips = growingTips;
        this.multiplying = multiplying;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VisualParameters getParameters() {
        return parameters;
    }

    public void setParameters(VisualParameters parameters) {
        this.parameters = parameters;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public YearMonth getDate() {
        return date;
    }

    public void setDate(YearMonth date) {
        this.date = date;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return origin.equals(plant.origin) &&
                parameters.equals(plant.parameters) &&
                soil == plant.soil &&
                date.equals(plant.date) &&
                growingTips.equals(plant.growingTips) &&
                multiplying.equals(plant.multiplying);
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int result = 1;
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
        result = prime * result + ((soil == null) ? 0 : soil.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Plant{");
        builder.append("origin=").append(origin);
        builder.append(", id=").append(id);
        builder.append(", parameters=").append(parameters);
        builder.append(", soil=").append(soil);
        builder.append(", date=").append(date);
        builder.append('}');
        return builder.toString();
    }
}
