package bob.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.Serializable;

public abstract class Task implements Serializable {
    private String name;
    private int status;
    public String type;
    protected static String[] statusSymbols = new String[]{"[ ]", "[✓]"};

    public Task(String name) {
        this.name = name;
    }

    public static Task addNewToDo(String input) {
        return new Todo(input);
    };

    public String getType() {
        return this.type;
    }

    public static Task addNewDeadline(String input, LocalDateTime date) {
        return new Deadline(input, date);
    }

    public static Task addNewEvent(String input, LocalDate date, LocalTime startTime, LocalTime endTime) {
        return new Event(input, date, startTime, endTime);
    }

    public String getName() { return this.name; }

    public int getStatus() { return this.status; }

    public abstract String printStatus();

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
