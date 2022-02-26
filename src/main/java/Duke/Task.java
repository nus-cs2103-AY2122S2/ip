package Duke;

abstract class Task {
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


    @Override
    public String toString() {
        // [X] study
        return "[" + ((isCompleted)?"X":" ") + "] " + description;
    }

    // T | 1 | read book
    public String toFileString(){
        return " | "+((isCompleted)?"1 | ":"0 | ") + description;
    }
}
