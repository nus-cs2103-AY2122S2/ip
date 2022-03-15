package duke.task;

import java.time.LocalDate;

/**
 * Represents a task that the user wants to do. Can set to completed and uncompleted.
 *
 */
public abstract class Task implements Comparable<Task> {
    protected String objective;
    protected boolean isDone;

    /**
     * Constructor for Task class where Task is by default uncompleted.
     * @param objective Task to be done.
     */
    public Task(String objective) {
        this.objective = objective;
        this.isDone = false;
    }

    /**
     * Constructor for Task class where Task is by default uncompleted.
     * @param objective Task to be done.
     * @param done Whether Task is completed.
     */
    public Task(String objective, Boolean done) {
        this.objective = objective;
        this.isDone = done;
    }

    /**
     * Task is completed. Mark it with a cross.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Task is uncompleted. It is unmarked.
     */
    public void unmarked() {
        this.isDone = false;
    }

    /**
     * Returns a serialized version of the string.
     *
     * @return Serialized form of the string.
     */
    public abstract String serialize();

    /**
     * Check if the task contains keyword.
     *
     * @return True if the task contains keyword.
     */
    public boolean containsKeyword(String keyword) {
        return this.objective.contains(keyword);
    }

    /**
     * Check if task occurs on a specific date.
     *
     * @param date Date to be compared to the date in task.
     * @return True if task occurs on that date.
     */
    public abstract boolean sameTime(LocalDate date);

    public abstract int compareTo(Task other);

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.objective;
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
            assert arr.length == 3;
            return new ToDo(arr[2], completed);
        } else if (arr[0].equals("D")) {
            assert arr.length == 5;
            return new DeadLine(arr[2], completed, arr[3], arr[4]);
        } else if (arr[0].equals("E")) {
            assert arr.length == 6;
            return new Event(arr[2], completed, arr[3], arr[4], arr[5]);
        } else {
            assert false;
            return null;
        }
    }
}


