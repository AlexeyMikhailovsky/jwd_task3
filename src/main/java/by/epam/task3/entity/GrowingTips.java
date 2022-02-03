package by.epam.task3.entity;

import java.util.Objects;

public class GrowingTips {

    private String temperature;
    private String lighting;
    private String watering;

    public GrowingTips(){}

    public GrowingTips(String temperature, String lighting, String watering) {
        this.temperature = temperature;
        this.lighting = lighting;
        this.watering = watering;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLighting() {
        return lighting;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting;
    }

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowingTips that = (GrowingTips) o;
        return temperature.equals(that.temperature) &&
                lighting.equals(that.lighting) &&
                watering.equals(that.watering);
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int result = 1;
        result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
        result = prime * result + ((lighting == null) ? 0 : lighting.hashCode());
        result = prime * result + ((watering == null) ? 0 : watering.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GrowingTips{");
        builder.append("temperature=").append(temperature);
        builder.append(", lighting=").append(lighting);
        builder.append(", watering=").append(watering);
        builder.append('}');
        return builder.toString();
    }
}
