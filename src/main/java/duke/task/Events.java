package duke.task;

import java.time.LocalDate;

public class Events extends Task {

    private LocalDate date;

    /**
     * Creates an Events instance
     * @param s Task description
     * @param time Date which task is due
     */
    public Events (String s, String time) {
        super(s);
        this.date = LocalDate.parse(time);
    }

    /**
     * Returns a String representation of the task
     * @return String representation of this Events
     */
    @Override
    public String toString() {
        if (super.getDone()) {
            return "[E][X] " + super.taskDescription() + " (at: " + date + ")";
        } else {
            return "[E][ ] " + super.taskDescription() + " (at: " + date + ")";
        }
    }

    /**
     * Returns a String format of the task to store
     * @return String format of this Events
     */
    @Override
    public String storeFormat() {
        return "E|" + super.getDone() + "|" + super.taskDescription() + "|" + this.date + "\n";
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns true if Object is duplicate of Event, else return false
     * @param e Object to check
     * @return Boolean
     */
    @Override
    public boolean equals(Object e) {
        if (!(e instanceof Events)) {
            return false;
        }
        Events ev = (Events) e;
        if (!ev.getDate().equals(this.date)) {
            return false;
        }
        if (!ev.taskDescription().equals(this.taskDescription())) {
            return false;
        }
        return true;
    }
}
