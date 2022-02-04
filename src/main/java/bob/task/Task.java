package bob.task;

import java.io.Serializable;

/**
 * Represents the task that the bob program will record.
 */
public abstract class Task implements Serializable {
    protected static String[] statusSymbols = new String[]{"[ ]", "[✓]"};
    private String name;
    private String type;
    private int status;

    public Task(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public int getStatus() {
        return this.status;
    }

    public abstract String printStatus();

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
