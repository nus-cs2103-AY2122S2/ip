public class ToDos extends Task {
    protected static String type = "T";

    public ToDos(String description, boolean isDone) {
        super(type, description, isDone);
    }

    public ToDos(String description) {
        super(type, description);
    }

    @Override
    public String toString() {
        return this.isDone ? "[T][X] " + this.description
                : "[T][ ] " + this.description;
    }
}
