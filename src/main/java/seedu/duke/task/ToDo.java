package seedu.duke.task;

public class ToDo extends Task {
    private final String taskType = "T";

    /**
     * used to construct a ToDo object.
     * @param taskName which specifies name of task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * used to construct a ToDo with a specified boolean.
     * @param oldToDo for a ToDo object
     * @param done to specify a boolean
     */
    ToDo(ToDo oldToDo, boolean done) {
        super(oldToDo.getTaskName(), done, null);
    }

    public ToDo(String taskName, boolean done) {
        super(taskName, done, null);
    }

    /**
     * returns new task with specified boolean as status.
     * @param status gives a specified boolean
     * @return new Task with adjusted boolean
     */
    @Override
    public Task changeTaskStatus(boolean status) {
        return new ToDo(this, status);
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
