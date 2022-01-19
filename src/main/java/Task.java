public abstract class Task {
    private final String content;
    private final boolean done;

    public Task(String content) {
        this.content = content;
        this.done = false;
    }

    public Task(String content, boolean done) {
        this.content = content;
        this.done = done;
    }

    public abstract Task mark(boolean done);

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        String s = "[";
        if (done) {
            s += "X] ";
        } else {
            s += " ] ";
        }
        return s + content;
    }
}
