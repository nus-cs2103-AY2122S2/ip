package bobby.command;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.task.Event;
import bobby.task.TaskList;

/**
 * Represents an 'event' command
 */
public class EventCommand extends Command {
    /** The name of the Deadline task */
    private String eventName;
    /** The date to tied to this task */
    private String atDate;

    /**
     * Creates an EventCommand object.
     *
     * @param eventName Name of the Event task.
     * @param atDate Date of the Event task.
     */
    public EventCommand(String eventName, String atDate) {
        this.eventName = eventName;
        this.atDate = atDate;
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @return Bobby's reply to the command.
     * @throws BobbyException if an invalid command is given by the user's input.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        Event newEvent = new Event(eventName, atDate);
        tasks.addTask(newEvent);
        storage.saveTasks(tasks.getTaskList());
        return ui.eventMessage(newEvent) + "\n" + ui.printNumTasks(tasks);
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
