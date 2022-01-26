package duke.task;

public class Todo extends Task {

    public Todo(String activity) {
        super(activity, "T");
    }

    @Override
    public String printTask() {
        if (this.status == 0) {
            return "[" + type + "][ ] " + activity;
        } else {
            return "[" + type + "][X] " + activity;
        }
    }

    @Override
    public String toString() {
        return type + "|" + status + "|" + activity + "|\n";
    }

}
