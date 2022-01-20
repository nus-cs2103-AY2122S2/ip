public class Event extends Task {
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
        return String.format("[E]%s (at: %s)",
                super.nameWithStatus(),
                this.dateRange);
    }

}
