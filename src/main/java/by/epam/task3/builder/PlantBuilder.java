package by.epam.task3.builder;

import by.epam.task3.entity.Plant;
import by.epam.task3.exception.PlantException;
import java.util.HashSet;
import java.util.Set;

public abstract class PlantBuilder {

    protected Set<Plant> plants;

    public PlantBuilder() {
        plants = new HashSet<>();
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    public abstract void buildSetPlants(String filename) throws PlantException;
}
