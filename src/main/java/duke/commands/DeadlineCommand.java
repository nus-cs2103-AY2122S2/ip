package duke.commands;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates command to add a deadline task
 */
public class DeadlineCommand extends Command implements DateValidator {
    /**
     * The description of the deadline task to be added
     */
    private final String description;
    /**
     * The deadline of the deadline task to be added
     */
    private LocalDate deadline;

    /**
     * Instantiates a new Deadline command.
     *
     * @param description description of deadline task
     * @param deadline    deadline of deadline task
     */
    public DeadlineCommand(String description, String deadline) {
        try {
            this.description = description;
            this.deadline = validDate(deadline);
        } catch (DateTimeParseException e) {
            String exceptionMsg = "Please input date in a valid date-time format.";
            throw new DateTimeParseException(exceptionMsg, description.split(" /by ")[1], e.getErrorIndex());
        }
    }

    /**
     * Refiormats a date in string Local ISO format to a
     * LocalDate object
     *
     * @param dateStr the Local ISO date string to be reformatted
     * @return the reformatted LocalDate date object
     * @throws DateTimeParseException when provided date string
     * is not in Local ISO format
     */
    public LocalDate validDate(String dateStr) throws DateTimeParseException {
        return LocalDate.parse(dateStr);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        Deadline task = new Deadline(tasks.size() + 1, this.description, this.deadline);
        tasks.add(task);
        ui.showMessage("Got it. I've added the deadline task:");
        ui.showMessage(task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
    }
}
