package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.ui.DukeException;

/**
 * Represents the deadline of task.
 */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for Deadline.
     *
     * @param description description of the task.
     * @param by by when the deadline end.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.date = super.getTaskDate(by);
        this.time = super.getTaskTime(by);
    }

    /**
     * Returns the strings representation of Deadline in the save file.
     *
     * @return the formats of the String to be save in the file
     */
    @Override
    public String encodeTaskToString() {
        String isDoneNum = (isDone ? "1|" : "0|");
        return "D|" + isDoneNum + super.getDescription() + "|" + date + " "
                + time + "\n";
    }

    /**
     * Returns the strings representation of Deadline.
     *
     * @return [D] with the status and description of the task,
     *         and by when.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
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
        if (other instanceof Deadline) {
            Deadline deadlineTask = (Deadline) other;
            boolean isSameDescription = (this.description.equals(other.description));
            boolean isSameDate = this.date.equals(deadlineTask.date);
            boolean isSameTime = this.time.equals(deadlineTask.time);
            if (isSameDescription && isSameDate && isSameTime) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}

