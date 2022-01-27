package bobby.command;

import bobby.exception.BobbyException;
import bobby.exception.MarkException;
import bobby.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.Ui;

/**
 * Represents a 'mark' command
 */
public class MarkCommand extends Command {
    /** The full user input command */
    private String fullCommand;
    /** The full user input command in array form */
    private String[] fullCommandArr;

    /**
     * Creates a MarkCommand object.
     *
     * @param fullCommand User input command
     * @param fullCommandArr User input command in array form, split by a whitespace
     */
    public MarkCommand(String fullCommand, String[] fullCommandArr) {
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
        if (fullCommand.substring(4).isBlank()) {                             // no argument
            throw new MarkException("empty");
        } else if (Character.isLetter(fullCommand.charAt(5))) {               // contains letter instead of number
            throw new MarkException("letter");
        } else if (Integer.parseInt(fullCommandArr[1]) > tasks.getSize()) {   // out of bounds
            throw new MarkException("OOB");
        } else if (Integer.parseInt(fullCommandArr[1]) < 1) {
            throw new MarkException("negative");
        }
        Task task = tasks.getIndex(Integer.parseInt(fullCommandArr[1]) - 1);
        if (task.isDone()) {
            throw new MarkException("alr_marked");
        }
        task.markDone();
        storage.saveTasks(tasks.getTaskList());
        ui.markMessage(task);
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are MarkCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof MarkCommand;
    }
}
