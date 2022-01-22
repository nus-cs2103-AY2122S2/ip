//Author: Tan Ting Yu
//Student Number: A218235J


/*
 * Task encapsulates the information necessary for a user task
 */


public class EventTask extends Task{
    String eventDate;

    /**
     *  Constructor for a EventTask
     *
     * @param taskname - Name/Description of a EventTask Object
     */

    public EventTask(String taskname, String eventDate) {
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
        return "[E]" + super.toString() + " (at: " + this.eventDate + ")";
    }

    @Override
    public String saveToFileFormat() {
        String result = "E";
        String mark = this.completed? "1":"0";
        return result + " | " + mark + " | " + taskName + " | " + eventDate;
    }
}
