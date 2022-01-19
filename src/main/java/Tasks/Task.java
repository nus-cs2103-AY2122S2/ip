package Tasks;

import SparkExceptions.TaskModificationExceptions.TaskAlreadyMarked;
import SparkExceptions.TaskModificationExceptions.TaskAlreadyUnMarked;

public class Task {
    protected String title;
    protected boolean done;

    public Task(String name) {
        this.title = name;
    }

    public String getTitle() {
        return title;
    }

    public void mark() throws TaskAlreadyMarked {
        if (this.done) {
            throw new TaskAlreadyMarked(this);
        }

        this.done = true;
    }

    public void unMark() throws TaskAlreadyUnMarked {
        if (!this.done) {
            throw new TaskAlreadyUnMarked(this);
        }

        this.done = false;
    }

    public String getStatusIcon() {
        return this.done ? "[X]" : "[]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.getTitle());
    }
}
