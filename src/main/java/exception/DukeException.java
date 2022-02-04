package exception;

/**
 * Custom Exception class that defines user related errors.
 */
public class DukeException extends Exception{

    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns error message.
     * @return String version of the error message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }

}
