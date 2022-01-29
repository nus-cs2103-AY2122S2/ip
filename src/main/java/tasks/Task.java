package tasks;

import java.io.Serializable;

public class Task implements Serializable {
    private String task;
    private boolean isDone;
    private String Symbol;
    private final String doneSymbol = "[X]";
    private final String undoneSymbol = "[ ]";

    public Task(String task) {
        this.task = task;
        Symbol = undoneSymbol;
        this.isDone = false;
    }

    public void setDone() {
        isDone = true;
    }

    public void setUnDone() {

        isDone = false;
    }

    public String getStatus() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatus() + " " + task;
    }
}
