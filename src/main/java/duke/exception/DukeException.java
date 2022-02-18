package duke.exception;

public class DukeException extends Exception {


    /**
     *
     * @param message
     */
    public DukeException(String message) {

        super(message);
    }

    @Override
    public String getMessage() {

        return "OOPS! " + super.getMessage();
    }

}