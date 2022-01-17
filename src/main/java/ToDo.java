/**
 * This class represents the ToDo tasks
 *
 * @author  Jan Alfenson Tan
 * @version 1.0
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo objects
     *
     * @param taskName  the task name
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a String representation of the ToDo
     *
     * @return  ToDo in String
     */
    @Override
    public String toString() {
        String box1 = "[T]";
        String doneness;
        if (super.getDone()) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        return box1 + doneness + super.toString();
    }
}
