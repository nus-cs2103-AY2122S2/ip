package duke.task;

public class Todo extends Task {

    public Todo(String activity) {
        super(activity, "T");
    }

    @Override
    public String printTask() {
        if (this.status) {
            return "[" + type + "][X] " + activity;
        } else {
            return "[" + type + "][ ] " + activity;
        }
    }

    @Override
    public String toString() {
        return type + "|" + status + "|" + activity + "|\n";
    }

}
