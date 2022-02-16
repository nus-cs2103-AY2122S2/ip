package Duke.main;

public class DukeException extends Exception {

    /**
     * Constructor for the DukeExceptions class.
     *
     * @param message - Error Message
     */
    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Sorry " + super.getMessage();
    }

}
