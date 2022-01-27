public class ToDo extends Task {
    public ToDo(String des) {
        super(des, TaskType.TODO);
    }

    public ToDo(String des, boolean isDone) {
        super(des, TaskType.EVENT, isDone);
    }

    @Override
    public String parseTask() {
        return "T | " + Boolean.toString(isDone) + " | " + description;
    }

    @Override
    public String toString() {
        if (isDone) {
            return  "[T][X] " + description;
        } else {
            return "[T][ ] " + description;
        }
    }
}