public class Event extends Task {

    private final ChatBotDateTime time;

    public Event(String title, ChatBotDateTime time) {
        super(title, "E", time);
        this.time = time;
    }

    public Event(String title, String done, ChatBotDateTime time) {
        super(title, "E", done, time);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), time);
    }
}
