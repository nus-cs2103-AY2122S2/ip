package duke.task;

/**
 * Represents a task that the user wants to do. Can set to completed and uncompleted.
 *
 */
public abstract class Task {
    protected String objective;
    protected boolean done;

    public Task(String objective) {
        this.objective = objective;
        this.done = false;
    }
    public Task(String objective, Boolean done) {
        this.objective = objective;
        this.done = done;
    }

    /**
     * Task is completed. Mark it with a cross.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Task is uncompleted. It is unmarked.
     */
    public void unmarked() {
        this.done = false;
    }

    /**
     * Returns a serialized version of the string.
     *
     * @return Serialized form of the string.
     */
    public abstract String serialize();

    /**
     * Check if task occurs on a specific date.
     *
     * @param date Date to be compared to the date in task.
     * @return True if task occurs on that date.
     */
    public abstract boolean sameTime(String date);

    @Override
    public String toString() {
        return (done ? "[X]" : "[ ]") + " " + this.objective;
    }

    /**
     * Convert an encoded string into a task.
     *
     * @param encoded Encoded string to be decoded into task.
     * @return Task that is formed from the encoded string.
     */
    public static Task deserialize(String encoded) {
        String[] arr = encoded.split("\\|");
        boolean completed = arr[1].equals("1");
        if (arr[0].equals("T")) {
            return new ToDos(arr[2], completed);
        } else if (arr[0].equals("D")) {
            return new DeadLine(arr[2], completed, arr[3], arr[4]);
        } else {
            return new Events(arr[2], completed, arr[3], arr[4], arr[5]);
        }
    }
}


