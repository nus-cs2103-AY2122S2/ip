package duke;

/**
 * This is a type of duke.Task: todo
 *
 */
public class ToDo extends Task {
    /**
     * Constructor of Todo.
     *
     * @param input descriptor of task
     * @param number task number
     * @param isReading flag to check if input is being read from file data
     */
    public ToDo(String input, int number, boolean isReading){
        super(input, number, null, "T", isReading);
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString(){
        String s = String.format("%d. [T][%s] %s\n", number + 1, getStatus(), name);
        return s;
    }
}
