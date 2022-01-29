package duke.task;

import java.time.LocalDate;

public class Events extends Task {

    private LocalDate date;

    public Events (String s, String time) {
        super(s);
        this.date = LocalDate.parse(time);
        super.setDate(this.date);
    }

    @Override
    public String show() {
        if (super.getDone()) {
            return "[E][X] " + super.taskDescription() + " (at: " + date + ")";
        } else {
            return "[E][ ] " + super.taskDescription() + " (at: " + date + ")";
        }
    }

    @Override
    public String storeFormat() {
        return "E|" + super.getDone() + "|" + super.taskDescription() + "|" + this.date + "\n";
    }

}
