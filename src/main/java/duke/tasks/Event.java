package duke.tasks;

public class Event extends Task{

    private String date;

    public Event(String content, String date) {
        super(content);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }
}
