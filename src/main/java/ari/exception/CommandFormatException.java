package main.java.ari.exception;

public class CommandFormatException extends DukeException {
    public CommandFormatException() {
    }

    public CommandFormatException(String message) {
        super("Sorry Master, please enter command with following format:\n" + message);
    }
}
