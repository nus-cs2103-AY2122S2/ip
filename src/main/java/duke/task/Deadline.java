package duke.task;


import duke.parser.DateTimeParser;
import java.time.LocalDate;
public class Deadline extends Task {
    protected String type;
    protected LocalDate time;

    public Deadline(String name, LocalDate time) {
        super(name);
        this.time = time;
        this.type = "D";
    }

    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + this.type + "][" + status + "]" + this.name + " (by: "
                + DateTimeParser.formatDate(this.time) + ")";
    }
}
