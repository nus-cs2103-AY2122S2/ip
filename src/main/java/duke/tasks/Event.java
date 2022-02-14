package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDate time;

    /**
     * The Initial which represents the Task
     */
    private static String INITIAL  = "E";

    /**
     * Constructor for Event when first created by user
     *
     * @param description A description of the event
     * @param time The time the event is to be completed by
     */
    public Event(String description, String time) {
        super(description);
        LocalDate date = LocalDate.parse(time);
        this.time = date;
    }

    public static String getInitial() {
        return INITIAL;
    }

    public String getTime() {return this.time.toString();}

    public String getFormattedTime() {return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));}

    /**
     * Constructor for Event when loaded by storage
     *
     * @param done determines if event should be marked as complete or incomplete
     * @param description A description of the event
     * @param time The time the event is to be completed by
     */
    public Event(String done, String description, String time) {
        super(description);
        LocalDate date = LocalDate.parse(time);
        this.time = date;
        if (done.equals("true")) {
            this.done();
        }
    }

    @Override
    public String toString() {
        return "[" + getInitial() + "]" + super.toString() + " (at: " + getFormattedTime() + ")";
    }

}
