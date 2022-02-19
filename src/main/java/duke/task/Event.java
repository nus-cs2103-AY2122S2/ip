package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that the user wants to do on a specific date and between a starting and ending time.
 *
 */
public class Event extends Task {
    protected LocalDate doByDate;
    protected LocalTime doFromTime;
    protected LocalTime doBytime;

    /**
     * Constructor for Events class where Event is by default uncompleted.
     * @param objective Task to be done.
     * @param date Date by which task should be done.
     * @param from Time by which task should start.
     * @param to Time by which task should end.
     */
    public Event(String objective, String date, String from, String to) {
        super(objective);
        this.doByDate = LocalDate.parse(date);
        this.doFromTime = LocalTime.parse(from);
        this.doBytime = LocalTime.parse(to);
    }

    /**
     * Constructor for Events class where completion can be set.
     * @param objective Task to be done.
     * @param done Whether Task has been done.
     * @param date Date by which task should be done.
     * @param from Time by which task should start.
     * @param by Time by which task should end.
     */
    public Event(String objective, Boolean done, String date, String from, String by) {
        super(objective, done);
        this.doByDate = LocalDate.parse(date);
        this.doFromTime = LocalTime.parse(from);
        this.doBytime = LocalTime.parse(by);
    }

    @Override
    public boolean sameTime(LocalDate date) {
        return this.doByDate.equals(date);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            return this.doByDate.equals(e.doByDate)
                    && this.doFromTime.equals(e.doFromTime) && this.doBytime.equals(e.doBytime)
                    && this.objective.equals(e.objective) && this.isDone == e.isDone;
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDo) {
            return -1;
        } else if (task instanceof DeadLine) {
            DeadLine deadline = (DeadLine) task;
            return this.doByDate.compareTo(deadline.doByDate);
        } else {
            Event event = (Event) task;
            return this.doByDate.compareTo(event.doByDate);
        }
    }

    @Override
    public String serialize() {
        assert this.doByDate != null;
        assert this.doFromTime != null;
        assert this.doBytime != null;
        assert this.objective != null;
        return "E|" + (this.isDone ? "1|" : "0|") + this.objective + "|" + this.doByDate.toString()
                + "|" + this.doFromTime.toString() + "|" + this.doBytime.toString() + "\n";
    };
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.doByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.doFromTime.format(DateTimeFormatter.ofPattern("HH:mm a")) + " - "
                + this.doBytime.format(DateTimeFormatter.ofPattern("HH:mm a"))
                + ")";
    }
}
