package by.epam.task3.entity;

import java.time.YearMonth;
import java.util.Objects;

public class Tree extends Plant{

    private String name;
    private String crownWidth;

    public Tree(){
        super();
    }

    public Tree(String origin, String id, VisualParameters parameters, Soil soil, YearMonth date, GrowingTips growingTips, Multiplying multiplying, String name, String crownWidth) {
        super(origin, id, parameters, soil, date, growingTips, multiplying);
        this.name = name;
        this.crownWidth = crownWidth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrownWidth() {
        return crownWidth;
    }

    public void setCrownWidth(String crownWidth) {
        this.crownWidth = crownWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tree tree = (Tree) o;
        return name.equals(tree.name) && crownWidth.equals(tree.crownWidth);
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((crownWidth == null) ? 0 : crownWidth.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tree{");
        builder.append("name=").append(name);
        builder.append(", crownWidth=").append(crownWidth);
        builder.append(", ").append(super.toString());
        builder.append('}');
        return builder.toString();
    }
}
