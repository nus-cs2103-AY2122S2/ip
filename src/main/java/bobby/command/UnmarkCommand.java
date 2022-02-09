package bobby.command;


import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.exception.MarkException;
import bobby.task.Task;
import bobby.task.TaskList;

/**
 * Represents an 'unmark' command.
 */
public class UnmarkCommand extends Command {
    /** The full user input command */
    private String fullCommand;
    /** The full user input command in array form */
    private String[] fullCommandArr;

    /**
     * Creates an UnmarkCommand object.
     *
     * @param fullCommand User input command
     * @param fullCommandArr User input command in array form, split by a whitespace
     */
    public UnmarkCommand(String fullCommand, String[] fullCommandArr) {
        this.fullCommand = fullCommand;
        this.fullCommandArr = fullCommandArr;
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
        if (fullCommand.substring(6).isBlank()) { // no argument
            throw new MarkException("empty");
        } else if (Character.isLetter(fullCommand.charAt(7))) { // contains letter instead of number
            throw new MarkException("letter");
        } else if (Integer.parseInt(fullCommandArr[1]) > tasks.getSize()) { // out of bounds
            throw new MarkException("OOB");
        } else if (Integer.parseInt(fullCommandArr[1]) < 1) {
            throw new MarkException("negative");
        }
        Task task = tasks.getIndex(Integer.parseInt(fullCommandArr[1]) - 1);
        if (!task.isDone()) {
            throw new MarkException("alr_unmarked");
        }
        task.unmarkDone();
        storage.saveTasks(tasks.getTaskList());
        return ui.unmarkMessage(task);
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are UnmarkCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnmarkCommand;
    }
}
