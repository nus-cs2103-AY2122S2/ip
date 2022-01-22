//Author: Tan Ting Yu
//Student Number: A218235J


/*
 * Task encapsulates the information necessary for a DeadlineTask
 */


public class DeadlineTask extends Task{
    
    String deadline;

    /**
     *  Constructor for a DeadlineTask
     *
     * @param taskname - Name/Description of a DeadlineTask Object
     */

    public DeadlineTask(String taskname, String deadline) {
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
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String saveToFileFormat() {
        String result = "D";
        String mark = this.completed? "1":"0";
        return result + " | " + mark + " | " + taskName + " | " + deadline;
    }

}


