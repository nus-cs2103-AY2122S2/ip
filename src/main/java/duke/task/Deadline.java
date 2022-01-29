package duke.task;

import duke.file_management.Time;

/**
 * One of the three tasks that a user can indicate.
 * Indicate a task with a Deadline that the user has to complete by.
 *
 * @author Justin Ng Jie Ern
 */
public class Deadline extends Task{
    /**
     * Time Object that is the deadline for the Task.
     */
    private Time time;

    /**
     * Constructor to create a Deadline Object.
     * @param name Name of the Task that has a deadline.
     * @param isChecked Boolean of whether the Task has been marked or not.
     * @param taskLabel "D" to represent that this is a Deadline Task.
     * @param deadline String of the date and time that the task has to be completed by.
     */
    public Deadline(String name, boolean isChecked, String taskLabel, String deadline) {
        super(name, isChecked, taskLabel);
        this.time = new Time(deadline);
    }

    /**
     * Prints out information for the Deadline Task.
     *
     * @return String of Deadline Task.
     */
    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + super.isTaskCheck() + "] " + super.toString() +
                " (by: " + this.time + ")";
    }
}
