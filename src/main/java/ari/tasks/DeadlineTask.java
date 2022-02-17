package ari.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Reprensents a Task with Deadline
 */
public class DeadlineTask extends Task implements Dateable {
    protected String date;
    protected LocalDate deadlineDate;

    /**
     * Constructor of DeadlineTask
     *
     * @param description description of DeadlineTask
     * @param deadline    deadline of DeadlineTask
     * @param date        LocalDate representation for convenience
     */
    public DeadlineTask(String description, String deadline, LocalDate date) {
        super.taskDescription = description;
        this.date = deadline;
        this.deadlineDate = date;
    }

    /**
     * Returns string representation of DeadlineTask
     *
     * @return string representation of DeadlineTask
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Returns string representation of DeadlineTask in save file
     *
     * @return string representation of DeadlineTask in save file
     */
    @Override
    public String writeToFile() {
        return "deadline " + super.writeToFile() + "/by " + date;
    }

    @Override
    public LocalDate getDate() {
        return deadlineDate;
    }
}
