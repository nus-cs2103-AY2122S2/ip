package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that the user wants to do by a specific date and time.
 *
 */
public class DeadLine extends Task {
    protected LocalDate doByDate;
    protected LocalTime doByTime;

    /**
     * Constructor for DeadLine class where DeadLine is by default uncompleted.
     * @param objective Task to be done.
     * @param date Date by which task should be done.
     * @param time Time by which task should be done.
     */
    public DeadLine(String objective, String date, String time) {
        super(objective);
        this.doByDate = LocalDate.parse(date);
        this.doByTime = LocalTime.parse(time);
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
        this.doByDate = LocalDate.parse(date);
        this.doByTime = LocalTime.parse(time);
    }

    @Override
    public boolean sameTime(LocalDate date) {
        return this.doByDate.equals(date);
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
    public boolean equals(Object o) {
        if (o instanceof DeadLine) {
            DeadLine e = (DeadLine) o;
            return this.doByDate.equals(e.doByDate) && this.doByTime.equals(e.doByTime)
                    && this.objective.equals(e.objective) && (this.isDone == e.isDone);
        } else {
            return false;
        }
    }

    @Override
    public String serialize() {
        assert this.doByDate != null;
        assert this.doByTime != null;
        assert this.objective != null;
        return "D|" + (this.isDone ? "1|" : "0|") + this.objective
                + "|" + doByDate.toString() + "|" + doByTime.toString() + "\n";
    };

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.doByDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.doByTime.format(DateTimeFormatter.ofPattern("HH:mm a")) + ")";
    }
}
