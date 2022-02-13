package taskmaster.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates the information necessary for a Deadline task.
 */

public class DeadlineTask extends Task {

    /** Time and Date of Deadline. **/
    private final LocalDateTime DEADLINE;
    /**
     *  Constructor for a DeadlineTask.
     *
     * @param taskName Name/Description of a DeadlineTask Object.
     *
     * @param deadline Time and Date of Deadline.
     */

    public DeadlineTask(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.DEADLINE = deadline;
    }

    /**
     * Format the string representation of DeadlineTask objects
     *
     * @return String representation of DeadlineTask objects
     */

    @Override
    public String toString() {
        //Formats the date and time based on the pattern specified.
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");
        return "[D]" + super.toString() + " (by: " + this.DEADLINE.format(newFormat) + ")";
    }

    /**
     * Format the string representation of task objects for
     * saving and writing to the text file.
     *
     * @return string representation of how the file will be saved
     * in the data file.
     */

    @Override
    public String saveToFileFormat() {
        String result = "D";
        String mark = this.isCompleted ? "1" : "0";
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return result + " | " + mark + " | " + taskName + " | " + DEADLINE.format(oldFormat);
    }
}


