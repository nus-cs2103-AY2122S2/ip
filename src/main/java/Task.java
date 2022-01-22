public class Task {
    protected String taskName;
    protected boolean isDone;

    Task() {
        this.taskName = "";
        this.isDone = false;
    }

    Task(String ss) {
        this.taskName = ss;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getDesc(){
        return "";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone?"X":" ", taskName);
    }
}

