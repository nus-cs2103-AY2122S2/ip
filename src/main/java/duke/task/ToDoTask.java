package duke.task;

public class ToDoTask extends Task{
    public ToDoTask(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public ToDoTask(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone?"X":" ", this.taskName);
    }
}
