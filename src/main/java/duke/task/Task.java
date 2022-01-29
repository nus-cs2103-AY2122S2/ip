package duke.task;

public class Task {

    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    //getter for done
    public boolean getDone(boolean bool) {
        return done;
    }

    //setter for done
    public void setDone(boolean bool) {
        this.done = bool;
    }
    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] "
        + this.description;
    }
}
