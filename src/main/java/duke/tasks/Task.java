package duke.tasks;

public class Task {
    public String name;
    public boolean isDone;
    public String info;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks this task as done/not done respectively.
     * To be overridden by child classes.
     */
    public void mark() { System.out.printf("Error! Mark method has not been overridden!"); }
    public void unmark() { System.out.printf("Error! Unmark method has not been overridden!"); }
    
}
