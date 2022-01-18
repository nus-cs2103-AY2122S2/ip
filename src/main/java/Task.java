public class Task {
    protected String description;
    protected boolean isDone;


    public Task (String description) {
        this.description = description;
        this.isDone = false;

    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public String markedDone() {
        this.isDone = true;
        return "[" +  this.getStatusIcon() + "] " + this.description;

    }

    public String markedUndone() {
        this.isDone = false;
        return "[" +  this.getStatusIcon() + "] " + this.description;
    }

    public String message() {
        return "[" + getStatusIcon() + "] " + this.description ;
    }


}
