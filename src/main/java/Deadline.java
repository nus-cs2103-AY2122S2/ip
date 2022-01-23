public class Deadline extends Task {

    private final ChatBotDateTime by;

    public Deadline(String title, ChatBotDateTime by) {
        super(title, "D", by);
        this.by = by;
    }

    public Deadline(String title, String done, ChatBotDateTime by) {
        super(title, "D", done, by);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
