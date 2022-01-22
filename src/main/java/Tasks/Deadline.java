package Tasks;

/**
 * Deadline type of Task, with a date and time that the task must be completed.
 */
public class Deadline extends Task{
    private String deadline;

    /**
     * Constructor for Deadline.
     * @param name
     * @param deadline
     */
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Constructor for Deadline with completion status.
     * @param name
     * @param deadline
     * @param isCompleted
     */
    public Deadline(String name, String deadline, boolean isCompleted) {
        super(name, isCompleted);
        this.deadline = deadline;
    }


    /**
     * Returns the file string representation of this deadline.
     */
    @Override
    public String toFileString() {
        return "D : " + (isCompleted ? "1 : " : "0 : ") + name + " : " + deadline;
    }
    /**
     * Overriden toString method for deadline object.
     * @return String representation of deadline object
     */
    @Override
    public String toString() {
        String str = "[D]";
        if (this.isCompleted) {
            str += "[X] ";
        } else {
            str += "[ ] ";
        }
        str += name + " ";
        str += "(by: " + deadline + ")";
        return str;
    }

}
