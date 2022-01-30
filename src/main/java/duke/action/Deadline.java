package duke.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Action {

    private LocalDateTime date;

    public Deadline(String task, String by)  {
        super(task);
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
            this.date = LocalDateTime.parse(by, format);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format: Please delete input "
                    + "and re-enter using yyyy-mm-dd format");
            this.date = LocalDateTime.now();
        }
    }

    public Deadline(String task, LocalDateTime by, boolean bool)  {
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
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy, H:m")) + ")";
    }
}