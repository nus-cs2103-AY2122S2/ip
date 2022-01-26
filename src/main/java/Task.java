public class Task {
    protected String taskType;
    protected boolean isDone;
    protected String description;
    protected String date;

    public Task(String taskType, String description, String date) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.date = date;
    }

    public Task(String taskType, boolean isDone, String description, String date) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
        this.date = date;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() { return this.taskType;}

    public boolean getIsDone() { return this.isDone;}

    public String getDescription() { return this.description; }

    public String getDate() {
        if (!this.taskType.equals(TaskType.TODO.getTaskType())) {
            return this.date;
        }
        return "";
    }

    private boolean hasAlreadyMark() {
        return this.isDone;
    }

    private boolean hasAlreadyUnmark() {
        return !this.isDone;
    }

    public void setDone() {
        if (hasAlreadyMark()) {
            System.out.println("\tThis task is already marked!");
        } else {
            this.isDone = true;
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + this);
        }
    }

    public void setUndone() {
        if (hasAlreadyUnmark()) {
            System.out.println("\tThis task is already unmarked!");
        } else {
            this.isDone = false;
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t  " + this);
        }
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "][" + this.getStatusIcon() + "] " + this.description;
    }
}