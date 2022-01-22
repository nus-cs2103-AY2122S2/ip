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
     */
    public ToDo(String input, int number, boolean reading){
        super(input, number, null, "T", reading);
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString(){
        String s = String.format("%d. [T][%s] %s\n", number+1, getStatus(), name);
        return s;
    }
}
