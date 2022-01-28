package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Responsible for adding new tasks to the list of tasks
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor 1 that deals with adding a ToDo item because it needs less information than other tasks
     * @param details of the todo
     * @throws DukeException
     */
    public AddCommand(String details) throws DukeException {
        if (details.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description cannot be empty.");
        }
        this.task = new ToDo(details);
    }

    /**
     * Constuctor 2 that deals with adding a Deadline or Event item. These have a date and need to be handled
     * differently from a todo. The date must be checked to be present and in correct format of yyy-mm-dd.
     * @param cmd the type of task, whether event or deadline
     * @param details description and date of the task
     * @throws DukeException
     */
    public AddCommand(String cmd, String details) throws DukeException {
        if (details.length() == 0) {
            throw new DukeException("☹ OOPS!!! The description cannot be empty.");
        }
        if (!details.contains("/")) {
            throw new DukeException("☹ OOPS!!! The date of a " + cmd + " cannot be empty. " +
                    "Use /at and type the date with the format yyyy-mm-dd after it");
        }
        try {
            String[] d_deets = details.split("/");
            String givenDate = d_deets[1].trim().substring(3); //ignore the words at or by + the space that follows
            LocalDate parsedDate = LocalDate.parse(givenDate);
            if (cmd .equals("event")) {
                this.task = new Event(details, parsedDate);
            } else {
                this.task = new Deadline(details, parsedDate);
            }
        } catch (
                DateTimeParseException | ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new DukeException("Please use /at and type the date with the format yyyy-mm-dd after it");
        }
    }

    /**
     * Adds the new task to the existing list of tasks, notifies the user
     * of the completion of the task, and updates the user's file.
     * @param tasks holds all the tasks that the user has recorded down.
     * @param ui used to notify the user of task completion.
     * @param storage saves the tasks to file if there were any edits to it.
     * @throws DukeException
     */
    @Override
    public void execute(TaskMaster tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.notifyAddedTaskMessage(this.task);
        storage.saveToFile(tasks.getTasks());
    }
}
