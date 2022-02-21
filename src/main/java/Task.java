public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description, boolean isCompleted){
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) { isCompleted = completed; }

    //public void setCompleted() { this.isCompleted = true; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "[" + ((isCompleted)?"X":" ") + "] " + description;
    }
}
