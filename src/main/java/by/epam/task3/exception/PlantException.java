package by.epam.task3.exception;

public class PlantException extends Exception{

    public PlantException() {
        super();
    }

    public PlantException(String message) {
        super(message);
    }

    public PlantException(Exception e) {
        super(e);
    }

    public PlantException(String message, Exception e) {
        super(message, e);
    }
}
