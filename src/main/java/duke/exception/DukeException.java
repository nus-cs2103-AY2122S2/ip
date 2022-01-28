package duke.exception;

public abstract class DukeException extends Exception {
    private String errorMessage;

    public DukeException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }

}
