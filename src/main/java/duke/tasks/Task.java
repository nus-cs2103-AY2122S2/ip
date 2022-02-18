package duke.tasks;

public class Task {
    protected boolean isDone;
    protected String name;
    protected String info;

    //Constructor
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    //Accessors
    public boolean getStatus() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public String getInfo() {
        return this.info;
    }

    /**
     * Marks this task as done/not done respectively.
     * To be overridden by child classes.
     */
    public void mark() {
        System.out.printf("Error! Mark method has not been overridden!");
    }
    public void unmark() {
        System.out.printf("Error! Unmark method has not been overridden!");
    }

}
