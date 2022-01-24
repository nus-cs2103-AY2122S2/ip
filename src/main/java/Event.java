public class Event extends Task {
    private String dateRange;
    private char tag = 'E';

    public Event(String name, String dateRange) {
        this(name, dateRange, false);
    }

    public Event(String name, String dateRange, Boolean done) {
        super(name, done);
        this.dateRange = dateRange;
    }

    @Override
    public String nameWithStatus() {
        return String.format("%s (at: %s)",
                super.nameWithStatus(),
                this.dateRange);
    }

    @Override
    public String fileSaveFormat() {
        return String.format("%s||%s)",
                super.fileSaveFormat(),
                this.dateRange);
    }
}
