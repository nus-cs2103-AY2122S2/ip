public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() { return this.taskType;}

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