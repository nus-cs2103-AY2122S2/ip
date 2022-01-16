public class Task {
    private final String name;
    private final boolean isDone;

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public Task markAsDone() {
        return new Task(this.name, true);
    }

    public Task markAsUndone() {
        return new Task(this.name, false);
    }

    @Override
    public String toString() {
        String mark;
        if (this.isDone) {
            mark = "X";
        } else {
            mark = " ";
        }
        return String.format("[%s] %s", mark, this.name);
    }
}
