package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that the user wants to do by a specific date and time.
 *
 */
public class DeadLine extends Task {
    protected LocalDate bydate;
    protected LocalTime bytime;

    /**
     * Constructor for DeadLine class where DeadLine is by default uncompleted.
     * @param objective Task to be done.
     * @param date Date by which task should be done.
     * @param time Time by which task should be done.
     */
    public DeadLine(String objective, String date, String time) {
        super(objective);
        this.bydate = LocalDate.parse(date);
        this.bytime = LocalTime.parse(time);
    }

    /**
     * Constructor for DeadLine class where completion can be set.
     * @param objective Task to be done.
     * @param done Whether Task has been done.
     * @param date Date by which task should be done.
     * @param time Time by which task should be done.
     */
    public DeadLine(String objective, Boolean done, String date, String time) {
        super(objective, done);
        this.bydate = LocalDate.parse(date);
        this.bytime = LocalTime.parse(time);
    }

    @Override
    public boolean sameTime(LocalDate date) {
        return this.bydate.equals(date);
    }

    @Override
    public int compareTo(Task task) {
        if (task instanceof ToDos) {
            return -1;
        } else if (task instanceof DeadLine) {
            DeadLine deadline = (DeadLine) task;
            return this.bydate.compareTo(deadline.bydate);
        } else {
            Events event = (Events) task;
            return this.bydate.compareTo(event.date);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeadLine) {
            DeadLine e = (DeadLine) o;
            return this.bydate.equals(e.bydate) && this.bytime.equals(e.bytime)
                    && this.objective.equals(e.objective) && (this.done == e.done);
        } else {
            return false;
        }
    }

    @Override
    public String serialize() {
        assert this.bydate != null;
        assert this.bytime != null;
        assert this.objective != null;
        return "D|" + (this.done ? "1|" : "0|") + this.objective
                + "|" + bydate.toString() + "|" + bytime.toString() + "\n";
    };

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.bydate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.bytime.format(DateTimeFormatter.ofPattern("HH:mm a")) + ")";
    }
}
