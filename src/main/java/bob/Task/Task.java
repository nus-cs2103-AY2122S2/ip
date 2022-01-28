package bob.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.Serializable;

/**
 * Represents the task that the bob program will record.
 */
public abstract class Task implements Serializable {
    private String name;
    private int status;
    public String type;
    protected static String[] statusSymbols = new String[]{"[ ]", "[✓]"};

    public Task(String name) {
        this.name = name;
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
