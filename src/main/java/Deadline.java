public class Deadline extends Task {
    private String date;
    public Deadline(String content, String date) {
        super(content);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( by:" + date + ")";
    }
}
