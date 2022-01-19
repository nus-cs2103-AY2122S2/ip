package chatbot;
public class Task {
    protected boolean markedAsDone = false;
    protected String taskName = "";

    ////////////////////////////////////////////////////////////////
    // Constructors
    public Task(String taskName) {
        this.taskName = taskName;
    }

    ////////////////////////////////////////////////////////////////
    // Getters
    public String getTaskName() {
        return this.taskName;
    }
    
    public boolean getMarkedAsDone() {
        return this.markedAsDone;
    }

    public String getStatusIcon() {
        return (markedAsDone ? "X" : " "); // mark done task with X
    }
    
    ////////////////////////////////////////////////////////////////
    // Methods
    public void markTask(boolean bool) {
        this.markedAsDone = bool;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}