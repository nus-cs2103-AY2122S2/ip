public class Event extends Task {
    private String metaInfo;

    public Event(String description, String metaInfo) {
        super(description);
        this.metaInfo = metaInfo;
    }

    @Override
    public String getTaskIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + "(at:" + this.metaInfo + ")";
    }
}