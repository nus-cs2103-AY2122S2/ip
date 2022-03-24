package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to add a deadline task
 */
public class DeadlineCommand implements DateValidator, Command {
    /**
     * The description of the deadline task to be added
     */
    private final String description;
    /**
     * The deadline of the deadline task to be added
     */
    private final LocalDate deadline;

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
            throw new DateTimeParseException(
                    "Please input date in a valid date-time format.",
                    description.split(" /by ")[1], e.getErrorIndex());
        }
    }

    /**
     * Refiormats a date in string Local ISO format to a
     * LocalDate object
     *
     * @param date the Local ISO date string to be reformatted
     * @return the reformatted LocalDate date object
     * @throws DateTimeParseException when provided date string
     * is not in Local ISO format
     */
    public LocalDate validDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date);
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
     * @return
     */
    @Override
    public String execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        String output = "";
        Deadline task = new Deadline(this.description, this.deadline);
        tasks.add(task);
        output += "Got it. I've added the deadline task:\n " + task + "\n";
        output += "Now you have " + tasks.size() + " tasks in your list.";
        return output;
    }
}
