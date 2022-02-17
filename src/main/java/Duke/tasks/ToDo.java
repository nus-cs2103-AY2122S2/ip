package duke.tasks;

/**
 * Represents the Todo things of task.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the strings representation of ToDO in the save file.
     *
     * @return the formats of the String to be save in the file
     */
    @Override
    public String encodeTaskToString() {
        String isDoneNum = (isDone ? "1|" : "0|");
        return "T|" + isDoneNum + super.getDescription() + "\n";
    }

    /**
     * Returns the strings representation of ToDo.
     *
     * @return [T] with the status and description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns true if the new task is same as the task inside the task list.
     * Else false.
     *
     * @param other the other task to compare.
     * @return true if the new task is same as the task inside the task list.
     *         Else return false.
     */
    @Override
    public boolean taskEquals(Task other) {
        if (other instanceof ToDo) {
            ToDo toDoTask = (ToDo) other;
            boolean isSameDescription = (this.description.equals(other.description));
            if (isSameDescription) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
