package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents a command that allows user to add an event task
 * to the task list
 */
public class AddEvent extends Command {
    private final String description;
    private final String dateTime;

    /**
     * Initialize the AddEvent Command
     * @param description Description of the event task
     * @param dateTime Duedate of the event task
     */
    public AddEvent(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Method that executes a command to add an event to the task list.
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return message after an event has successfully been added to the task list
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            if (description.equals("") || dateTime.equals("")) {
                throw new DukeException("Event command is invalid!");
            }
            Task event = new Event(description, dateTime);
            return TaskList.addTask(event);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
