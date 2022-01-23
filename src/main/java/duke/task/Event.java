package duke.task;

import duke.time.Time;

import java.time.LocalDate;

public class Event extends Task {

    private LocalDate deadline;
    private String time;

    public Event(boolean completed, String task, LocalDate deadline, String time) {
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
            return "[E]" + super.toString() + " (at: " + Time.convertToString(this.deadline) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + Time.convertToString(this.deadline) + ", " + this.time + ")";
        }
    }
}