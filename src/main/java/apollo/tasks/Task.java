package apollo.tasks;

public class Task implements java.io.Serializable {
    private boolean isDone;
    private final String description;
    private final Type type;
    public enum Type {
        TODO, DEADLINE, EVENT;

        @Override
        public String toString() {
            return this.name().substring(0,1);
        }
    }

    public Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatus() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return description;
    }

    public void markAs(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return String.format("[%s][%s] %s", type, this.getStatus(), this.description);
    }
}
