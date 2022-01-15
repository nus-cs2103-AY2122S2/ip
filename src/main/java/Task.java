public class Task {
    private final String content;
    private final boolean done;

    public Task(String content) {
        this.content = content;
        this.done = false;
    }

    @Override
    public String toString() {
        String s = "[";
        if (done) {
            s += "X]";
        } else {
            s += " ]";
        }
        return s + content;
    }
}
