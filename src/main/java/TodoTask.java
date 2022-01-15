public class TodoTask extends Task {
    TodoTask(String taskname) {
        super(taskname);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
