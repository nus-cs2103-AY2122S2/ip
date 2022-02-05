package duke;

public class ToDo extends Task {

    static final int TASK_INDEX = 0;
    static final int TIME_INDEX = 3;
    /**
     * Creates new Todo task
     *
     * @param description description of task
     */
    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (this.isCompleted ? "x" : " ") + "] " + this.description + "\n";
    }

    /**
     * Returns details of task as a string array
     * Index 0: TaskType
     * Index 1: Completed
     * Index 2: Description
     * Index 3: Time = ""
     *
     * @return String array of details
     */
    @Override
    public String[] getDetails() {
        String[] details = super.getDetails();
        details[TASK_INDEX] = TaskType.TODO.toString();
        details[TIME_INDEX] = "";
        return details;
    }
}
