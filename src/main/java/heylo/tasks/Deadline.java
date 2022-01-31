package heylo.tasks;

import heylo.util.DateFormatter;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate deadline;

    public Deadline(String description, String deadline) {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (Exception e) {
            this.deadline = null;
        }
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + "\t (by " + DateFormatter.formatDateInLongForm(deadline) + ")";
    }
}