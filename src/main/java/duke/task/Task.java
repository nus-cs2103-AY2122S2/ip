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

    /**
     * Returns true if object is duplicate of Task, else return false
     * @param t Object to check
     * @return Boolean
     */
    @Override
    public boolean equals(Object t) {
        if (!(t instanceof Task)) {
            return false;
        }
        return ((Task) t).taskDescription().equals(this.s);
    }


    public boolean find(String phrase) {
        return this.s.contains(phrase);
    }

}
