

public class Deadline extends Task {
    private final String time;

    public Deadline(String title, String time, boolean status) {
        super(title, status);
        this.time = time;
    }

    public Deadline(String title, String time) {
        super(title, false);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
