public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    public Todo markAsDone() {
        return new Todo(this.name, true);
    }

    public Todo markAsUndone() {
        return new Todo(this.name, false);
    }

    @Override
    public String toString() {
        String taskType = "T";
        String doneMark;
        if (super.isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }
        return String.format("[%s][%s] %s", taskType, doneMark, super.name);
    }
}
