package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadLine extends Task {
    protected LocalDate bydate;
    protected LocalTime bytime;

    public DeadLine(String objective, String date, String time) {
        super(objective);
        this.bydate = LocalDate.parse(date);
        this.bytime = LocalTime.parse(time);
    }

    public DeadLine(String objective, Boolean done, String date, String time) {
        super(objective, done);
        this.bydate = LocalDate.parse(date);
        this.bytime = LocalTime.parse(time);
    }

    public boolean sameTime(String date) {
        return this.bydate.equals(LocalDate.parse(date));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeadLine) {
            DeadLine e = (DeadLine) o;
            return this.bydate.equals(e.bydate) && this.bytime.equals(e.bytime) &&
                    this.objective.equals(e.objective) && (this.done == e.done);
        } else {
            return false;
        }
    }

    @Override
    public String serialize() {
        return "D|" + (this.done ? "1|" : "0|") + this.objective
                + "|" + bydate.toString() + "|" + bytime.toString() +"\n";
    };

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.bydate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.bytime.format(DateTimeFormatter.ofPattern("HH:mm a")) + ")";
    }
}