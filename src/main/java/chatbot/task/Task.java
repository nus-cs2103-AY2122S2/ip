package chatbot.task;

public class Task {
    private final String desc;
    private boolean done;

    public Task(String desc) {
        done = false;
        this.desc = desc;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", done ? "X" : " ", desc);
    }
}
