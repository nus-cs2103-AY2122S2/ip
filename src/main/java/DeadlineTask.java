//Author: Tan Ting Yu
//Student Number: A218235J

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Task encapsulates the information necessary for a DeadlineTask
 */


public class DeadlineTask extends Task{
    
    LocalDateTime deadline;

    /**
     *  Constructor for a DeadlineTask
     *
     * @param taskname - Name/Description of a DeadlineTask Object
     */

    public DeadlineTask(String taskname, LocalDateTime deadline) {
        super(taskname);
        this.deadline = deadline;
    }

    /**
     *  Format the string representation of DeadlineTask objects
     *
     * @return String representation of DeadlineTask objects
     */

    @Override
    public String toString() {
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");
        return "[D]" + super.toString() + " (by: " + this.deadline.format(newFormat) + ")";
    }

    @Override
    public String saveToFileFormat() {
        String result = "D";
        String mark = this.completed? "1":"0";
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return result + " | " + mark + " | " + taskName + " | " + deadline.format(oldFormat);
    }

  

}

