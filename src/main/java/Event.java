public class Event extends Task {
    private static final String tag = "E";
    private String dateRange;

    public Event(String name, String dateRange) {
        this(name, dateRange, false);
    }

    public Event(String name, String dateRange, Boolean done) {
        super(name, done);
        this.dateRange = dateRange;
    }

    @Override
    public String nameWithStatus() {
        return String.format("[%c][%c] %s (at: %s)",
                this.tag,
                super.isDone(),
                super.getName(),
                this.dateRange);
    }

}
