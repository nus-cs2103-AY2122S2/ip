package duke.task;

import duke.time.Time;

import java.time.LocalDate;

public class Deadline extends Task {

    private LocalDate deadline;
    private String time;

    public Deadline(boolean completed, String task, LocalDate deadline, String time) {
        super(task, completed);
        this.deadline = deadline;
        this.time = time;
    }

    public String getDeadline() {
        return Time.convertToString(this.deadline);
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        if (this.time.equals("")) {
            return "[D]" + super.toString() + " (by: " + Time.convertToString(this.deadline) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + Time.convertToString(this.deadline) + ", " + this.time + ")";
        }
    }
}