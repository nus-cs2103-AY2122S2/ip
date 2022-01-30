package main.java.duke.main;

/**
 * An abstract clas that represents the exceptions related to main.Duke.
 */
public abstract class DukeException extends Exception {
    private String message;

    protected DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
