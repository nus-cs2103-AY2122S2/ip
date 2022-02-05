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
     * Constructor for Event with date
     * @param name Name of Event
     * @param date Date of event in yyyy-mm-dd format
     * @throws DateTimeParseException If date time is in wrong format
     */
    public Event (String name, String date) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = null;
    }

    /**
     * Constructor for Event with date and time
     * @param name Name of Event
     * @param date Date of event in yyyy-mm-dd format
     * @param time Time of event in hh:mm format
     * @throws DateTimeParseException If date time is in wrong format
     */
    public Event (String name, String date, String time) throws DateTimeParseException {
        super(name);
        this.dueDate = LocalDate.parse(date);
        this.dueTime = LocalTime.parse(time);
    }

    /**
     * @override
     * @return String of Event task, eg: [E][X] Event (at:) vs [E] [✓] Event (at:)
     */
    public String toString() {
        String dueDateAndTime = (this.dueTime == null) ? dateConverterToString(this.dueDate) : dateConverterToString(this.dueDate) + " " + timeConverterToString(this.dueTime);
        return "[E]" + super.toString() + " (at:" + dueDateAndTime + ")"; }
}