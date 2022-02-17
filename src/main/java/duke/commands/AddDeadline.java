package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents a command that allows user to add a deadline task
 * to the task list.
 */
public class AddDeadline extends Command {
    private final String description;
    private final String date;

    /**
     * Initialize an AddDeadline Command.
     *
     * @param description Description of the deadline task.
     * @param date Duedate of the deadline task.
     */
    public AddDeadline(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Returns a success string after a successful execution of an add deadline command.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return Message after a deadline has successfully been added to the task list.
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

    /**
     * Returns a success message after a successful undo of an AddDeadline Command.
     * Throws a DukeException when there errors undoing the command.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @return Success message after an AddDeadline command has been undone.
     * @throws DukeException if the program is unable to undo the command.
     */
    @Override
    public String undo(TaskList taskList) throws DukeException {
        return TaskList.deleteLastTask();
    }
}
