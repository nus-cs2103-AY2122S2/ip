package DukeBot;

public class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super("E", description);
        this.time = time;
    }

    public String toString() {
        return super.toString() + "(at: " + time + ")";
    }

    @Override
    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        String saveText = String.format("E|%s|%s|%s", complete, this.getDescription(), this.time);
        return saveText;
    }

}
