package duke.exceptions;

public class DukeException extends Exception {

    /**
     * Creates an exception with the given message
     * @param x Message detailing exception
     */
    public DukeException(String x){
        super("\nDuke: " + x + "\n");
    }
}
