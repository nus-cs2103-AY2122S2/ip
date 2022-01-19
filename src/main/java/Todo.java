public class Todo extends Task {
    String type = "[T]";

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.description;
    }

    @Override
    public String track() {
        return this.type;
    }
}