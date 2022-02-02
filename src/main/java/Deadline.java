public class Deadline extends Task {
    protected String time;
    protected static String type = "DEADLINE";

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}
