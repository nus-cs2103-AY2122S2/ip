/**
 * This is a type of Task: todo
 *
 */
public class ToDo extends Task {
    /**
     * Constructor of Todo.
     *
     * @param input descriptor of task
     * @param number task number
     */
    public ToDo(String input, int number){
        super(input.substring(4,input.length()), number, null, "T");
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
