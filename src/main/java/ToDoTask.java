public class ToDoTask extends Task{
    ToDoTask(String ss) {
        this.taskName = ss;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone?"X":" ", this.taskName);
    }
}
