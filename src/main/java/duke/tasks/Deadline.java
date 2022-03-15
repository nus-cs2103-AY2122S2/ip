package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for deadline type task
 * stores the due date of the task
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline task
     * @param detail details of the task
     * @param deadline date of which the task is due
     */
    public Deadline(String detail, String deadline) {
        super(detail);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Returns the due date of the task
     * @return Date if applicable
     */
    @Override
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return deadline.format(formatter);
    }

    /**
     * returns a string representation for the type of task
     * @return String representing the type of task
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Updates the details of the task
     */
    @Override
    public void updateDetail(String update) {
        this.detail = update;
    }

    /**
     * Updates the date of the task
     */
    @Override
    public void updateDate(String update) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.deadline = LocalDateTime.parse(update, formatter);
    }

    /**
     * returns string representation of Task with mark status date and detail
     * @return String representation of Task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String markedPrint;
        if (isMarked) {
            markedPrint = "[D][X] ";
        } else {
            markedPrint = "[D][ ] ";
        }
        return markedPrint + detail + "(by:" + deadline.format(formatter) + ")";
    }
}
