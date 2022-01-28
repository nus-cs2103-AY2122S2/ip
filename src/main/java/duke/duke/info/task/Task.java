package duke.info.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    private final String action;
    private boolean isComplete;
    private final String type;
    private String dateString;
    private LocalDate date;

    Task(String action, String type, boolean isComplete) {
        this.action = action;
        this.type = type;
        this.isComplete = isComplete; // task added is not complete by default
        this.dateString = null;
        this.date = null;
    }

    Task(String action, String type, boolean isComplete, String dateString) {
        this.action = action;
        this.type = type;
        this.isComplete = isComplete;
        try {
            this.date = LocalDate.parse(dateString);
            this.dateString = null;
        } catch (DateTimeParseException e) {
            this.date = null;
            this.dateString = dateString;
        }
    }

    void complete() {
        this.isComplete = true;
    }

    void incomplete() {
        this.isComplete = false;
    }

    public String saveFormat() {
        return String.format("%s|%s|%s", this.type, this.isComplete ? "1" : "0", this.action);
    }

    public String getDateString() {
        if (this.date != null) {
            return this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } else {
            return this.dateString;
        }
    }

    @Override
    public String toString() {
        String product = "";
        if (isComplete) {
            product += "[X] ";
        } else {
            product += "[ ] ";
        }
        product += this.action;
        return product;
    }
}
