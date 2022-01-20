public class TaskStorage {
    private String description;
    private boolean complete;

    public TaskStorage(String description) {
        this.description = description;
        this.complete = false;
    }

    public void taskDone() {
        this.complete = true;
    }

    public void taskUndone() {
        this.complete = false;
    }

    @Override
    public String toString() {
        String temp;
        if (complete) {
            temp = "[X]";
        } else {
            temp = "[ ]";
        }
        return temp + " " + description;
    }
}
