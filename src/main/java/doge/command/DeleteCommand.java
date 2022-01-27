package doge.command;

import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;

/**
 * Represents the "delete" command. Doge bot will delete a specified task from the TaskList.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor for class DeleteCommand
     *
     * @param s the task no. that will be deleted
     */
    public DeleteCommand(String s) {
        super(s);
    }

    /**
     * Executes the "delete" command. It deletes the specified task from the TaskList.
     *
     * {@inheritDoc}
     * @throws DogeException if it fails to delete the specified task
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        if (this.details.isEmpty()) {
            throw new DogeException("How am I suppose to delete something without knowing which task?");
        }

        int pos;
        try {
            pos = Integer.parseInt(this.details);
        } catch (NumberFormatException e) {
            throw new DogeException("Are you incapable of understanding what's an integer? Input valid task no.");
        }

        if (pos > tasks.size() || pos < 0) {
            throw new DogeException("How am I supposed to delete something that doesn't exist?");
        } else {
            this.task = tasks.getTask(pos - 1);
            tasks.deleteTask(pos - 1);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "I've already deleted for you! You're welcome.";
    }
}