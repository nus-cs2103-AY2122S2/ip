package duke;

public class ToDo extends Task {

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
        details[0] = TaskType.TODO.toString();
        details[3] = "";
        return details;
    }
}
