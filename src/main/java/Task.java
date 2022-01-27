package tesseract.task;

import tesseract.main.Date;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean isOn(Date date) {
        return false;
    }

    public String toMemoryString() {
        if (isDone) {
            return "@1@" + this.description;
        } else {
            return "@0@" + this.description;
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "
                + this.description;
    }

}
