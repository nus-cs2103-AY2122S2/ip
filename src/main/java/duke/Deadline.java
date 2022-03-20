package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * Represents deadline related tasks.
 */
public class Deadline extends Task {
    protected LocalDate deadlineDate;
    protected DayOfWeek day;
    protected Month month;
    protected int year;
    protected LocalTime time;
    protected Tag tag;

    /**
     * Constructs a Deadline object.
     *
     * @param description description of the deadline task.
     * @param deadlineDate deadline date.
     * @param time deadline time.
     */
    public Deadline(String description, LocalDate deadlineDate, LocalTime time) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.day = deadlineDate.getDayOfWeek();
        this.month = deadlineDate.getMonth();
        this.year = deadlineDate.getYear();
        this.time = time;
    }

    /**
     * Constructs deadline object with tag.
     *
     * @param description description of deadline task.
     * @param deadlineDate deadline date.
     * @param time deadline time.
     * @param tag deadline tag.
     */
    public Deadline(String description , LocalDate deadlineDate, LocalTime time, Tag tag) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.day = deadlineDate.getDayOfWeek();
        this.month = deadlineDate.getMonth();
        this.year = deadlineDate.getYear();
        this.time = time;
        this.tag = tag;
    }

    /**
     * Tags deadline task.
     *
     * @param taskTag tag for task.
     * @return deadline object.
     */
    @Override
    public Deadline tag(Tag taskTag) {
        return new Deadline(description, deadlineDate, time, taskTag);
    }

    /**
     * Returns a string representation to write to disk.
     *
     * @return
     */
    @Override
    public String toStringForSave() {
        return tag == null
               ? "D " + super.toStringForSave() + " # " + this.deadlineDate + " " + this.time
               : "D " + super.toStringForSave() + " # " + this.deadlineDate + " " + this.time + " "
                + this.tag.toString();
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return string representation of Deadline object.
     */
    @Override
    public String toString() {
        return tag == null
                ? ("[D]" + super.toString() + " (by: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")"
                + "\n")
                : ("[D]" + super.toString() + " (by: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")"
                + this.tag.toString()
                + "\n");
    }

    /**
     * Loads a deadline task from the hard disk.
     *
     * @param todoList List of tasks in the hard disk.
     * @param taskDescription Deadline task description.
     * @param taskStatus Current completion status of the task.
     * @param taskContents Important content details of a task including date and time.
     */
    public static void loadDeadlineTask(ArrayList<Task> todoList, String taskDescription,
                                 String taskStatus, String[] taskContents) {
        String dateAndTime = taskContents[3];
        String date = dateAndTime.split(" ")[0];
        String time = dateAndTime.split(" ")[1];

        // deadline date and time
        LocalTime deadlineTime = LocalTime.parse(time);
        LocalDate deadlineDate = LocalDate.parse(date);

        // creating deadline task
        Deadline deadlineTask = new Deadline(taskDescription, deadlineDate, deadlineTime);

        // check if task is completed
        if (taskStatus.equals("0")) {
            deadlineTask.markAsNotDone();
        } else {
            deadlineTask.markAsDone();
        }

        // add deadlineTask to todoList
        todoList.add(deadlineTask);
    }
}
