package duke;

/**
 * Class for Handling {@code Duke} Exceptions.
 * Extends {@code Throwable}
 */
public class DukeException extends Throwable {

    /**
     * Method to indicate to User that command is invalid.
     *
     * @return Message to user to indicate command is invalid
     */
    public String noSuchCommandException() {
        return ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    /**
     * Method to indicate to User that Description of Task is not found.
     *
     * @return Message to user to indicate that description of @code{todo} task cannot be empty.
     */
    public String noDescriptionException() {
        return "The description of a todo cannot be empty.";
    }

    /**
     * Method to indicate to User that Deadline Syntax is invalid.
     *
     * @return Message to user to indicate that description of deadline task does not follow syntax.
     */
    public String invalidDeadlineSyntax() {
        return "Your syntax for adding deadline task is wrong. \r\n " +
                "Please follow the command: deadline <Description> /by <dd/mm/yyyy>";
    }

    /**
     * Method to indicate to User that Event Syntax is invalid.
     *
     * @return Message to user to indicate that description of Event task does not follow syntax.
     */
    public String invalidEventSyntax() {
        return "Your syntax for adding event task is wrong. \r\n " +
                "Please follow the command: event <Description> /at <Date/Time/DueDate/Place>";
    }

    /**
     * Method to indicate to User that there is no supplied Task number.
     *
     * @return Message to user indicating that no task number is supplied for deleting
     */
    public String noTaskNumber() {
        return "There is no task number provided.";
    }

    /**
     * Method to indicate to User that the Task number supplied is invalid.
     *
     * @return Message to user indicating that task number is not within the list
     */
    public String invalidTaskNumber() {
        return "The task number provided is not valid. \r\n " +
                "Please use the 'list' command to view all your tasks.";
    }

    /**
     * Method to indicate to User that the supplied Tag Syntax is incorrect
     *
     * @return Message to user indicating that tag syntax is invalid
     */
    public String invalidTagSyntax() {
        return "The tag syntax is not valid \r\n " +
                "Tag Syntax: tag <Task Number> <Description of Tag>";
    }

    /**
     * Method to indicate to User that the Task number supplied is invalid.
     *
     * @return Message to user indicating that task number is not within the list
     */
    public String invalidDeleteSyntax() {
        return "The delete syntax is not valid. \r\n " +
                "Delete Syntax: delete <Task Number>";
    }


    /**
     * Method to indicate to User that the Task number supplied is invalid.
     *
     * @return Message to user indicating that task number is not within the list
     */
    public String invalidDeleteTagSyntax() {
        return "The remove tag syntax is not valid. \r\n " +
                "Remove Tag Syntax: \r\n removeTag <Task Number> <Tag Number> " +
                "You can use [list allTags] command to view all tags";
    }
}
