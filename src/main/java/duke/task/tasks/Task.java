package duke.task.tasks;

/**
 * Represent task to be done.
 * Abstract class and cannot be instantiated
 */
public abstract class Task implements ITask {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }


    /**
     * Check whether a keyword exists in the taskname
     *
     * @param word keyword search
     * @return keyword exists inside the string taskname
     */
    public boolean hasWord(String word) {
        return taskName.contains(word);
    }

    /**
     * Handle the mark and unmark instruction, alter the isDone variable
     *
     * @param instr instruction mark/unmark
     * @return String containing the information of the changed task.
     */
    public String switchMark(String instr) {
        assert instr != null;

        if ((instr.equals("mark") && !isDone)|| (instr.equals("unmark") && isDone)) {
            this.isDone = !this.isDone;
        }
        if (isDone) {
            return "Nice! I've marked this task as done:\n  " + toString();
        }
        return "OK, I've marked this task as not done yet:\n  " + toString();
    }

    /**
     * Format the isDone state of a task.
     * Returns X if it's done, empty otherwise.
     *
     * @return String representation of the current state of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), taskName);
    }

    /**
     * Format the task to be compatible with storage
     *
     * @return String representation of the task in the storage.
     */
    public String encode() {
        return String.format("%d <> %s", isDone? 1: 0, taskName);
    }

    public abstract Task cloneSelf();

    String getTaskName() {
        return this.taskName;
    }
}
