package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents a command that allows user to add a deadline task
 * to the task list
 */
public class AddDeadline extends Command {
    private final String description;
    private final String date;

    /**
     * Initialize the AddDeadline Command
     * @param description Description of the deadline task
     * @param date Duedate of the deadline task
     */
    public AddDeadline(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Method that executes a command to add a deadline to the task list.
     * @param taskList a taskList containing all existing tasks
     * @param ui a ui object
     * @param storage a storage object that is able to read and write to storage file
     * @return message after a deadline has successfully been added to the task list
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {
        try {
            if (description.equals("") || date.equals("")) {
                throw new DukeException("Deadline command is invalid!");
            }
            Task deadline = new Deadline(description, date);
            return TaskList.addTask(deadline);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
