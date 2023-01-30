package johnny;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskString() {
        return "TODO" + "," + super.isDone + "," + super.description;
    }

    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + super.getDescription();
    }

}
