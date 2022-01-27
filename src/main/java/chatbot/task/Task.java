package chatbot.task;

import java.io.Serializable;

public class Task implements Serializable {
    private final String desc;
    private boolean isDone;

    public Task(String desc) {
        isDone = false;
        this.desc = desc;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", desc);
    }
}