public class Deadlines extends Task {
    private final String time;

    public Deadlines(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getDescription() {
        return "[D]" + this.getDescription() + "(by: " + time + ")\n";
    }
}
