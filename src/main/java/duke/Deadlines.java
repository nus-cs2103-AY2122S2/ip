package duke;

public class Deadlines extends Task {
    private final String time;
    private final String date;

    public Deadlines(String description, String date, String time) {
        super(description);
        this.time = time;
        this.date = date;
    }

    public Deadlines(int mark, String description, String date, String time) {
        super(description, mark);
        this.date = date;
        this.time = time;
    }

    public String getDeadline() {
        return "[D]" + this.getTask() + "(by: " + this.date + ", " + this.time + ")\n";
    }

    public String getFormattedText() {
        return "D>" + this.getMark() + ">" + this.getDescription() + ">" + this.date + ">" + this.time;
    }
}