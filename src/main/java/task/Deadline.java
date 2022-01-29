package task;

import task.Task;

import java.time.LocalDate;

public class Deadline extends Task {

    LocalDate date;

    public Deadline(String details, String date) {
        super(details);
        this.date = LocalDate.parse(date);
    }

    public String symbol() {
        return "D";
    }

    @Override
    public String displayTime() {
        return super.toString() + this.date.getDayOfMonth() + " " + this.date.getMonth() + " " + this.date.getYear();
    }

    @Override
    public String toString() {
        return super.toString() + "/" + this.date.toString();
    }

}
