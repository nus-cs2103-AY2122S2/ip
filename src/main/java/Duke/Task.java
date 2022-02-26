package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Task {
    private String description;
    private boolean isCompleted;
    LocalDateTime createdDate;
    private final static DateTimeFormatter fm = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");

    public Task(String description, boolean isCompleted){
        this.description = description;
        this.isCompleted = isCompleted;
        this.createdDate = LocalDateTime.now();
    }

    public Task(String description, boolean isCompleted, LocalDateTime createdDate){
        this.description = description;
        this.isCompleted = isCompleted;
        this.createdDate = createdDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) { isCompleted = completed; }

    //public void setCompleted() { this.isCompleted = true; }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }


    @Override
    public String toString() {
        // [X] study
        return "[" + ((isCompleted)?"X":" ") + "] " + description;
    }

    /**
     * Generates parent task string to be stored in the file.
     */
    // T | 1 | read book
    public String toFileString(){
        return " | "+((isCompleted)?"1 | ":"0 | ") + description
                + " | " + createdDate.format(Parser.dtFormat);
    }
}
