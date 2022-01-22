import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//Author: Tan Ting Yu
//Student Number: A218235J


/*
 * Task encapsulates the information necessary for a user task
 */


public class EventTask extends Task{
    LocalDateTime eventDate;

    /**
     *  Constructor for a EventTask
     *
     * @param taskname - Name/Description of a EventTask Object
     */

    public EventTask(String taskname, LocalDateTime eventDate) {
        super(taskname);
        this.eventDate = eventDate;

    }

    /**
     *  Format the string representation of EventTask objects
     *
     * @return String representation of EventTask objects
     */

    @Override
    public String toString() {
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");
        return "[E]" + super.toString() + " (at: " + this.eventDate.format(newFormat) + ")";
    }

    @Override
    public String saveToFileFormat() {
        String result = "E";
        String mark = this.completed? "1":"0";
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return result + " | " + mark + " | " + taskName + " | " + eventDate.format(oldFormat);
    }

    
}
