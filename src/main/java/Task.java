import java.util.ArrayList;

public class Task {
    protected String description;
    protected ArrayList<Boolean> isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = new ArrayList<Boolean>();
        isDone.add(false);
    }

    public String getStatusIcon() {
        return (isDone.get(0) ? "[X]" : "[ ]"); // mark done task with X // if done is "X" then " " 
    }

    public void setAsDone() {
        isDone.set(0, true); 
    }

    public void setAsUndone() {
        isDone.set(0, false);
    }

    public String marking(String mark) {
        if (mark.equals("mark")) {
            setAsDone();
            String messageMarked = "Nice! I've marked this task as done: \n" + 
                                    this.toString() + "\n " 
                                    + "=======================================";
            return messageMarked; 
        } else {
            setAsUndone();
            String messageUnmarked = "OK, I've marked this task as not done yet: \n" +
                                    this.toString() + "\n " 
                                    + "=======================================";
            return messageUnmarked;

        }
    }

    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    public String getInitial(){
        return "Task";
    }

    public String getDescription() {
        return description;
    }
}
