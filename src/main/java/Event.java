public class Event  extends Task {
    private String time;

    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")" ;
    }

    @Override
    public String toData() {
        String isFinishedData;
        if (super.finished) {
            isFinishedData = "1";
        } else {
            isFinishedData = "0";
        }
        return "E:" + isFinishedData + ":" + super.content + ":" + time;
    }
}
