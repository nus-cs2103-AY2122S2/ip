import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Task {
    private String task;
    private Boolean marked;

    public Task(String task, Boolean marked) {
        this.task = task;
        this.marked = marked;
    }

    public void setMarked(Boolean var) {
        this.marked = var;
    }

    public String getTask() {
        return this.task;
    }

    public boolean getMarked() {
        return this.marked;
    }

    public static String returnDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate dateTime = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return dateTime.format(formatter);
        } catch (DateTimeParseException e) {
            return s;
        }
    }

    @Override
    public String toString() {
        if (this.marked) {
            return "[X" + "] " + this.task;
        } else {
            return "[ " + "] " + this.task;
        }
    }
}
