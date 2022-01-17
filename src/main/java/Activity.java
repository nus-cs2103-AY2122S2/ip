public class Activity {
    /** Name of the activity */
    private String name;
    private boolean isDone;

    public Activity(String name) {
        this.name = name;
    }

    /**
     * Marks activity as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Marks activity as undone.
     */
    public void undone() {
        this.isDone = false;
    }

    /**
     * Returns a string representing the activity's name.
     *
     * @return the name of the activity
     */
    @Override
    public String toString() {
        String status = isDone ? "[X] " : "[ ] ";
        return status + this.name;
    }
}
