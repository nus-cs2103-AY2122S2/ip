package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task. A Deadline object corresponds to a String description of the Task
 * and the LocalDateTime the task is due.
 */
public class Deadline extends Task implements Comparable<Task> {

    private final LocalDateTime dateAndTime;

    /**
     * Constructs a deadline task.
     * @param description Description of deadline task.
     * @param dateAndTime LocalDateTime of the deadline task.
     */
    public Deadline(String description, LocalDateTime dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Compare tasks by task type and local date time.
     * @param otherTask The task compared with.
     * @return Returns -1,0,1.
     */
    @Override
    public int compareTo(Task otherTask) {
        if (otherTask.getClass() != this.getClass()) {
            int compareValueOfOtherTask = 2;
            int compareValueOfCurrentTask = 2;
            if (otherTask instanceof ToDo) {
                compareValueOfOtherTask = 1;
            }
            if (otherTask instanceof Event) {
                compareValueOfOtherTask = 3;
            }
            return Integer.compare(compareValueOfCurrentTask, compareValueOfOtherTask);
        } else {
            Deadline otherDeadline;
            otherDeadline = (Deadline) otherTask;
            LocalDateTime currentDateAndTime = this.dateAndTime;
            LocalDateTime otherDateAndTime = otherDeadline.dateAndTime;
            return compareLocalDateTime(currentDateAndTime, otherDateAndTime);
        }
    }

    private static int compareLocalDateTime(LocalDateTime currentDateAndTime, LocalDateTime otherDateAndTime) {
        if (currentDateAndTime.isBefore(otherDateAndTime)) {
            return -1;
        } else if (currentDateAndTime.isAfter(otherDateAndTime)) {
            return 1;
        } else {
            return 0;
        }
    }
    /**
     * Returns a String representation of the deadline task in the desired format.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" MMM dd yyyy h:mm a");
        String outputDateTime = dateAndTime.format(outputFormat);
        return "[D]" + super.toString() + " (by:" + outputDateTime + ")";
    }
}
