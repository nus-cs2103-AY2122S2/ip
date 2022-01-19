public class Event extends Task{

    public Event(String input, int number, String deadline) {
        super(input, number, deadline, "E");
    }

    /**
     * Gets string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString(){
        String s = String.format("%d. [E][%s] %s (at: %s)\n", number+1, getStatus(), name, deadline);
        return s;
    }
}
