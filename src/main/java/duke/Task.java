package duke;

/**
 * Represents tasks that user enters into the app
 */
public class Task {
    /**
     * String description of task to be completed
     */
    String description;
    /**
     * Boolean to indicate if class is completed
     */
    Boolean completed = false;

    /**
     * Creation of new task
     *
     * @param description description of task to be completed
     */
    Task(String description){
        this.description = description;
    }

    /**
     * Marks task as completed
     */
    public void markCompleted(){
        this.completed = true;
    }

    /**
     * Marks task as incompleted
     */
    public void markIncompleted(){
        this.completed = false;
    }

    /**
     * Prints details of task
     */
    public void print() {
        System.out.println("[" + (this.completed ? "x" : " ") +  "] " + this.description);
    }

    /**
     * Returns String array of details of task
     * Index 1: Completed
     * Index 2: Description
     *
     * @return String array of details
     */
    public String[] getDetails() {
        String[] details = new String[4];
        details[1] = completed ? "1" : "0";
        details[2] = description;
        return details;

    }
}
