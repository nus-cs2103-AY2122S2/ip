public class Deadlines extends Task {
    private final String timeFrame;

    public Deadlines(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    public String getDeadline() {
        return "[D]" + this.getTask() + "(by: " + timeFrame + ")\n";
    }
}