public class Task {

    private final String title;
    private final String type;
    private final ChatBotDateTime datetime;
    private String done;

    public Task(String title, String type, ChatBotDateTime datetime) {
        this.title = title;
        this.type = type;
        this.datetime = datetime;
        this.done = " ";
    }

    public Task(
        String title,
        String type,
        String done,
        ChatBotDateTime datetime
    ) {
        this.title = title;
        this.type = type;
        this.done = done;
        this.datetime = datetime;
    }

    public String isCompleted() {
        return done;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public ChatBotDateTime getDateTime() {
        return datetime;
    }

    public void mark() {
        done = "X";
    }

    public void unmark() {
        done = " ";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, done, title);
    }
}
