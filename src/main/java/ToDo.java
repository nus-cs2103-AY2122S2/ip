public class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    String getTaskType() {
        return "T";
    }

    public String toString() {
        return String.format("[%s]", getTaskType()) + super.toString();
    }
}
