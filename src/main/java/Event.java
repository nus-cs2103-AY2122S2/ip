public class Event extends Task {
    private final String date;
    private final String time;

    public Event(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String getEvent() {
        return "[E]" + this.getTask() + "(at: " + this.date + ", " + this.time + ")\n";
    }
}