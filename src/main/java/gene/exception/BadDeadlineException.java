package gene.exception;

public class BadDeadlineException extends Exception {
    private final String taskType;

    public BadDeadlineException(String type) {
        this.taskType = type;
    }

    @Override
    public String getMessage() {
        return "--------------------------------------------------------\n"
                + ":( OOPS!!! The time details of a " + this.taskType + " cannot"
                + " be empty."
                + "\n--------------------------------------------------------";
    }
}