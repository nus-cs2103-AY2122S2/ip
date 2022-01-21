public class Task {
    boolean isDone = false;
    String name = "";

    public Task(boolean isDone, String name) {
        this.isDone = isDone;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ",name);
    }
}
