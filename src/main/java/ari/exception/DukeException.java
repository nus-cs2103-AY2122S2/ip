package main.java.ari.exception;

public class DukeException extends IllegalArgumentException {
    public DukeException() {
    }

    public DukeException(String message) {
        super(message);
    }
}
