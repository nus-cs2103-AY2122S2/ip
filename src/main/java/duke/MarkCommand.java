package duke;

/**
 * Runs the command for user to mark a particular task completed or uncompleted.
 */

public class MarkCommand extends Command {

    enum MarkTypes {
        Mark, Unmark
    }

    private MarkTypes markTypes;
    private int index;

    /**
     * Constructor to create a new instance of a mark command.
     * @param markTypes whether this command is to mark or unmark a particular task.
     * @param index to indicate which task does the user want to mark or unmark.
     */
    MarkCommand (MarkTypes markTypes, int index) {
        this.markTypes = markTypes;
        this.index = index;
    }

    /**
     * Executes the command proceeds to mark or unmark the task.
     *
     * @param taskList The TaskList of the current user.
     * @param ui The user interface to show messages to users.
     * @param storage The file system for reading and writing into the database.
     */
    @Override
    void runCommand(TaskList taskList, Ui ui, Storage storage) {
        String indentation = "    ";
        Task t = taskList.getTask(index);
        String message;
        if (markTypes == MarkTypes.Mark) {
            taskList.markTask(index);
            message = indentation + "Nice! I've marked this task as done:\n"
                    + indentation + "  " + t.toString() + t.getStatus() + " " + t.getDescription();
        } else {
            taskList.unmarkTask(index);
            message = indentation + "OK, I've marked this task as not done yet:\n"
                    + indentation + "  " + t.toString() + t.getStatus() + " " + t.getDescription();
        }

        ui.outputMessage(message);
    }

}
