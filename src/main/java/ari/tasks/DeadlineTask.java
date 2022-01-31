package ari.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Reprensents a Task with Deadline
 */
public class DeadlineTask extends Task {
    protected String time;
    protected LocalDate nowTime;

    /**
     * Constructor of DeadlineTask
     *
     * @param description description of DeadlineTask
     * @param deadline    deadline of DeadlineTask
     * @param date        LocalDate representation for convenience
     */
    public DeadlineTask(String description, String deadline, LocalDate date) {
        super.taskDescription = description;
        this.time = deadline;
        this.nowTime = date;
    }

    /**
     * Returns string representation of DeadlineTask
     *
     * @return string representation of DeadlineTask
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + nowTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns string representation of DeadlineTask in save file
     *
     * @return string representation of DeadlineTask in save file
     */
    @Override
    public String writeToFile() {
        return "deadline " + super.writeToFile() + "/by " + time;
    }

}
