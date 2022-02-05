package duke;

import java.io.Serializable;

class Task implements Serializable {

    // 1 indicates done and 0 indicates not done
    private int status;
    private final String name;

    public Task(String name) {
        this.status = 0;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public void mark() {
        this.status = 1;
    }

    public void unmark() {
        this.status = 0;
    }

    @Override
    public String toString() {
        if (status == 1) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
