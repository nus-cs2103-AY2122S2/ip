abstract public class Task {
    protected String details;
    protected boolean isDone;

    Task(String details) {
        this.details = details;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public void setAsNotDone() {
        this.isDone = false;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    abstract public String symbol();

    @Override
    public String toString() {
        return details;
    }
}
