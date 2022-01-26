package duke;

/**
 * A type of tasks, do not have date
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    /**
     * Transform the task into the format of data, to be stored into file data
     * @return A formatted string
     */
    @Override
    public String dataFormatOfTask() {
        String bool;
        if(this.isDone) {
            bool = "1";
        } else {
            bool = "0";
        }
        return "T | " + bool + " | " + this.description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
