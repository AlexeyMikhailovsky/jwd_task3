package by.epam.task3.entity;

import java.util.Objects;

public class VisualParameters {

    private Color stemColor;
    private Color leafColor;
    private String size;

    public VisualParameters(){
    }

    public VisualParameters(Color stemColor, Color leafColor, String size) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.size = size;
    }

    public Color getStemColor() {
        return stemColor;
    }

    public void setStemColor(Color stemColor) {
        this.stemColor = stemColor;
    }

    public Color getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(Color leafColor) {
        this.leafColor = leafColor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameters that = (VisualParameters) o;
        return stemColor == that.stemColor && leafColor == that.leafColor && size.equals(that.size);
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int result = 1;
        result = prime * result + ((stemColor == null) ? 0 : stemColor.hashCode());
        result = prime * result + ((leafColor == null) ? 0 : leafColor.hashCode());
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("VisualParameters{");
        builder.append("stemColor=").append(stemColor);
        builder.append(", leafColor=").append(leafColor);
        builder.append(", size=").append(size);
        builder.append('}');
        return builder.toString();
    }
}
