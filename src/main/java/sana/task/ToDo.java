package sana.task;

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
     * @param taskName  the sana.task name
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Constructor for ToDo objects
     *
     * @param taskName  the sana.task name
     * @param isDone      whether the sana.task is done
     */
    public ToDo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Returns a String representation of the ToDo
     *
     * @return  ToDo in String
     */
    @Override
    public String toStringFromList() {
        String box1 = "[T]";
        String doneness;
        if (super.isDone()) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        return box1 + doneness + super.toString() + "\n";
    }

    /**
     * Converts the strings in mem to a ToDo
     *
     * @param taskStr   the string in mem
     * @return ToDo     the ToDo represented by taskStr
     */
    public static ToDo memToTask(String taskStr) {
        // Example str = T|1|read book
        String[] args = taskStr.split(":");
        boolean isDone = args[1].equals("1");
        return new ToDo(args[2], isDone);
    }

    /**
     * Converts ToDo into a String to be stored in mem
     *
     * @return string   string of todo to be stored in mem
     */
    @Override
    public String taskToMemStr() {
        // Example str = T|1|read book
        StringBuilder sb = new StringBuilder();
        sb.append("T:");
        if (this.isDone()) {
            sb.append("1:");
        } else {
            sb.append("0:");
        }
        sb.append(super.toString());
        return sb.toString();
    }
}
