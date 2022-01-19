public class Deadline extends Task{

    public Deadline(String input, int number, String deadline) {
        super(input, number, deadline, "D");
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString(){
        String s = String.format("%d. [D][%s] %s (by: %s)\n", number+1, getStatus(), name, deadline);
        return s;
    }
}
