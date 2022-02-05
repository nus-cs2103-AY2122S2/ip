package duke;

/**
 * Represents a task without a time or date and has the same behavior as parent class Task.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo class
     *
     * @param d a string representing a description of the task
     */
    public ToDo(String d) {
        super(d);
    }


    /**
     * Compares this instance with a Task
     * @param t1 a Task object
     * @return integer representing which deadline takes precedence
     */
    @Override
    public int compareTo(Task t1) {
        if (t1 instanceof ToDo) {
            boolean checkIsDone = this.isDone == t1.isDone;
            if (checkIsDone) {
                return this.description.compareTo(t1.description);
            } else {
                if (t1.isDone) {
                    return -1;
                } else {
                    return 1;
                }
            }
        } else {
            return -1; //ToDo takes precedence
        }
    }
    /**
     * Returns the task properties in the format of the task to be saved onto hard disk
     *
     * @return String representing the task toString in hard-disk format
     */
    @Override
    public String toStringInFileFormat() {
        return "T|" + this.getStatusIcon() + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
