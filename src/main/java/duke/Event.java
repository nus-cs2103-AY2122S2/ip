package duke;

import java.time.LocalDate;
public class Event extends Task {
    protected String type;
    protected LocalDate time;

    public Event(String name, LocalDate time) {
        super(name);
        this.time = time;
        this.type = "E";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + this.type + "][" + status + "]" + this.name + " (at: "
                + DateTimeParser.formatDate(this.time) + ")";
    }

}
