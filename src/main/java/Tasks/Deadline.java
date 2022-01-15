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
     * Overriden toString method for deadline object.
     * @return String representation of deadline object
     */
    @Override
    public String toString() {
        String str = "[D]";
        if (this.completed) {
            str += "[X] ";
        } else {
            str += "[ ] ";
        }
        str += name + " ";
        str += "(by: " + deadline + ")";
        return str;
    }

}
