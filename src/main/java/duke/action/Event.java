package java.duke.action;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Action {

    private LocalDate date;

    public Event(String task, String at) {
        super(task);
        try {
            this.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format: Please use yyyy-mm-dd");
        }
    }

    public Event(String task, LocalDate date, boolean bool){
        super(task, bool);
        this.date = date;
    }

    /**
     * Returns a new Event object with same variable values except
     * isDone which is now true
     * @return marked Event object
     */
    @Override
    public Action setDone()  {
        return new Event(act, date, true);
    }

    /**
     * Returns a new Event object with same variable values except
     * isDone which is now false
     * @return unmarked Event object
     */
    @Override
    public Action setUnDone()  {
        return new Event(act, date, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}