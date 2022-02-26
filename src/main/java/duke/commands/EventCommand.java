package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates command to create an event task.
 */
public class EventCommand implements DateValidator, Command {
    /**
     * The description of event
     */
    private final String description;
    /**
     * The date of the event
     */
    private LocalDate date;

    /**
     * Instantiates a new Event command.
     *
     * @param description the description of the event
     * @param date        date of the event
     */
    public EventCommand(String description, String date) {
        try {
            this.description = description;
            this.date = validDate(date);
        } catch (DateTimeParseException e) {
            String exceptionMsg = "Please input date in a valid date-time format.";
            throw new DateTimeParseException(exceptionMsg, description.split(" /by ")[1], e.getErrorIndex());
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
     */
    @Override
    public void execute(TaskList<Task> tasks, Ui ui, Storage storage) {
        Event event = new Event(this.description, this.date);
        tasks.add(event);
        ui.showMessage("Got it. I've added the deadline task:");
        ui.showMessage(event.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in your list.");
    }
}
