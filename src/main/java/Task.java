public abstract class Task {
    private final String content;
    private final boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public abstract Task mark(boolean isDone);

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        String s = "[";
        if (isDone) {
            s += "X] ";
        } else {
            s += " ] ";
        }
        return s + content;
    }
}
