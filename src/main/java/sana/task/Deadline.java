package sana.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the Deadline sana.task
 *
 * @author Jan
 * @version 1.0
 */
public class Deadline extends Task {
    /** The deadline */
    private LocalDate deadlineTime;

    /**
     * Constructor for Deadline objects
     *
     * @param deadlineName  the deadline name
     * @param deadlineTime  the deadline
     */
    public Deadline(String deadlineName, LocalDate deadlineTime) {
        super(deadlineName);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Constructor for Deadline objects
     *
     * @param deadlineName  the deadline name
     * @param isDone        if the deadline is done
     * @param deadlineTime  the deadline
     */
    public Deadline(String deadlineName, boolean isDone, String deadlineTime) {
        super(deadlineName, isDone);
        this.deadlineTime = LocalDate.parse(deadlineTime);
    }


    /**
     * Returns a String representation of the Deadline
     *
     * @return  Deadline in String
     */
    @Override
    public String toStringFromList() {
        String box1 = "[D]";
        String doneness;
        if (super.isDone()) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        String deadlineName = super.toString();
        String time = " (by: " + deadlineTimeToString() + ")";
        return box1 + doneness + deadlineName + time;
    }

    /**
     * Converts the strings in mem to a Deadline
     *
     * @param taskStr   the string in mem
     */
    public static Deadline memToTask(String taskStr) {
        // Example str = D|0|return book|June 6th
        String[] args = taskStr.split(":");
        boolean isDone = args[1].equals("1");
        return new Deadline(args[2], isDone, args[3]);
    }

    /**
     * Converts Deadline into a string to be stored in mem
     *
     * @return string of deadline to be stored in mem
     */
    @Override
    public String taskToMemStr() {
        // Example str = D|0|return book|June 6th
        StringBuilder sb = new StringBuilder();
        sb.append("D:");
        if (this.isDone()) {
            sb.append("1:");
        } else {
            sb.append("0:");
        }
        sb.append(super.toString());
        sb.append(":");
        sb.append(this.deadlineTime);
        return sb.toString();
    }

    /**
     * Returns a string representation of the deadline time
     *
     * @return  deadline time in string
     */
    private String deadlineTimeToString() {
        return deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
