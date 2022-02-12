package duke;

public class MissingDescriptionException extends Exception {
    public MissingDescriptionException() {
        super("Missing description!");
    }
}
