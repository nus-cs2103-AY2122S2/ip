package bobby.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.exception.EventException;
import bobby.task.Event;
import bobby.task.TaskList;

/**
 * Represents an 'event' command
 */
public class EventCommand extends Command {
    /** The full user input command */
    private String fullCommand;
    /** Specific date format that Bobby accepts as input */
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Creates an EventCommand object.
     *
     * @param fullCommand User input command.
     */
    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Checks whether the command includes a valid date.
     *
     * @param input The date to be inspected.
     * @return True if the date is in the correct format.
     */
    public boolean isValidDate(String input) {
        if (input.contains("[a-zA-Z]+") || input.length() != 10) {
            return false;
        }
        try {
            SIMPLE_DATE_FORMAT.setLenient(false);
            SIMPLE_DATE_FORMAT.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
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
        if (fullCommand.substring(5).isBlank()) { // nothing after command
            throw new EventException("blank");
        } else if (!fullCommand.contains("/")) { // no "/"
            throw new EventException("no_slash");
        } else if (fullCommand.substring(fullCommand.indexOf("/") + 1).isBlank()) { // nothing after time
            throw new EventException("no_date");
        } else if (!isValidDate(fullCommand.substring(fullCommand.length() - 10))) { // invalid date
            throw new EventException("invalid_date");
        }
        Event newEvent = new Event(fullCommand.substring(fullCommand.indexOf(" ") + 1,
                fullCommand.indexOf("/") - 1),
                fullCommand.substring(fullCommand.length() - 10));
        ui.eventMessage(newEvent);
        tasks.addTask(newEvent);
        storage.saveTasks(tasks.getTaskList());
        ui.printNumTasks(tasks);
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are EventCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof EventCommand;
    }
}
