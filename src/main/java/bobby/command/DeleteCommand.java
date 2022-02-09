package bobby.command;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.exception.DeleteException;
import bobby.exception.InvalidNumberException;
import bobby.task.Task;
import bobby.task.TaskList;


/**
 * Represents a 'delete' command.
 */
public class DeleteCommand extends Command {
    /** The task index to be deleted */
    private String toDelete;

    /**
     * Creates a DeleteCommand object.
     *
     * @param toDelete The task index to be deleted, or 'all'
     */
    public DeleteCommand(String toDelete) {
        this.toDelete = toDelete;
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @return Bobby's reply to the command.
     * @throws BobbyException if an invalid command is given by the user's input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        String replyMessage;
        int indexDelete;
        if (toDelete.equalsIgnoreCase("all")) {
            if (tasks.isEmpty()) {
                throw new DeleteException("list_empty");
            }
            tasks.removeAll();
            replyMessage = ui.deleteAllMessage();
        } else {
            try {
                indexDelete = Integer.parseInt(toDelete) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidNumberException("letter");
            }
            Task task = tasks.getIndex(indexDelete);
            replyMessage = ui.deleteMessage(task);
            tasks.removeTask(task);
        }
        storage.saveTasks(tasks.getTaskList());
        return replyMessage + "\n" + ui.printNumTasks(tasks);
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are DeleteCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeleteCommand;
    }
}

