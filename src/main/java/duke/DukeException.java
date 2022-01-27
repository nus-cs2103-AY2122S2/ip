package duke;

public class DukeException extends Exception {

    /**
     * Instantiates a DukeException object
     * @param errorMessage returns an error message
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Method that returns the string representation of the error
     * @return A string of the error message
     */
    @Override
    public String toString() {
        return this.getMessage();
    }

}
