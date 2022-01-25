package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import duke.exceptions.DukeException;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;
    protected boolean hasTime;

    public Deadline(String item, LocalDate date) {
        super(item);
        this.date = date;
        hasTime = false;
    }

    public Deadline(String item, LocalDate date, LocalTime time) {
        super(item);
        this.date = date;
        this.time = time;
        hasTime = true;
    }

    @Override
    public String toString() {
        if (hasTime) {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    protected static Deadline createTask(String[] tokens) throws DukeException {
        String item = "";
        int idx = 0;
        for (;idx < tokens.length; idx++) {
            if (tokens[idx].equals("deadline")) {
                continue;
            } else if (tokens[idx].equals("/by")) {
                idx++;
                break;
            }
            item += tokens[idx] + " ";
        }


        if (item.equals(""))
            throw new DukeException("The description of a deadline task cannot be empty!");

        LocalDate date;
        LocalTime time;

        try {
            date = LocalDate.parse(tokens[idx]);
            idx++;
        } catch (Exception e) {
            throw new DukeException("Please specify a valid date!");
        }

        if (idx == tokens.length)
            return new Deadline(item.trim(), date);
        else {
            try {
                time = LocalTime.parse(tokens[idx]);
                return new Deadline(item.trim(), date, time);
            } catch (Exception e) {
                throw new DukeException("Please specify a valid time! (hh:mm)");
            }
        }
    }
}
