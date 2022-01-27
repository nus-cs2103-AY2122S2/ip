import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    boolean done;
    protected final String task;
    protected final String type;
    protected final String time;
    protected LocalDate date;
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    Task(String task, String type) {
        this.done = false;
        this.task = task;
        this.type = type;
        this.date = LocalDate.now();
        this.time = this.date.toString();
    }

    Task(String task, String type, String time) {
        this.done = false;
        this.task = task;
        this.type = type;
        this.time = time;
        String timeArr[] = time.split(" ");
        this.date = LocalDate.parse(timeArr[1]);
    }

    void mark() {
        this.done = true;
    }

    LocalDate getDate() {
        return this.date;
    }

    void unmark() {
        this.done = false;
    }

    String saveFormat() {
        if (this.done) {
            return this.type + " ### 1 ### " + this.task;
        } else {
            return this.type + " ### 0 ### " + this.task;
        }
    }


    @Override
    public String toString() {
        if (this.done) {
            return "[" + this.type + "] [X] " + this.task;
        } else {
            return "[" + this.type + "] [ ] " + this.task;
        }
    }
}
