package by.epam.task3.entity;

public enum Color {

    COLORLESS("colorless"),
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    NAVY("navy"),
    PURPLE("purple"),
    PINK("pink"),
    WHITE("white"),
    BROWN("brown");

    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
