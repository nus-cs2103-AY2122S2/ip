package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Deadlines extends Task {
    protected static String type = "D";
    protected String printed;
    protected LocalDate date;
    protected LocalTime time;

    public Deadlines(String description, boolean isDone) throws DukeException {
        super(type, description, isDone);
        try {
            String[] temp = description.split("/by ");
            if (temp.length > 1) {
                String[] temp2 = temp[1].split(" ");

                if (temp2.length > 1) {
                    this.date = LocalDate.parse(temp2[0]);
                    this.time = LocalTime.parse(temp2[1]);

                    this.printed = temp[0] + " (by: "
                            + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                            + this.time.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
                } else {
                    throw new DukeException(
                            "Please include the time in the deadline in the following manner: yyyy-mm-dd hh:mm");
                }
            } else {
                throw new DukeException("Please input a deadline in the following manner: yyyy-mm-dd hh:mm");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the deadline in the proper manner: yyyy-mm-dd hh:mm");
        }
    }

    public Deadlines(String description) throws DukeException {
        this(description, false);
    }

    @Override
    public String toString() {
        return this.isDone ? "[D][X] " + this.printed
                : "[D][ ] " + this.printed;
    }
}
