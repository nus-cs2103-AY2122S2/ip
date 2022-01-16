public class Deadline extends Task {
    protected String time;

    public Deadline(String message, String deadline) {
        super(message);
        time = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
    
}
