package duke.tasks;

public class Todo extends Task {

    /**
     * The Initial which represents the Task
     */
    private final static String INITIAL  = "T";

    /**
     * Class constructor
     * @param description Description of Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Class Constrcutor which specifies if the Todo is complete or in complete
     * @param isDone
     * @param description
     */
    public Todo(String isDone, String description) {
        super(description);
        if (isDone.equals("true")) {
            this.done();
        }
    }

    public static String getInitial() {
        return INITIAL;
    }

    public String getTime() {return null;}

    public String getStatus() {
        return (isDone ? "[X] " + this.description : "[ ] " + this.description); // mark done task with X}
    }

    @Override
    public String toString() {
        return "[" + getInitial() + "]" + super.toString() ;
    }
}
