package bob.task;

import java.io.Serializable;

/**
 * Represents the task that the bob program will record.
 */
public abstract class Task implements Serializable {
    protected static String[] statusSymbols = new String[]{"[ ]", "[✓]"};
    private String name;
    private String type;
    private String taskStatus;
    private enum Status {
        MARKED, UNMARKED
    }

    public Task(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public boolean isMarked() {
        return taskStatus.equals(Status.MARKED.name());
    }

    public void unmarkTask() {
        this.taskStatus = Status.UNMARKED.name();
    }

    public String getStatusSymbol() {
        if (taskStatus.equals(Status.MARKED.name())) {
            return statusSymbols[1];
        } else {
            return statusSymbols[0];
        }
    }

    public abstract String printStatus();

    public void markTask() {
        this.taskStatus = Status.MARKED.name();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
