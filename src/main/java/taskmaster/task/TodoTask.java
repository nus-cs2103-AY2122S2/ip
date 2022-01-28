package taskmaster.task;

/**
 * This class encapsulates the information necessary for a Todo task.
 */

public class TodoTask extends Task {

    /**
     *  Constructor for a TodoTask
     *
     * @param taskName Name/Description of a TodoTask Object
     */

    public TodoTask(String taskName) {
        super(taskName);
    }

    /**
     * Format the string representation of TodoTask objects
     *
     * @return String representation of TodoTask objects
     */

    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Format the string representation of task objects for
     * saving and writing to the text file.
     */

    @Override
    public String saveToFileFormat() {
        String result = "T";
        String mark = this.isCompleted ? "1" : "0";
        return result + " | " + mark + " | " + taskName;
    }
}
