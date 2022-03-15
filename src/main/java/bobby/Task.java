package bobby;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create an instance of Task.
     *
     * @param description description of the Task to be done.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public abstract void writeToFile(FileWriter fw) throws IOException;

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
