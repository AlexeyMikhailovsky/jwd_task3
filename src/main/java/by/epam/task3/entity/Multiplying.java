package by.epam.task3.entity;

public enum Multiplying {

    LEAVES("leaves"),
    CUTTINGS("cuttings"),
    SEEDS("seeds");

    private String multiplying;

    Multiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    public String getMultiplying() {
        return multiplying;
    }
}
