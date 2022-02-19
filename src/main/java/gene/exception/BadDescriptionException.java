package gene.exception;

public class BadDescriptionException extends Exception {
    private final String taskType;

    public BadDescriptionException(String type) {
        this.taskType = type;
    }

    @Override
    public String getMessage() {
        return "--------------------------------------------------------\n"
                + ":( OOPS!!! The description of a " + this.taskType + " cannot"
                + " be empty."
                + "\n--------------------------------------------------------";
    }
}
