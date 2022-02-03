package by.epam.task3.entity;

import java.time.YearMonth;

public class Flower extends Plant{

    private String name;
    private int petalNumber;

    public Flower(){
        super();
    }

    public Flower(String origin, String id, VisualParameters parameters, Soil soil, YearMonth date, GrowingTips growingTips, Multiplying multiplying, String name, int petalNumber) {
        super(origin, id, parameters, soil, date, growingTips, multiplying);
        this.name = name;
        this.petalNumber = petalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPetalNumber() {
        return petalNumber;
    }

    public void setPetalNumber(int petalNumber) {
        this.petalNumber = petalNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Flower flower = (Flower) o;
        return petalNumber == flower.petalNumber && name.equals(flower.name);
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Flower{");
        builder.append("name=").append(name);
        builder.append(", petalNumber=").append(petalNumber);
        builder.append(", ").append(super.toString());
        builder.append('}');
        return builder.toString();
    }
}
