public class Todo extends Task {

    public Todo(String description) {
        super(description, "T");
    }

    public String toSaveDataFormat() {
        String isDone = (super.checkIsDone() == true) ? "1" : "0";
        return String.format("%s|%s|%s\n", super.getTag(), isDone, super.getTaskDescription());
    }
}