package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that the user wants to do on a specific date and between a starting and ending time.
 *
 */
public class Events extends Task {
    protected LocalDate date;
    protected LocalTime fromtime;
    protected LocalTime bytime;

    /**
     * Constructor for Events class where Event is by default uncompleted.
     * @param objective Task to be done.
     * @param date Date by which task should be done.
     * @param from Time by which task should start.
     * @param to Time by which task should end.
     */
    public Events(String objective, String date, String from, String to) {
        super(objective);
        this.date = LocalDate.parse(date);
        this.fromtime = LocalTime.parse(from);
        this.bytime = LocalTime.parse(to);
    }

    /**
     * Constructor for Events class where completion can be set.
     * @param objective Task to be done.
     * @param done Whether Task has been done.
     * @param date Date by which task should be done.
     * @param from Time by which task should start.
     * @param by Time by which task should end.
     */
    public Events(String objective, Boolean done, String date, String from, String by) {
        super(objective, done);
        this.date = LocalDate.parse(date);
        this.fromtime = LocalTime.parse(from);
        this.bytime = LocalTime.parse(by);
    }

    @Override
    public boolean sameTime(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Events) {
            Events e = (Events) o;
            return this.date.equals(e.date) && this.fromtime.equals(e.fromtime) && this.bytime.equals(e.bytime)
                    && this.objective.equals(e.objective) && this.done == e.done;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDos) {
            return -1;
        } else if (task instanceof DeadLine) {
            DeadLine deadline = (DeadLine) task;
            return this.date.compareTo(deadline.bydate);
        } else {
            Events event = (Events) task;
            return this.date.compareTo(event.date);
        }
    }

    @Override
    public String serialize() {
        assert this.date != null;
        assert this.fromtime != null;
        assert this.bytime != null;
        assert this.objective != null;
        return "E|" + (this.done ? "1|" : "0|") + this.objective + "|" + this.date.toString()
                + "|" + this.fromtime.toString() + "|" + this.bytime.toString() + "\n";
    };
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.fromtime.format(DateTimeFormatter.ofPattern("HH:mm a")) + " - "
                + this.bytime.format(DateTimeFormatter.ofPattern("HH:mm a"))
                + ")";
    }
}
