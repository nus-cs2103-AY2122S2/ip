package duke.task;
public abstract class Task {
    
    protected String description; 
    protected boolean done;

    public Task(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        if (this.done == true) {
            System.out.println("this item has already been marked as done");
        } else {
            this.done = true;
        }
    }

    public void markAsUndone() {
        if (this.done == false) {
            System.out.println("this item has already been marked as undone");
        } else {
            this.done = false;
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public abstract String toStringSaveData();

}
