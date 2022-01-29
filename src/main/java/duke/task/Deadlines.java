package duke.task;

import java.time.LocalDate;

public class Deadlines extends Task {

    private LocalDate date;

    public Deadlines(String s, String time) {
        super(s);
        this.date = LocalDate.parse(time);
        super.setDate(this.date);
    }

    @Override
    public String show() {
        if (super.getDone()) {
            return "[D][X] " + super.taskDescription() + " (by: " + date + ")";
        } else {
            return "[D][ ] " + super.taskDescription() + " (by: " + date + ")";
        }
    }

    @Override
    public String storeFormat() {
        return "D|" + super.getDone() + "|" + super.taskDescription() + "|" + this.date + "\n";
    }
}
