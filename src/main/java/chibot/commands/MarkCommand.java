package chibot.commands;

import chibot.exception.ChiException;
import chibot.storage.Storage;
import chibot.task.Task;
import chibot.tasklist.TaskList;

import java.io.IOException;

/**
 * Command for marking a task as complete.
 */
public class MarkCommand extends Command {

    /** Message of user in array format */
    private final String[] tokens;

    /**
     * Constructor of the class.
     *
     * @param tokens The message of user in array format.
     */
    public MarkCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Sets the task to complete and returns a string of the completed task.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A String of the completed task.
     * @throws ChiException If the user provides an invalid index or uses words instead of natural numbers.
     */
    @Override
    public String execute(TaskList tl, Storage sge) throws ChiException {
        try {
            String msg = String.join(" ", tokens);
            boolean isValidMarkCommand = validateMessageBody(msg, tl);
            if (!isValidMarkCommand) {
                throw new ChiException("Hey there is something wrong with this mark command nyan!");
            }
            int index = getIndexInMessage(msg);
            Task doneTask = tl.getTask(index);
            doneTask.markAsDone();
            sge.updateFile(doneTask, tl, "mark");
            return String.format("Great job nyan~!\n%s\n", doneTask);
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
    public boolean validateMessageBody(String msg, TaskList tl) {
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
