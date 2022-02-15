package duke.task;
import duke.duke.Duke;
import duke.ui.Parser;
import duke.ui.DukeException;
import duke.ui.InputHandler;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Event which is a subclass of Task
 * Overrides toString() from Task
 */
public class Event extends Task {
    public LocalDate dueDate;
    public LocalTime dueTime;

    /**
     * Constructor for Event. Takes in name and date but no time.
     *
     * @param name name of the Event
     * @param date date in yyyy-mm-dd format only
     * @throws DateTimeParseException If date format is not in yyyy-mm-dd format
     */
    public Event (String name, String date) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = null;
    }

    /**
     * Constructor for Event. Includes name, date and time
     *
     * @param name name of the Event
     * @param date date in yyyy-mm-dd format only
     * @param time time in hh:mm format
     * @throws DateTimeParseException if date format is not in yyyy-mm-dd format AND/OR time is not in hh:mm format
     */
    public Event (String name, String date, String time) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = LocalTime.parse(time);
    }

    /**
     * String representation of Event
     *
     * @return String of Event task, eg: [E][X] Event (at:) vs [E] [✓] Event (at:)
     */
    @Override
    public String toString() {
        String dueDateAndTime = (this.dueTime == null) ? dateConverterToString(this.dueDate) : dateConverterToString(this.dueDate) + " " + timeConverterToString(this.dueTime);
        return "[E]" + super.toString() + " (at:" + dueDateAndTime + ")"; }
}