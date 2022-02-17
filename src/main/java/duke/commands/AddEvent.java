package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents a command that allows user to add an event task
 * to the task list.
 */
public class AddEvent extends Command {
    private final String description;
    private final String dateTime;

    /**
     * Initialize an AddEvent Command.
     *
     * @param description Description of the event task.
     * @param dateTime Due date of the event task.
     */
    public AddEvent(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Returns a success string after a successful execution of an add event command.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return Message after an event has successfully been added to the task list.
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

    /**
     * Returns a success message after a successful undo of an AddEvent Command.
     * Throws a DukeException when there errors undoing the command.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @return Success message after an AddEvent command has been undone.
     * @throws DukeException if the program is unable to undo the command.
     */
    @Override
    public String undo(TaskList taskList) throws DukeException {
        return TaskList.deleteLastTask();
    }
}
