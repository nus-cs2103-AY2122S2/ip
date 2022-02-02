package bobby.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.exception.DeadlineException;
import bobby.task.Deadline;
import bobby.task.TaskList;

/**
 * Represents a 'deadline' command
 */
public class DeadlineCommand extends Command {
    /** The full user input command */
    private String fullCommand;
    /** Specific date format that Bobby accepts as input */
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates a DeadLineCommand object.
     *
     * @param fullCommand User input command.
     */
    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Checks whether the command includes a valid date.
     *
     * @param input The date to be inspected.
     * @return True if the date is in the correct format.
     */
    public boolean isValidDate(String input) {
        try {
            SIMPLE_DATE_FORMAT.setLenient(false);
            SIMPLE_DATE_FORMAT.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
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
        if (fullCommand.substring(8).isBlank()) { // nothing after command
            throw new DeadlineException("blank");
        } else if (!fullCommand.contains("/")) { // no "/"
            throw new DeadlineException("no_slash");
        } else if (fullCommand.substring(fullCommand.indexOf("/") + 1).isBlank()) { // nothing after time
            throw new DeadlineException("no_date");
        } else if (!isValidDate(fullCommand.substring(fullCommand.length() - 10))) { // invalid date
            throw new DeadlineException("invalid_date");
        }
        Deadline newDeadline = new Deadline(fullCommand.substring(fullCommand.indexOf(" ") + 1,
                fullCommand.indexOf("/") - 1),
                fullCommand.substring(fullCommand.length() - 10));
        tasks.addTask(newDeadline);
        storage.saveTasks(tasks.getTaskList());
        return ui.deadlineMessage(newDeadline) + "\n" + ui.printNumTasks(tasks);
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are DeadlineCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeadlineCommand;
    }
}
