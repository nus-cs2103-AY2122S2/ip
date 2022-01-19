public class Task {
    private final String content;
    private final boolean done;

    public Task(String content) {
        this.content = content;
        this.done = false;
    }

    private Task(String content, boolean done) {
        this.content = content;
        this.done = done;
    }

    public Task mark(boolean done) {
        if (done == this.done) {
            return this;
        } else {
            return new Task(this.content, done);
        }
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
