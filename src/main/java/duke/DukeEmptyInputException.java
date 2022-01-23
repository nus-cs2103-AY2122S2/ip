package duke;

/**
 * Represents an exception that occurs as a result of no input commands entered by the user
 */
public class DukeEmptyInputException extends DukeException {

    /**
     * Representation of a <code>DukeEmptyInputException</code> object
     * @return String representation corresponding to the <code>DukeEmptyInputException</code> object
     */
    @Override
    public String toString() {
        return "No input is entered.";
    }
}
