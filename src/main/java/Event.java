public class Event extends Task {

    private String startTime;

    public Event(String description, String time) {
        super(description);
        this.startTime = time;
    }

    public String getStartTime() {
        return this.startTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]")
                .append(super.toString())
                .append(" (at: ")
                .append(this.startTime)
                .append(")");
        return sb.toString();
    }
}
