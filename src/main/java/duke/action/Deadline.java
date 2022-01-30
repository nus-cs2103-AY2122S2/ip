package java.duke.action;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Action {

    private LocalDate date;

    public Deadline(String task, String by)  {
        super(task);
        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format: Please use yyyy-mm-dd");
        }
    }

    public Deadline(String task, LocalDate by, boolean bool)  {
        super(task, bool);
        this.date = by;
    }

    /**
     * Returns a new Deadline object with same variable values except
     * isDone which is now true
     * @return marked Deadline object
     */
    @Override
    public Action setDone()  {
        return new Deadline(act, date, true);
    }

    /**
     * Returns a new Deadline object with same variable values except
     * isDone which is now false
     * @return unmarked Deadline object
     */
    @Override
    public Action setUnDone()  {
        return new Deadline(act, date, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}