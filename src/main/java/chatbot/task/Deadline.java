package chatbot.task;

public class Deadline extends Task {
    private final String time;

    public Deadline(String desc, String time) {
        super(desc);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}