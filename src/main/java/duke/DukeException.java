package duke;

public class DukeException extends Exception {

    private String errorMessage;

    /**
     * Constructor for DukeException
     * @param errorMessage
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}
