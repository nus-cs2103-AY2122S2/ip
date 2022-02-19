package gene.task;

public class TodoTask extends Task {
    private final String taskTitle;
    private final String taskType = "T";
    private boolean markedStatus;

    /**
     * Constructor for to do task, this constructor is the default constructor
     * that makes new to do tasks. Confirm will be unmarked
     * @param taskTitle title of task
     */
    public TodoTask(String taskTitle) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        this.markedStatus = false;
    }

    /**
     * Constructor to toggle between marked and unmarked to do tasks
     * @param taskTitle the title of task
     * @param isMarked whether task is masked or not
     */
    public TodoTask(String taskTitle, boolean isMarked) {
        super(taskTitle);
        this.taskTitle = taskTitle;
        this.markedStatus = isMarked;
    }

    /**
     * This method marks tasks to be done
     * @return New marked task
     */
    public Task markTask() {
        assert this.markedStatus == false : "Cannot mark marked tasks";
        if (!this.markedStatus) {
            this.markedStatus = true;
        }

        return this;
    }

    /**
     * This method un marks marked tasks
     * @return New unmarked task
     */
    public Task unmarkTask() {
        assert this.markedStatus == true : "Cannot unmark unmarked tasks";
        if (this.markedStatus) {
            this.markedStatus = false;
        }

        return this;
    }

    /**
     * Returns marked status of task
     * @return boolean
     */
    public boolean isMarked() {
        return this.markedStatus;
    }

    @Override
    public String toString() {
        String mark = this.markedStatus ? "[X]" : "[ ]";
        String toReturn = "[T]" + mark + " " + this.taskTitle;
        return toReturn;
    }

    public boolean containsKeyword(String keyword) {
        return this.taskTitle.contains(keyword);
    }
}
