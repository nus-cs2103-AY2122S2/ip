package duke;

public class CustomTask extends Task {
    /**
     * Constructor for a CustomTask, used in repopulating the task array during a tasklist load
     *
     * @param type       Type of task
     * @param isComplete Whether the task is done
     * @param input      User input (includes task type and completion symbols)
     */
    public CustomTask(String type, boolean isComplete, String input) {
        super(input);
        this.type = type;
        this.description = this.input; // Updates description of class
        this.isComplete = isComplete;
    }
}
