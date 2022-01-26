public class Todo extends Task {

    public Todo(String taskType, String description) {
        super(taskType, description, "");
    }

    public Todo(String taskType, boolean isDone, String description) {
        super(taskType, isDone, description, "");
    }

}