public class ToDo extends Task {
    public ToDo(String des) {
        super(des, TaskType.TODO);
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