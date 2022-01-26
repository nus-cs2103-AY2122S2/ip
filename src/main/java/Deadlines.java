public class Deadlines extends Task {
    private final String timeFrame;

    public Deadlines(String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;
    }

    public Deadlines(int mark, String description, String timeFrame) {
        super(description, mark);
        this.timeFrame = timeFrame;
    }

    public String getDeadline() {
        return "[D]" + this.getTask() + "(by: " + timeFrame + ")\n";
    }
}