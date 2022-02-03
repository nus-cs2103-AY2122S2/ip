package src.main.java.duke;

/**
 * DukeException is an Exception unique to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException instance with the inputted error message
     * 
     * @param errorMessage message describing the fault
     */
    public DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
