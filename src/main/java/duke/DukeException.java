package duke;

/**
 * Class for Handling {@code Duke} Exceptions.
 * Extends {@code Throwable}
 */
public class DukeException extends Throwable {

    /**
     * Method to indicate to User that command is invalid.
     */
    public void noSuchTaskException() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    /**
     * Method to indicate to User that Description of Task is not found.
     */
    public void noDescriptionException() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
    }

    /**
     * Method to indicate to User that Deadline Syntax is invalid.
     */
    public void invalidDeadlineSyntax() {
        System.out.println("☹ OOPS!!! Your syntax for adding deadline task is wrong. \r\n Please follow the command: deadline <Description> /by <dd/mm/yyyy>");
    }

    /**
     * Method to indicate to User that Event Syntax is invalid.
     */
    public void invalidEventSyntax() {
        System.out.println("☹ OOPS!!! Your syntax for adding event task is wrong. \r\n Please follow the command: event <Description> /at <Date/Time/DueDate/Place>");
    }

    /**
     * Method to indicate to User that there is no supplied Task number.
     */
    public void noTaskNumber() {
        System.out.println("☹ OOPS!!! There is no task number provided.");
    }

    /**
     * Method to indicate to User that the Task number supplied is invalid.
     */
    public void invalidTaskNumber() {
        System.out.println("☹ OOPS!!! The task number provided is not valid. Please use the 'list' command to view all your tasks.");
    }
}
