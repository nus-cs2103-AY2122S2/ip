package duke.task;

import duke.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date at;

    public Event(String taskName, String at) throws DukeException {
        super(taskName);
        try {
            this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
        } catch (ParseException e) {
            throw new DukeException("Please enter the date in \"dd / MM / yyyy HH:mm\" format");
        }
    }

    public Event(String taskName, String at, String isMarked) throws DukeException {
        super(taskName);
        try {
            this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
            this.isMarked = (isMarked.equals("1"));
        } catch (ParseException e) {
            throw new DukeException("Please enter the date in \"dd / MM / yyyy HH:mm\" format");
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy @ hh:mm a");
        return String.format("[E]%s (at: %s)", super.toString(), sdf.format(this.at));
    }

    public String toFile() {
        String markStatus = super.isMarked ? "1" : "0";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return String.format("%s | %s | %s | %s\n", "E", markStatus, super.taskName, sdf.format(this.at));
    }
}
