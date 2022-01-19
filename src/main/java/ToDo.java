public class ToDo extends Task {

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
