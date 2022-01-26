package duke.exceptions;

/**
 * A throwable object to indicate an invalid String
 * being parsed as a command.
 */
public class TaskIndexException extends Exception {
    private String message;

    /**
     * Constructs the TaskIndexException Object.
     *
     * @param text Type of the Task Object
     */
    public TaskIndexException(String text){
        this.message = text;
    }

    /**
     * @return a String representation of the error message
     */
    @Override
    public String toString() {
        return "OOPS!!! You didn't give me a proper " + this.message +" typed task to include!";
    }
}
