package duke.commands;

import duke.exception.ChiException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.IOException;

/**
 * Command for deleting a specified task.
 */
public class DeleteCommand extends Command {

    /** Message sent by user in array format */
    private String[] tokens;

    /**
     * Constructor of the class.
     *
     * @param tokens The message sent by the user in array format.
     */
    public DeleteCommand(String[] tokens) {
        this.tokens = tokens;
    }

    /**
     * Deletes a task and prints the task deleted.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A String response of task deleted.
     * @throws ChiException If the user typed an invalid index or used words instead of indexes.
     */
    @Override
    public String execute(TaskList tl, Storage sge) throws ChiException {
        try {
            // Re-combines the message back into a single string
            String msg = String.join(" ", tokens);
            // Checks if it is a valid message
            boolean isValidMarkCommand = validateMessageBody(msg, tl);
            if (!isValidMarkCommand) {
                throw new ChiException("Hey there is something wrong with this delete command nyan!");
            } else {
                int index = getIndexInMessage(msg);
                Task toDelete = tl.getTask(index);
                tl.deleteTask(toDelete);
                sge.updateFile(toDelete, tl, "delete");
                return String.format("Chi-san has removed the task~!\n%s\n You now have %d tasks!\n",
                        toDelete, tl.getSize());
            }
        } catch (IOException e) {
            throw new ChiException("Hey something went wrong with the IO nyan!");
        }
    }

    /**
     * Checks if the body of the message has a valid syntax.
     *
     * @param msg The message sent by the user.
     * @param tl The TaskList.
     * @return A boolean of whether the message conforms to proper syntax.
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
