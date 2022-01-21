//Author: Tan Ting Yu
//Student Number: A218235J



/*
 * Task encapsulates the information necessary for a TodoTask
 */


public class TodoTask extends Task {

    /**
     *  Constructor for a TodoTask
     *
     * @param taskname - Name/Description of a TodoTask Object
     */
    TodoTask(String taskname) {
        super(taskname);
    }

    /**
     *  Format the string representation of TodoTask objects
     *
     * @return String representation of TodoTask objects
     */


    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveToFileFormat() {
        String result = "T";
        String mark = this.completed? "1":"0";
        return result + " | " + mark + " | " + taskName;
    }
    
}
