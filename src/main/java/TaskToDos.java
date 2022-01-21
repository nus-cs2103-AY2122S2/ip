public class TaskToDos extends Task {

    public TaskToDos(boolean isDone, String name) {
        super(isDone, name);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X" : " ", name);
    }
}
