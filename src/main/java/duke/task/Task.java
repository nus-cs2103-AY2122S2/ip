package duke.task;

public class Task {

    private String s;
    private boolean isDone;

    /**
     * Creates a Task instance
     * @param s Description of the task
     */
    public Task(String s) {
        this.s = s;
        this.isDone = false;
    }

    /**
     * Marks current task as done by setting done as true
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks current task by setting done as false
     */
    public void unmark() {
        this.isDone = false;
    }


    /**
     * returns String format of the task to store
     * @return String format of the task to store
     */
    public String storeFormat() {
        return this.s;
    }

    public String taskDescription() {
        return this.s;
    }

    public boolean getDone() {
        return this.isDone;
    }


    public boolean find(String phrase) {
        return this.s.contains(phrase);
    }

}
