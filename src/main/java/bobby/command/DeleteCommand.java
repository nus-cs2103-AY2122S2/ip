package bobby.command;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.exception.DeleteException;
import bobby.task.Task;
import bobby.task.TaskList;


/**
 * Represents a 'delete' command.
 */
public class DeleteCommand extends Command {
    /** The full user input command */
    private String fullCommand;
    /** The full user input command in array form */
    private String[] fullCommandArr;

    /**
     * Creates a DeleteCommand object.
     *
     * @param fullCommand User input command
     * @param fullCommandArr User input command in array form, split by a whitespace
     */
    public DeleteCommand(String fullCommand, String[] fullCommandArr) {
        this.fullCommand = fullCommand;
        this.fullCommandArr = fullCommandArr;
    }

    /**
     * Carries out the respective command's actions.
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @throws BobbyException if an invalid command is given by the user's input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ui.printLongLine();
        if (fullCommandArr[1].equalsIgnoreCase("all")) {
            if (tasks.isEmpty()) {
                throw new DeleteException("list_empty");
            }
            tasks.removeAll();
            ui.deleteAllMessage();
        } else {
            try {
                if (fullCommand.substring(6).isBlank()) { // no argument
                    throw new DeleteException("empty");
                } else if (Integer.parseInt(fullCommandArr[1]) > tasks.getSize()) { // out of bounds
                    throw new DeleteException("OOB");
                } else if (Integer.parseInt(fullCommandArr[1]) < 1) {
                    throw new DeleteException("negative");
                }
            } catch (NumberFormatException e) {
                throw new DeleteException("letter"); // contains letter(s)
            }
            Task task = tasks.getIndex(Integer.parseInt(fullCommandArr[1]) - 1);
            ui.deleteMessage(task);
            tasks.removeTask(task);
        }
        storage.saveTasks(tasks.getTaskList());
        ui.printNumTasks(tasks);
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

