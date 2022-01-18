public class Event extends Task {
    private final String date;

    Event(String task, String date) {
        super(task, "event");
        this.date = date.replaceFirst("at", "at:");
    }

    @Override
    public String toString() {
        return String.format("[E]%s(%s)", super.toString(), date);
    }
}
