package duke.commands;

import duke.exception.ChiException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.IOException;

/**
 * Command for marking a task as not complete.
 */
public class UnmarkCommand extends Command {

    /** Message of user in array format */
    private String[] tokens;

    /**
     * Constructor of the class.
     *
     * @param tokens The message of user in array format.
     */
    public UnmarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Sets the task to not completed and returns a string of the task.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A String of the task.
     * @throws ChiException If the user provides an invalid index or uses words instead of natural numbers.
     */
    @Override
    public String execute(TaskList tl, Storage sge) throws ChiException {
        try {
            String msg = String.join(" ", tokens);
            boolean isValidMarkCommand = validateMessageBody(msg, tl);
            if (!isValidMarkCommand) {
                throw new ChiException("Hey there is something wrong with this unmark command nyan!");
            } else {
                int index = getIndexInMessage(msg);
                Task doneTask = tl.getTask(index);
                // Mark as done
                doneTask.markAsUndone();
                sge.updateFile(doneTask, tl, "mark");
                return String.format("We will get them next time~!\n%s\n", doneTask);

            }
        } catch (IOException e) {
            throw new ChiException("Hey something went wrong with the IO nyan!");
        }
    }

    /**
     * Checks if the message body conforms to valid syntax.
     *
     * @param msg The message body.
     * @param tl The TaskList.
     * @return A boolean of whether the message body is valid.
     */
    public boolean validateMessageBody(String msg, TaskList tl)  {
        try {
            String[] words = msg.split(" ");
            if (words.length > 1) {
                return false;
            }
            int s = Integer.parseInt(words[0]);
            return s >= 0 && s <= tl.getSize();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Gets the index of the task to access in the TaskList.
     *
     * @param msg The message body.
     * @return The index of the task.
     */
    public int getIndexInMessage(String msg) {
        return Integer.parseInt(msg) - 1;
    }
}


