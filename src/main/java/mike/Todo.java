package mike;
public class Todo extends Task{

    private static final String taskType = "T";

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

    public String convertToStoredTaskFormat() {
        String doneIndicator = "false";
        if (super.isDone) {
            doneIndicator = "true";
        }
        String storedListFormat = String.format("%s|%s|%s|", taskType,
                doneIndicator, super.name);
        return storedListFormat;
    }

    @Override
    public String toString() {
        String doneMark;
        if (super.isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }
        return String.format("[%s][%s] %s", taskType, doneMark, super.name);
    }
}
