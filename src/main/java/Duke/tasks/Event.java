package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.ui.DukeException;
/**
 * Represents the event of task.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for Event.
     *
     * @param description description of the task.
     * @param at by when the event at.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.date = super.getTaskDate(at);
        this.time = super.getTaskTime(at);
    }

    /**
     * Returns the strings representation of Event in the save file.
     *
     * @return the formats of the String to be save in the file.
     */
    @Override
    public String encodeTaskToString() {
        String isDoneNum = (isDone ? "1|" : "0|");
        return "E|" + isDoneNum + super.getDescription() + "|" + date + " "
                + time + "\n";
    }

    /**
     * Returns the strings representation of event.
     *
     * @return [E] with the status and description of the task,
     *         and at when.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time + ")";
    }


    /**
     * Returns true if the new task is same as the task inside the task list.
     * Else false.
     *
     * @param other the other task to compare.
     * @return true if the new task is same as the task inside the task list.
     *         Else return false.
     */
    @Override
    public boolean taskEquals(Task other) {
        if (other instanceof Event) {
            Event eventTask = (Event) other;
            boolean isSameDescription = (this.description.equals(other.description));
            boolean isSameDate = this.date.equals(eventTask.date);
            boolean isSameTime = this.time.equals(eventTask.time);
            if (isSameDescription && isSameDate && isSameTime) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}

