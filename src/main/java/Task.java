public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        String icon = this.getStatusIcon();
        String output = "[" + icon + "] " + this.description;
        return output;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(){
        this.isDone = true;
    }

    public void setNotDone(){
        this.isDone = false;
    }

}