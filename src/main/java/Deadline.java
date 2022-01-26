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

    @Override
    public String toData() {
        String isFinishedData;
        if (super.finished) {
            isFinishedData = "1";
        } else {
            isFinishedData = "0";
        }
        return "D:" + isFinishedData + ":" + super.content + ":" + date;
    }
}
